package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    // catch Element
    TextView signIn, signUp, forgotPass;
    TextInputEditText username, password;
    DBHelper DB;
    CheckBox isRemember;
    FirebaseUser user;
    SharedPreferences sharedPreferences;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this,R.color.white);

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }

        AnhXa();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });
//        username.setText(sharedPreferences.getString("username", ""));
//        password.setText(sharedPreferences.getString("password", ""));
//        isRemember.setChecked(sharedPreferences.getBoolean("checked", false));
//
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//
//                if (user.equals("") || pass.equals("")) {
//                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ các thông tin được yêu cầu", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
//                    if (checkuserpass == true) {
//                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        if (isRemember.isChecked()) {
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("username", user);
//                            editor.putString("password", pass);
//                            editor.putBoolean("checked", true);
//                            editor.commit();
//                        }else{
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.remove("username");
//                            editor.remove("password");
//                            editor.remove("checked");
//                            editor.commit();
//                        }
//                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
    private void AnhXa(){
        signIn = (TextView)findViewById(R.id.btnSignIn);
        signUp = (TextView)findViewById(R.id.btnSignUp);
        forgotPass = (TextView)findViewById(R.id.btnForgotPass);
        isRemember = (CheckBox) findViewById(R.id.ckbRemember);
        username = (TextInputEditText) findViewById(R.id.txtEmail);
        password = (TextInputEditText) findViewById(R.id.txtPassword);
        DB = new DBHelper(this);
    }

    private  void DangNhap() {
        String email = username.getText().toString();
        String pass = password.getText().toString();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}