package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText emailEditText;
    Button resetButton;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        AnhXa();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        Toolbar mToolbar= (Toolbar) findViewById(R.id.btnBack);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setNavigationOnClickListener(view -> onBackPressed());
            }
        });
    }
    private void AnhXa(){
        emailEditText = (EditText) findViewById(R.id.et_email);
        resetButton = (Button) findViewById(R.id.button_send);

        auth = FirebaseAuth.getInstance();
    }
    private void resetPassword(){
        String email = emailEditText.getText().toString();

        if (email.isEmpty()){
            emailEditText.setError("Vui lòng điền email!");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email không hợp lệ!");
            emailEditText.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Kiểm tra mail của bạn để reset mật khẩu", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(ForgotPasswordActivity.this, "Lỗi! Vui lòng thử lại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}