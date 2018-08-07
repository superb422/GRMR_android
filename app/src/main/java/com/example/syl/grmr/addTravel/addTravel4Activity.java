package com.example.syl.grmr.addTravel;

import android.Manifest;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.grmr.Constructor.addTravelContributor;
import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.R;
import com.example.syl.grmr.service.ServerService;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class addTravel4Activity extends AppCompatActivity {


    private static final int PICK_FROM_FILE = 100;
    private static String base_URL = "http://52.197.13.138:80";
    private static String travelTitle = "서버테스트title";
    private static String destination = addTravelActivity.cityText.getText().toString();
    private static String startDate = addTravel2Activity.departureDate.getText().toString();
    private static String endDate = addTravel2_1Activity.homeComingDate.getText().toString();
    private static String fileName;


    private static Button picButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel4);

        verifyStoragePermissions(this);



        /*******************************PictureButton********************************************/

        picButton = (Button) findViewById(R.id.pictureButton);
        //next page
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent chooser = Intent.createChooser(intent,"이미지 등록");

                startActivityForResult(chooser, PICK_FROM_FILE);


            }

        });





        /********************************Bring Text Info*******************************/
        //Bring the City Name
        TextView cityText = (TextView) findViewById(R.id.cityText);
        cityText.setText(destination);

        //Bring the departure date
        final TextView departureText = (TextView) findViewById(R.id.departureText);
        departureText.setText(startDate);


        final TextView  homecomingText = (TextView) findViewById(R.id.homeComingText);
        homecomingText.setText(endDate);



        /********************************click next button*******************************/
        /*
        addTravel5Activity가 아닌 다시 mainActivity로 넘어가야함.
         */
        Button nextButton = findViewById(R.id.nextButton);

        //next page
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(addTravel4Activity.this, MainActivity.class);
                addTravel4Activity.this.startActivity(mainIntent);
            }

        });


        /********************************click previous button*******************************/
        Button previousButton = (Button) findViewById(R.id.previousButton);

        //Click previousButton
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTravel3Intent = new Intent(addTravel4Activity.this, MainActivity.class);
                addTravel4Activity.this.startActivity(addTravel3Intent);
            }
        });


        /********************************click close button*******************************/
        ImageButton closeButton = (ImageButton) findViewById(R.id.closeButton);
        //Click closeButton
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(addTravel4Activity.this, MainActivity.class);
                addTravel4Activity.this.startActivity(mainIntent);
            }
        });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_FROM_FILE){
            if(resultCode == RESULT_OK){
                Uri selectImage = data.getData();
                uploadTravelInfo(travelTitle, selectImage, destination, startDate, endDate);
            }
        }
    }


    /******************************이미지uri경로 가져오는 method******************************************/
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();



        System.out.print("conflic 확인;;;;;;;;00");
        return result;
    }

    /******************************이미지uri경로 가져오는 method******************************************/
    private String getRealPathFromULLL(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();



        System.out.print("conflic 확인;;;;;;;;00");
        return result;
    }


    private File createFileFromBitmap(Bitmap bitmap) throws IOException {
        File newFile = new File(getFilesDir(), fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        fileOutputStream.close();
        return newFile;
    }






    /**********************************서버 통신**************************************/
    private void uploadTravelInfo(final String Title, final Uri imagePath,final String Destination,final String StartDate,final String EndDate) {

        System.out.println("서버통신 들어옴 ==========================");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerService service = retrofit.create(ServerService.class);



        /*************************title,imagefile,destination,startDate,endDate****************************************/
        try{

            System.out.println("서버통신 try 들어옴 ==========================");

            File image = null;
            try {
                image = createFileFromBitmap(getBitmapFromUri(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }


            File file = new File(getRealPathFromURI(imagePath));

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), image);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("image", file.getName(), requestFile);



           // MultipartBody.Part imageFile = prepareFilePart("image", imagePath);

            /*
            RequestBody title = createPartFromString(Title);
            RequestBody destination = createPartFromString(Destination);
            RequestBody startDate = createPartFromString(StartDate);
            RequestBody endDate = createPartFromString(EndDate);
*/
            Call<ResponseBody> call = service.uploadTravel(Title, Destination,body,StartDate,EndDate);
           //tv_message.setText(call.request().url().toString()); //todo 디버깅용

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    addTravelContributor contributor = new addTravelContributor(Title, getBitmapFromUri(imagePath),Destination, StartDate,EndDate );

                    Log.e("갈래말래클라이언트","서버에 데이터를 전달했습니다 : " + contributor.getDestination());
                    Toast.makeText(getApplicationContext(), "서버에 값을 전달했습니다 : " + contributor.getDestination(), Toast.LENGTH_SHORT).show();

                    if (response.isSuccessful()) {
                        System.out.println(" ===============서버통신 성공==========================");
                        Log.e("갈래말래서버", "Networking Success : " + response.code() + "");
                        Log.e("갈래말래서버", "여행등록 데이터" + new Gson().toJson(response.body()));
                    }
                    else
                        Log.e("갈래말래","Networking unSuccess : " + new Gson().toJson(response.body()));
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                }
            });



        }
        catch (Exception e){
            Log.d("TEST", e.getMessage());
        }
    }





    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    private Bitmap getBitmapFromUri(Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bitmap;
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));

        RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        fileName = file.getName();
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }







    /*
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    */


    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // save file

            } else {
                Toast.makeText(getApplicationContext(), "PERMISSION_DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }








}
