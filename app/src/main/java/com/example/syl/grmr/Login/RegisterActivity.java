package com.example.syl.grmr.Login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syl.grmr.Constructor.UserInformation;
import com.example.syl.grmr.R;
import com.example.syl.grmr.service.ServerService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {

    private static String base_URL = "http://52.197.13.138:80";
    private String email;
    private String password;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        System.out.println("서버통신 들어옴 ==========================");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ServerService service = retrofit.create(ServerService.class);

        //view
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nicknameText = (EditText) findViewById(R.id.nameText);


        //buttonListener
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                nickname = nicknameText.getText().toString();

                final Call<UserInformation> userInformationCall = service.registerUser(email, password, nickname);
                userInformationCall.enqueue(new Callback<UserInformation>() {
                    @Override
                    public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                        UserInformation userInformation = new UserInformation(email,password, nickname);

                        if(response.isSuccessful()){
                            Log.e("갈래말래서버","네트워크 상태 : " + response.code() + "");
                            Log.e("갈래말래서버","메시지 : " + response.message() + "");
                            Log.e("갈래말래서버","바디 : " + response.body() + "");
                            Toast.makeText(getApplicationContext(), "서버에 전달 했습니다: " + userInformation.getEmail() + '/' + userInformation.getPassword() + '/' +userInformation.getEmail(), Toast.LENGTH_SHORT).show();

                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            RegisterActivity.this.startActivity(loginIntent);

                        } else {
                            Log.e("갈래말래서버","네트워크 상태 : " + response.code() + "");
                            Log.e("갈래말래서버","메시지 : " + response.message() + "");
                            Log.e("갈래말래서버","에러바디 : " + response.errorBody() + "");
                            Toast.makeText(getApplicationContext(), "서버에 전달 하지 못 했습니다: " + userInformation.getEmail() + '/' + userInformation.getPassword() + '/' +userInformation.getEmail(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInformation> call, Throwable t) {
                        Log.e("갈래말래서버","서버 통신 중 에러 발생");
                        Toast.makeText(getApplicationContext(), "서버와 통신중 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}