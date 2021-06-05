package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {

    ImageView btnCapture;
    CircleImageView avatar;
    Button btnSignup;
    DBHelper DB;
    TextInputEditText txtUsername, txtEmail, txtPhone;
    EditText txtPassword, txtConfirmPassword;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        AnhXa();

        //Request For Camera Permission
        if (ContextCompat.checkSelfPermission(SignupActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SignupActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
        }

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        androidx.appcompat.widget.Toolbar mToolbar = (Toolbar) findViewById(R.id.btnBack);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setNavigationOnClickListener(view -> onBackPressed());
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            // Get Capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            // Set Capture Image to ImageView
            avatar.setImageBitmap(captureImage);
        }
    }
    private void AnhXa() {
        btnCapture=(ImageView)findViewById(R.id.btnCapture);
        avatar =(CircleImageView) findViewById(R.id.avatar);
        DB = new DBHelper(this);
        btnSignup = (Button) findViewById(R.id.button_signup);
        txtUsername = (TextInputEditText) findViewById(R.id.et_username);
        txtPassword = (EditText) findViewById(R.id.et_password);
        txtEmail = (TextInputEditText) findViewById(R.id.et_email);
        txtPhone = (TextInputEditText) findViewById(R.id.et_phone);
        txtConfirmPassword = (EditText) findViewById(R.id.et_confirm_password);
    }
    private void DangKy() {
        String user = txtUsername.getText().toString();
        String pass = txtPassword.getText().toString();
        String confirmPass = txtConfirmPassword.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}