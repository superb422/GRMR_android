package com.example.dongh.grmr.Login;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongh.grmr.Constructor.Contributor;
import com.example.dongh.grmr.Main.MainActivity;
import com.example.dongh.grmr.Profile_Register.register_step1;
import com.example.dongh.grmr.R;
import com.example.dongh.grmr.Travel.Travel_Schedule;
import com.example.dongh.grmr.service.ServerService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    static Context mContext;
    private CallbackManager faceCallbackManager;
    SessionCallback kakaoCallback;
    private int check = 0;
    ImageView facebookLoginButton, kakaoLoginButton, emailLoginButton;
    TextView registerButton;


    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = Utility.getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("TAG", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        mContext = getApplicationContext();
        kakaoCallback = new SessionCallback(); // 콜백 선언
        Session.getCurrentSession().addCallback(kakaoCallback);

        /********************************갈래말래(email) Login*************************************/
        emailLoginButton = (ImageView) findViewById(R.id.emailLoginButton);
        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent login2Intent = new Intent(LoginActivity.this, register_step1.class);
                Intent login2Intent = new Intent(LoginActivity.this, Travel_Schedule.class);
                LoginActivity.this.startActivity(login2Intent);
            }
        });

        /****************************FaceBook Login***********************************/
        facebookLoginButton = (ImageView) findViewById(R.id.fbLoginButton);
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookLoginOnClick(view);
            }
        });

        /******************************Kakaotalk Login*********************************/

        kakaoLoginButton = (ImageView) findViewById(R.id.kkLoginButton);
        kakaoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Session session = Session.getCurrentSession();
                session.addCallback(new SessionCallback());
                session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
            }
        });


        /****************************Register Button************************************/
        registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, EmailregisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


    }

    /**************************갈래말래서버통신***************************************************/
    private void networkingTravel(final String accessToken, int check) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10,TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.197.13.138") // 뒤에 / 붙여야하나??
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerService service = retrofit.create(ServerService.class);
        /******************************************페이스북 토큰**************************/
        try{
            if(check == 0) {
                final Call<Contributor> getFaceToken = service.Facebook_token(accessToken);
                getFaceToken.enqueue(new Callback <Contributor>() {
                    @Override
                    public void onResponse(Call<Contributor> call, Response<Contributor> response) {
                        Contributor contributor = new Contributor(accessToken);
                        Log.e("갈래말래클라이언트", "서버에 토큰값을 전달했습니다 : " + contributor.getToken());
                        Toast.makeText(getApplicationContext(), "서버에 값을 전달했습니다 : " + contributor.getToken(), Toast.LENGTH_SHORT).show();

                        if (response.isSuccessful()) {
                            Log.e("갈래말래서버", "네트워크 정보 : " + response.code() + "");
                            Log.e("갈래말래서버", "갈래말래 토큰 : " + new Gson().toJson(response.body()));
                        } else {
                            Log.e("갈래말래서버", "네트워크 정보: " + response.code() + "");
                            Log.e("갈래말래서버", "에러바디 : " + new Gson().toJson(response.errorBody()));

                        }
                    }
                    @Override
                    public void onFailure(Call<Contributor> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("갈래말래","서버에 통신중 에러가 발생했습니다.");
                        Toast.makeText(getApplicationContext(), "서버와 통신중 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            /*************************************카카오톡 토큰*************************************/
            if(check == 1) {
                final Call<Contributor> postKakaoToken = service.Kakao_token(accessToken);
                postKakaoToken.enqueue(new Callback<Contributor>() {
                    @Override
                    public void onResponse(Call<Contributor> call, Response<Contributor> response) {
                        Contributor contributor = new Contributor(accessToken);
                        Log.e("갈래말래클라이언트", "서버에 토큰값을 전달했습니다 : " + contributor.getToken());
                        Toast.makeText(getApplicationContext(), "서버에 값을 전달했습니다 : " + contributor.getToken(), Toast.LENGTH_SHORT).show();

                        if (response.isSuccessful()){
                            Log.e("갈래말래서버", "네트워크 정보 : " + response.code() + "");
                            Log.e("갈래말래서버", "메시지 : " + new Gson().toJson(response.message()));
                            Log.e("갈래말래서버", "바디 : " + new Gson().toJson(response.body()));
                        }
                        else {
                            Log.e("갈래말래서버", "네트워크 정보 : " + response.code());
                            Log.e("갈래말래서버", "메시지 : " + new Gson().toJson(response.message()));
                            Log.e("갈래말래서버", "에러바디 : " + new Gson().toJson(response.errorBody()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Contributor> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("갈래말래","서버에 통신중 에러가 발생했습니다.");
                        Toast.makeText(getApplicationContext(), "서버와 통신중 에러가 발생했습니다", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
        catch (Exception e){
            Log.d("TEST", e.getMessage());
        }
    }

    /***************************************FaceBook Function **********************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        faceCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void facebookLoginOnClick(View v){
        FacebookSdk.sdkInitialize(getApplicationContext());
        faceCallbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(faceCallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult result) {
                System.out.println(getKeyHash(getApplicationContext()));
                GraphRequest request;
                request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject user, final GraphResponse response) {
                        if (response.getError() != null) {

                        } else { //tocken 가져옴
                            String accessToken =  result.getAccessToken().getToken();
                            String nickname = null;
                            String email = null;
                            check = 0;
                            try {
                                nickname = user.getString("name");
                                email = user.getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("Facebook", "유저정보: " + user.toString());
                            Log.i("Facebook", "페이스북토큰: " + accessToken);
                            setResult(RESULT_OK);

                            //페이스북 로그인이 완료 되면 메인페이지로 이동
                            Intent facebookIntent = new Intent(LoginActivity.this, MainActivity.class);
                            facebookIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            facebookIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(facebookIntent);
                            finish();
                            networkingTravel(accessToken, check);
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                finish();
            }

            @Override
            public void onCancel() {
                finish();
            }
        });
    }

    /***************************************Kakao Session **********************************/
    public class SessionCallback implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {

            requestMe();             // 사용자 정보 동의 화면
            System.out.println(getKeyHash(getApplicationContext()));

        }

        // 로그인에 실패한 상태
        @Override

        public void onSessionOpenFailed(KakaoException exception) {
            System.out.println("확인용===============================================로그인 실패..=============");
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());

            System.out.println(getKeyHash(getApplicationContext()));
        }

        // 사용자 정보 요청
        public void requestMe() {
            // 사용자정보 요청 결과에 대한 Callback
            UserManagement.getInstance().requestMe(new MeResponseCallback() {

                // 세션 오픈 실패. 세션이 삭제된 경우,
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    System.out.println("확인용===============================================onSessionClosed 메서드지남");
                    Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage());

                }

                // 회원이 아닌 경우,
                @Override
                public void onNotSignedUp() {
                    Log.e("SessionCallback :: ", "onNotSignedUp");
                }

                // 사용자정보 요청에 성공한 경우,
                @Override
                public void onSuccess(UserProfile userProfile) {

                    //requestAccessTokenInfo();
                    Log.e("SessionCallback :: ", "onSuccess");

                    check = 1;
                    String nickname = userProfile.getNickname();
                    String email = userProfile.getEmail();
                    String profileImagePath = userProfile.getProfileImagePath();
                    String thumnailPath = userProfile.getThumbnailImagePath();
                    String UUID = userProfile.getUUID();
                    long id = userProfile.getId();
                    String accessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();

                    Log.e("카카오톡","Profile : " + nickname + "");
                    Log.e("카카오톡","Profile : " + email + "");
                    Log.e("카카오톡","Profile : " + profileImagePath  + "");
                    Log.e("카카오톡","Profile : " + thumnailPath + "");
                    Log.e("카카오톡","Profile : " + UUID + "");
                    Log.e("카카오톡","Profile : " + id + "");
                    Log.e("카카오톡","토큰:" + accessToken+"");

                    /* 성공시 메인화면으로 이동*/
                    Intent kakaoIntent = new Intent(LoginActivity.this, MainActivity.class);  //로그인 성공시 메인화면으로 전환
                    kakaoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    kakaoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(kakaoIntent);
                    finish();

                    networkingTravel(accessToken,check);
                }

                // 사용자 정보 요청 실패
                @Override
                public void onFailure(ErrorResult errorResult) {
                    Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage());
                }
            });
        }
    }
}




