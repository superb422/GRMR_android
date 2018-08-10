package com.example.syl.grmr.Setup;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    /*사진 찍기 변수*/
    private ImageView picture1,picture2,picture3,picture4,picture5;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_ALBUM = 2;
    private static final int CROP_FROM_CAMERA = 3;

    private Uri photoUri;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    private static final int MULTIPLE_PERMISSIONS = 101;

    private String mCurrentPhotoPath;
    public AlertDialog.Builder builder;
    String items[] = { "사진 찍기", "앨범에서 가져오기" };
    int picture_index=1;

    /*여행 스타일 변수*/
    LinearLayout linearLayout_style,linearLayout_char,linearLayout_interest,linearLayout_pr;
    Button prev_btn,next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*사진 찍기 메인 메소드*/
        checkPermissions();
        initView();

        /*여행 스타일 레이아웃*/
        linearLayout_style = (LinearLayout)findViewById(R.id.p_style);
        linearLayout_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent styleintent = new Intent(Profile.this,StyleActivity.class);
                styleintent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(styleintent);
            }
        });

        /*성격 레이아웃*/
        linearLayout_char = (LinearLayout)findViewById(R.id.p_char);
        linearLayout_char.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent charintent = new Intent(Profile.this,CharActivity.class);
                charintent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(charintent);
            }
        });

        /*관심사 레이아웃*/
        linearLayout_interest = (LinearLayout)findViewById(R.id.p_interest);
        linearLayout_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent interestintent = new Intent(Profile.this,InterestActivity.class);
                interestintent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(interestintent);
            }
        });

        /*자기소개 레이아웃*/
        linearLayout_pr = (LinearLayout)findViewById(R.id.p_pr);
        linearLayout_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent printent = new Intent(Profile.this,PrActivity.class);
                printent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(printent);
            }
        });

        prev_btn = (Button)findViewById(R.id.Profile_prev);
        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ProfileIntent = new Intent(Profile.this, MainActivity.class);
                startActivity(ProfileIntent);
            }
        });
    }


    private boolean checkPermissions() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for (String pm : permissions) {
            result = ContextCompat.checkSelfPermission(this, pm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void initView() {
        picture1 = (ImageView) findViewById(R.id.add_picture1);
        picture2 = (ImageView) findViewById(R.id.add_picture2);
        picture3 = (ImageView) findViewById(R.id.add_picture3);
        picture4 = (ImageView) findViewById(R.id.add_picture4);
        picture5 = (ImageView) findViewById(R.id.add_picture5);
        //btnCamera = (Button) findViewById(R.id.btn_camera);
        //btnAlbum = (Button) findViewById(R.id.btn_album);

        picture1.setOnClickListener(this);
        picture2.setOnClickListener(this);
        picture3.setOnClickListener(this);
        picture4.setOnClickListener(this);
        picture5.setOnClickListener(this);
        //btnCamera.setOnClickListener(this);
        //btnAlbum.setOnClickListener(this);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(Profile.this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (photoFile != null) {
            photoUri = FileProvider.getUriForFile(Profile.this,
                    "com.example.syl.grmr.provider", photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "nostest_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/NOSTest/");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void goToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    public void onClick(View v) {
        builder = new AlertDialog.Builder(Profile.this);
        switch (v.getId()) {
            case R.id.add_picture1:
                picture_index=1;
                builder.setTitle("프로필 촬영")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which)
                                {
                                    case 0:
                                        takePhoto();
                                        break;
                                    case 1:
                                        goToAlbum();
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.add_picture2:
                picture_index=2;
                builder.setTitle("프로필 촬영")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which)
                                {
                                    case 0:
                                        takePhoto();
                                        break;
                                    case 1:
                                        goToAlbum();
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.add_picture3:
                picture_index=3;
                builder.setTitle("프로필 촬영")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which)
                                {
                                    case 0:
                                        takePhoto();
                                        break;
                                    case 1:
                                        goToAlbum();
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.add_picture4:
                picture_index=4;
                builder.setTitle("프로필 촬영")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which)
                                {
                                    case 0:
                                        takePhoto();
                                        break;
                                    case 1:
                                        goToAlbum();
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.add_picture5:
                picture_index=5;
                builder.setTitle("프로필 촬영")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which)
                                {
                                    case 0:
                                        takePhoto();
                                        break;
                                    case 1:
                                        goToAlbum();
                                        break;
                                }
                            }
                        }).show();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        if (permissions[i].equals(this.permissions[0])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();
                            }
                        } else if (permissions[i].equals(this.permissions[1])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();

                            }
                        } else if (permissions[i].equals(this.permissions[2])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();

                            }
                        }
                    }
                } else {
                    showNoPermissionToastAndFinish();
                }
                return;
            }
        }
    }

    private void showNoPermissionToastAndFinish() {
        Toast.makeText(this, "권한 요청에 동의 해주셔야 이용 가능합니다. 설정에서 권한 허용 하시기 바랍니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (requestCode == PICK_FROM_ALBUM) {
            if (data == null) {
                return;
            }
            photoUri = data.getData();
            cropImage();
        } else if (requestCode == PICK_FROM_CAMERA) {
            cropImage();
            // 갤러리에 나타나게
            MediaScannerConnection.scanFile(Profile.this,
                    new String[]{photoUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } else if (requestCode == CROP_FROM_CAMERA) {
            if (picture_index == 1) {
                picture1.setImageURI(null);
                picture1.setImageURI(photoUri);
            } else if (picture_index == 2) {
                picture2.setImageURI(null);
                picture2.setImageURI(photoUri);
            } else if (picture_index == 3) {
                picture3.setImageURI(null);
                picture3.setImageURI(photoUri);
            } else if (picture_index == 4) {
                picture4.setImageURI(null);
                picture4.setImageURI(photoUri);
            } else {
                picture5.setImageURI(null);
                picture5.setImageURI(photoUri);
            }
        }
    }
    //tes
    //Android N crop image
    public void cropImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.grantUriPermission("com.android.camera", photoUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");

        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            grantUriPermission(list.get(0).activityInfo.packageName, photoUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        int size = list.size();
        if (size == 0) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "용량이 큰 사진의 경우 시간이 오래 걸릴 수 있습니다.", Toast.LENGTH_SHORT).show();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            }


            //picture1.getLayoutParams().height=LinearLayout.LayoutParams.MATCH_PARENT;
            intent.putExtra("crop", "true");
            intent.putExtra("outputX", 90);
            intent.putExtra("outputY", 90);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);


            File croppedFileName = null;
            try {
                croppedFileName = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            File folder = new File(Environment.getExternalStorageDirectory() + "/NOSTest/");
            File tempFile = new File(folder.toString(), croppedFileName.getName());

            photoUri = FileProvider.getUriForFile(Profile.this,
                    "com.example.syl.grmr.provider", tempFile);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }

            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

            Intent i = new Intent(intent);
            ResolveInfo res = list.get(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                grantUriPermission(res.activityInfo.packageName, photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            startActivityForResult(i, CROP_FROM_CAMERA);
        }
    }

}
