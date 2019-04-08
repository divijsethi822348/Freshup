package com.example.freshup.Activities;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.R;

public class Profile extends AppCompatActivity {
    ImageView profileedit,profilepic;
    RelativeLayout logout;
    EditText name,email,phone;
    TextView profilesave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=findViewById(R.id.logout_button);
        name=findViewById(R.id.name_et);
        email=findViewById(R.id.emal_et);
        phone=findViewById(R.id.number_et);
        profileedit=findViewById(R.id.profile_edit);
        profilesave=findViewById(R.id.profile_save);
        profilepic=findViewById(R.id.change_pic);
        profilepic.setEnabled(false);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        profileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                email.setEnabled(true);
                phone.setEnabled(true);
                profilesave.setVisibility(View.VISIBLE);
                profileedit.setVisibility(View.GONE);
                profilepic.setEnabled(true);
            }
        });
        profilesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(false);
                email.setEnabled(false);
                phone.setEnabled(false);
                profileedit.setVisibility(View.VISIBLE);
                profilesave.setVisibility(View.GONE);
                profilepic.setEnabled(false);
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






                    break;
                case 11:
                    Uri selectedImage = data.getData();
                    ImageView imageView2 = (ImageView) findViewById(R.id.profile_image);
                    imageView2.setImageURI(selectedImage);

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    cursor.close();


                    break;
            }
        }
    }
}
