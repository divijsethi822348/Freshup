package com.example.freshup.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.SharedPrefrences.Login_Logout;
import com.example.freshup.Models.GetProfilePojo;
import com.example.freshup.SharedPrefrences.Picture_Path;
import com.example.freshup.R;
import com.example.freshup.Util.App;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.UserRegisterViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Profile extends AppCompatActivity {
    ImageView profileedit,profilepic;
    CircleImageView profile;
    RelativeLayout logout;
    EditText name,email,phone;
    TextView profilesave;
    String picturePath="",picturePath1="";
    String path="";
    private MultipartBody.Part Image;
   private UserRegisterViewModel viewModel;
   Boolean Changed;
   String phoneold="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        viewModel= ViewModelProviders.of(this).get(UserRegisterViewModel.class);
        logout=findViewById(R.id.logout_button);
        name=findViewById(R.id.name_et);
        profile=findViewById(R.id.profile_image);
        email=findViewById(R.id.emal_et);
        phone=findViewById(R.id.number_et);
        profileedit=findViewById(R.id.profile_edit);
        profilesave=findViewById(R.id.profile_save);
        profilepic=findViewById(R.id.change_pic);
        profilepic.setEnabled(false);
        getProfile();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.showProgress(Profile.this);
                Common.Logout(Profile.this);
                Login_Logout.Logout(Profile.this);
                App.getAppPreference().Logout(Profile.this);
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                CommonUtils.dismiss();
            }
        });
        profileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneold=phone.getText().toString();
                name.setEnabled(true);
                phone.setEnabled(true);
                profilesave.setVisibility(View.VISIBLE);
                profileedit.setVisibility(View.GONE);
                profilepic.setEnabled(true);
                logout.setEnabled(false);
            }
        });
        profilesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                logout.setEnabled(true);


            }
        });
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Working", Toast.LENGTH_SHORT).show();
                changePic();
            }
        });

        if (ActivityCompat.checkSelfPermission(Profile.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(Profile.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(Profile.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Profile.this,new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);

        }
        else{
            Toast.makeText(this, "Everything On", Toast.LENGTH_SHORT).show();
        }



    }

    private void updateProfile() {
        CommonUtils.showProgress(Profile.this);

        if (path!=""){
            final File file=new File(path);
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
             Image=MultipartBody.
                    Part.createFormData("image",file.getName(),requestBody);
        }else {
            final File file=new File(Picture_Path.GetToken(Profile.this,"path"));
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
            Image=MultipartBody.
                    Part.createFormData("image",file.getName(),requestBody);
        }

        Picture_Path.SaveToken(Profile.this,"path",path);
        String check_phone=phone.getText().toString();
        String check_name=name.getText().toString();

        if (phoneold.equalsIgnoreCase(check_phone)){
            Changed=false;
        }
        else {
            Changed=true;
        }

            RequestBody userId=RequestBody.create(MediaType.parse("text/plain"),Common.GetToken(Profile.this,"ID"));

            RequestBody Name=RequestBody.create(MediaType.parse("text/plain"),check_name);
            RequestBody Phone=RequestBody.create(MediaType.parse("text/plain"),check_phone);


            viewModel.updateProfile(Profile.this,userId,Name,Phone,Image).observe(Profile.this, new Observer<GetProfilePojo>() {
                @Override
                public void onChanged(@Nullable GetProfilePojo getProfilePojo) {
                    name.setEnabled(false);
                    phone.setEnabled(false);
                    profileedit.setVisibility(View.VISIBLE);
                    profilesave.setVisibility(View.GONE);
                    profilepic.setEnabled(false);
                    CommonUtils.dismiss();
                    Toast.makeText(Profile.this, "Saved", Toast.LENGTH_SHORT).show();
                    picturePath="";
                    picturePath1="";
                if (Changed==true){
                    Toast.makeText(Profile.this, "Verify your number again", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Profile.this, "Otp is : "+getProfilePojo.getDetails().getOtp(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Profile.this,OtpVerification.class);
                    startActivity(intent);
                }
                }
            });

    }

    private void getProfile() {
        CommonUtils.showProgress(Profile.this);
        String id= Common.GetToken(this,"ID");

        viewModel.getProfile(Profile.this,id).observe(Profile.this, new Observer<GetProfilePojo>() {
            @Override
            public void onChanged(@Nullable GetProfilePojo getProfilePojo) {
                name.setText(getProfilePojo.getDetails().getName());
                email.setText(getProfilePojo.getDetails().getEmail());
                phone.setText(getProfilePojo.getDetails().getPhone());

                if (getProfilePojo.getDetails().getImage().isEmpty()){
                    CommonUtils.dismiss();

                    Toast.makeText(Profile.this, "Add your image", Toast.LENGTH_SHORT).show();
                }
                else{
                    Picasso.with(Profile.this).load(getProfilePojo.getDetails().getImage()).into(profile);
                    CommonUtils.dismiss();
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                boolean camera=grantResults[0]==PackageManager.PERMISSION_GRANTED;

                if (grantResults.length>0 && camera){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else if (Build.VERSION.SDK_INT>23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Permissions");
                    builder.setMessage("Permissions are requeired");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Go to the setting for granting permissions", Toast.LENGTH_SHORT).show();
                            boolean sentToSettings = true;
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", Profile.this.getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);

                        }
                    })
                            .create()
                            .show();

                }
                else {
                    Toast.makeText(Profile.this, "Permission Denied", Toast.LENGTH_SHORT).show();

                }
                break;


        }
    }


    private void changePic() {
        checkPermision();
        PopupMenu popupMenu=new PopupMenu(Profile.this,profilepic);
        popupMenu.getMenuInflater().inflate(R.menu.camera_intent_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.take_pic:
                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,10);
                        break;

                    case R.id.select_pic:
                        Intent intent2=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent2, 11);
                        break;


                }
                return true;
            }
        });

        popupMenu.show();

    }



    private void checkPermision() {
        if (ActivityCompat.checkSelfPermission(Profile.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(Profile.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(Profile.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Profile.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);

        }
        else{
            Toast.makeText(this, "Everything On", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && data != null) {

            switch (requestCode) {
                case 10:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                    imageView.setImageBitmap(bitmap);

                    Uri image = data.getData();

                    String[] filePathColumn1 = {MediaStore.Images.Media.DATA};


                    Cursor cursor1 = managedQuery(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            filePathColumn1, null, null, null);
                    int column_index_data = cursor1
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor1.moveToLast();
                    path = cursor1.getString(column_index_data);





                    break;
                case 11:
                    Uri selectedImage = data.getData();
                    ImageView imageView2 = (ImageView) findViewById(R.id.profile_image);
                    imageView2.setImageURI(selectedImage);

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    path = cursor.getString(columnIndex);
                    cursor.close();

                    break;
            }
        }
    }

}
