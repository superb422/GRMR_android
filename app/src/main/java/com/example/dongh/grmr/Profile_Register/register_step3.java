package com.example.dongh.grmr.Profile_Register;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dongh.grmr.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class register_step3 extends AppCompatActivity implements View.OnClickListener {

    ImageView back ,photo1, photo2, photo3, photo4;
    Button nxt_btn;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_ALBUM = 2;
    private static final int CROP_FROM_CAMERA = 3;

    private Uri photoUri;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    private static final int MULTIPLE_PERMISSIONS = 101;
    boolean picture_sub_lock0=true,picture_sub_lock1=true , picture_sub_lock2=true;
    int picture_index = 1; // lock 풀기위한 index
    //int n_index=1; // 필수 사진에서 부터 사진 추가되도록..하기 위한 조건변수
    private String mCurrentPhotoPath;
    public AlertDialog.Builder builder;
    String items[] = {"사진 찍기", "앨범에서 가져오기"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);

        checkPermissions();
        initView();

        back = (ImageView) findViewById(R.id.step3_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step3back = new Intent(register_step3.this, register_step2.class);
                step3back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step3back);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });

        nxt_btn = (Button) findViewById(R.id.step3_btn);
        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!picture_sub_lock1) {
                    Intent step3intent = new Intent(register_step3.this, register_step4.class);
                    step3intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(step3intent);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);  // 오른쪽 화면이 들어오면서 왼쪽화면 아웃
                    finish();
                }
            }
        });

    }

    private void initView() {
        photo1 = (ImageView) findViewById(R.id.profile_photo1);
        photo2 = (ImageView) findViewById(R.id.profile_photo2);
        photo3 = (ImageView) findViewById(R.id.profile_photo3);
        photo4 = (ImageView) findViewById(R.id.profile_photo4);

        photo1.setOnClickListener(this);
        photo2.setOnClickListener(this);
        photo3.setOnClickListener(this);
        photo4.setOnClickListener(this);
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

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(register_step3.this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (photoFile != null) {
            photoUri = FileProvider.getUriForFile(register_step3.this,
                    "com.example.dongh.grmr.provider", photoFile);
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

        return image;
    }

    private void goToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    /* 대표 -> 필수 -> 대표 -> 3번째가 안됨*/
    @Override
    public void onClick(View v) {
        builder = new AlertDialog.Builder(register_step3.this);
        switch (v.getId()) {
            case R.id.profile_photo1:
                picture_index = 1;
                builder.setTitle("프로필 촬영")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
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
            case R.id.profile_photo2:
                if(!picture_sub_lock0) {
                    picture_index = 2;
                    builder.setTitle("프로필 촬영")
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            takePhoto();
                                            break;
                                        case 1:
                                            goToAlbum();
                                            break;
                                    }
                                }
                            }).show();
                }
                break;
            case R.id.profile_photo3:
                if(!picture_sub_lock1) {
                    picture_index = 3;
                    builder.setTitle("프로필 촬영")
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            takePhoto();
                                            break;
                                        case 1:
                                            goToAlbum();
                                            break;
                                    }
                                }
                            }).show();
                }
                break;
            case R.id.profile_photo4:
                if(!picture_sub_lock2) {
                    picture_index = 4;
                    builder.setTitle("프로필 촬영")
                            .setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
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
            MediaScannerConnection.scanFile(register_step3.this,
                    new String[]{photoUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } else if (requestCode == CROP_FROM_CAMERA) {
            try { //저는 bitmap 형태의 이미지로 가져오기 위해 아래와 같이 작업하였으며 Thumbnail을 추출하였습니다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);

                Bitmap thumbImage = ThumbnailUtils.extractThumbnail(bitmap, photo1.getWidth(), photo1.getHeight());
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                thumbImage.compress(Bitmap.CompressFormat.JPEG, 100, bs); //이미지가 클 경우 OutOfMemoryException 발생이 예상되어 압축

                //여기서는 ImageView에 setImageBitmap을 활용하여 해당 이미지에 그림을 띄우시면 됩니다.
                switch (picture_index){
                    case 1:
                        photo1.setImageBitmap(thumbImage);
                        photo1.setBackground(new ShapeDrawable(new OvalShape()));
                        photo1.setClipToOutline(true);
                        picture_sub_lock0=false;
                        break;
                    case 2:
                        photo2.setImageBitmap(thumbImage);
                        photo2.setBackground(new ShapeDrawable(new OvalShape()));
                        photo2.setClipToOutline(true);
                        picture_sub_lock1=false;
                        nxt_btn.setBackgroundResource(R.drawable.btn_skyblue);
                        nxt_btn.setTextColor(Color.rgb(255,255,255));
                        break;
                    case 3:
                        photo3.setImageBitmap(thumbImage);
                        photo3.setBackground(new ShapeDrawable(new OvalShape()));
                        photo3.setClipToOutline(true);
                        picture_sub_lock2=false;
                        break;
                    case 4:
                        photo4.setImageBitmap(thumbImage);
                        photo4.setBackground(new ShapeDrawable(new OvalShape()));
                        photo4.setClipToOutline(true);
                        break;
                }


            } catch (Exception e) {
                Log.e("ERROR", e.getMessage().toString());
            }
            /*imgMain.setImageURI(null);
            imgMain.setImageURI(photoUri);
            revokeUriPermission(photoUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);*/
        }
    }

    public void cropImage() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, 0);
        grantUriPermission(list.get(0).activityInfo.packageName, photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        int size = list.size();
        if (size == 0) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "용량이 큰 사진의 경우 시간이 오래 걸릴 수 있습니다.", Toast.LENGTH_SHORT).show();
            intent.putExtra("crop", "true");
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
            photoUri = FileProvider.getUriForFile(register_step3.this,
                    "com.example.dongh.grmr.provider", tempFile);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

            Intent i = new Intent(intent);
            ResolveInfo res = list.get(0);
            grantUriPermission(res.activityInfo.packageName, photoUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            startActivityForResult(i, CROP_FROM_CAMERA);

        }
    }

    @Override
    public void onBackPressed() {
        Intent step3back = new Intent(register_step3.this, register_step2.class);
        step3back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step3back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}


