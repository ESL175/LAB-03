package com.cst2335.lab3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_3.R;

public class ProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private ImageButton imageButton;
    private ImageView imageView;
    private TextView textView;

    private ActivityResultLauncher<Intent> myPictureTakerLauncher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        imageButton = findViewById(R.id.imageButton);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        // Get the email passed from MainActivity
        String email = getIntent().getStringExtra("EMAIL");
        emailEditText.setText(email);

        // Handle taking a picture
        myPictureTakerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imageView.setImageBitmap(bitmap);
                    }
                });

        imageButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            myPictureTakerLauncher.launch(takePictureIntent);
        });
    }
}
