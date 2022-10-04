package id.ac.polinema.intentexercise;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.IOException;



public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    ImageView avatarImage;

    Button btnOk;
    EditText text_fullName, text_Email, text_Password, text_Homepage, text_About, text_confirm;

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        avatarImage = (ImageView)findViewById(R.id.image_profile);
        btnOk = findViewById(R.id.button_ok);

        text_fullName = findViewById(R.id.text_fullname);
        text_Email = findViewById(R.id.text_email);
        text_Password = findViewById(R.id.text_password);
        text_confirm = findViewById(R.id.text_confirm_password);
        text_Homepage = findViewById(R.id.text_homepage);
        text_About = findViewById(R.id.text_about);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(text_Password.getText().toString().equals("")){

                    AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
                    alert.setTitle("Error");
                    alert.setMessage("Sorry, please enter your password");
                    alert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                        }
                    });
                    alert.show();
                }

                else if(!text_confirm.getText().toString().equals(text_Password.getText().toString()) ){
                    AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
                    alert.setTitle("Error");
                    alert.setMessage("Sorry, your confirm password wrong");
                    alert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                        }
                    });
                    alert.show();
                }
                else {
                    String text_name = text_fullName.getText().toString();
                    String text_email = text_Email.getText().toString();
                    String pass = text_Password.getText().toString();
                    String text_home = text_Homepage.getText().toString();
                    String text_abt = text_About.getText().toString();
//                    avatarImage.setDrawingCacheEnabled(true);
//                    Bitmap bitmap=avatarImage.getDrawingCache();


                    Intent move = new Intent(RegisterActivity.this, ProfileActivity.class);


                    move.putExtra("KEY_FULLNAME", text_name);
                    move.putExtra("KEY_EMAIL", text_email);
                    move.putExtra("KEY_PASS", pass);
                    move.putExtra("KEY_HOMEPAGE", text_home);
                    move.putExtra("KEY_ABOUT", text_abt);
//                    move.putExtra("KEY_Bitmap", bitmap);
                    startActivity(move);


                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);

                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }


    }

}
//
