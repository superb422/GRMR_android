package com.example.syl.grmr.Login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.syl.grmr.Constructor.GM_Users;
import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.R;
import com.example.syl.grmr.service.ServerService;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Login2Activity extends AppCompatActivity {

    private static String base_URL = "http://52.197.13.138:80";
    private String email = null;
    private String password = null;

    FrameLayout frameLayout;
    Fragment fragment1,fragment2,fragment3,fragment4;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

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

        fragment1 = new pw_find1();
        fragment2 = new pw_find2();
        fragment3 = new pw_find3();
        fragment4 = new pw_find4();

        onFragmentChange(0);
/*
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);


        Button loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailText.getText().toString();
                password = passwordText.getText().toString();

                Intent mainIntent = new Intent(Login2Activity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Login2Activity.this.startActivity(mainIntent);

                final Call<List<GM_Users>> gmUsersCall = service.userList(email, password);
                gmUsersCall.enqueue(new Callback<List<GM_Users>>() {
                    @Override
                    public void onResponse(Call<List<GM_Users>> call, Response<List<GM_Users>> response) {

                        GM_Users gmUsers = new GM_Users(email, password);

                        if(response.isSuccessful()){
                            Log.e("갈래말래서버","네트워크 상태 : " + response.code() + "");
                            Log.e("갈래말래서버","헤더 : " + response.headers() + "");
                            Log.e("갈래말래서버","메시지 : " + response.message() + "");
                            Log.e("갈래말래서버","바디 : " + response.body() + "");
                            Toast.makeText(getApplicationContext(), "서버와 통신되었습니다." , Toast.LENGTH_SHORT).show();

                            Intent mainIntent = new Intent(Login2Activity.this, MainActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Login2Activity.this.startActivity(mainIntent);

                        } else {
                            Log.e("갈래말래서버","네트워크 상태 : " + response.code() + "");
                            Log.e("갈래말래서버","헤더 : " + response.headers() + "");
                            Log.e("갈래말래서버","메시지 : " + response.message() + "");
                            Log.e("갈래말래서버","에러바디 : " + new Gson().toJson(response.errorBody()) + "");
                            Toast.makeText(getApplicationContext(), "서버와 통신이 되지 않습니다." , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GM_Users>> call, Throwable t) {
                        Log.e("갈래말래서버","서버 통신 중 에러 발생");
                        Toast.makeText(getApplicationContext(), "서버와 통신중 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                final Call<GM_Users> gmUsersCall = service.userList("email", "password");
                gmUsersCall.enqueue(new Callback<GM_Users>() {
                    @Override
                    public void onResponse(Call<GM_Users> call, Response<GM_Users> response) {

                        GM_Users gmUsers = new GM_Users(email, password);

                        if(response.isSuccessful()){
                            Log.e("갈래말래서버","네트워크 상태 : " + new Gson().toJson(response.code()) + "");
                            Log.e("갈래말래서버","메시지 : " + new Gson().toJson(response.message())+ "");
                            Log.e("갈래말래서버","에러바디 : " + new Gson().toJson(response.body()) + "");
                            Toast.makeText(getApplicationContext(), "서버와 통신되었습니다." , Toast.LENGTH_SHORT).show();

                            Intent mainIntent = new Intent(Login2Activity.this, MainActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Login2Activity.this.startActivity(mainIntent);

                        } else {
                            Log.e("갈래말래서버","네트워크 상태 : " + new Gson().toJson(response.code()) + "");
                            Log.e("갈래말래서버","메시지 : " + new Gson().toJson(response.message())+ "");
                            Log.e("갈래말래서버","에러바디 : " + new Gson().toJson(response.errorBody()) + "");
                            Toast.makeText(getApplicationContext(), "서버와 통신이 되지 않습니다." , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GM_Users> call, Throwable t) {
                        Log.e("갈래말래서버","서버 통신 중 에러 발생");
                        Toast.makeText(getApplicationContext(), "서버와 통신중 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                });

             /*
                Intent mainIntent = new Intent(Login2Activity.this, MainActivity.class);
                //Intent mainIntent = new Intent(Login2Activity.this, MainActivity2.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Login2Activity.this.startActivity(mainIntent);
                //Login2Activity.this.startActivity(mainIntent);

            }
        });*/
    }

    public void onFragmentChange(int index){
        if(index ==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment1).commit();
        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment2).commit();
        }else if(index==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment3).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment4).commit();
        }
    }

}
