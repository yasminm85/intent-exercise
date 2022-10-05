package id.ac.polinema.intentexercise;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    TextView label_About, label_full, label_email,label_Homepage;
    Button btnHomepage;
    CircleImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        label_About = findViewById(R.id.label_about);
        label_full = findViewById(R.id.label_fullname);
        label_email = findViewById(R.id.label_email);
        label_Homepage = findViewById(R.id.label_homepage);
        btnHomepage = findViewById(R.id.button_homepage);
        image = findViewById(R.id.image_profile);

        final String defaultLink = getIntent().getExtras().getString("KEY_HOMEPAGE");

        Bundle extras = getIntent().getExtras();
            label_full.setText(extras.getString("KEY_FULLNAME"));
            label_About.setText(extras.getString("KEY_ABOUT"));
            label_email.setText(extras.getString("KEY_EMAIL"));
            label_Homepage.setText(extras.getString("KEY_HOMEPAGE"));
            image.setImageURI(Uri.parse(extras.getString("KEY_IMAGE")));


        btnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(defaultLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }
}