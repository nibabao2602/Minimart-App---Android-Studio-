package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter.AsiaFoodAdapter;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.AsiaFood;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.PopularFood;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.UserModel;

public class Profile extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference userProfile;
    EditText name, email, phone, dob;
    Button update;
    ImageView avatar, capture;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AnhXa();

        getUserProfile();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }

    private void uploadImage() {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(800, 800)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        avatar.setImageURI(uri);
    }

    private void updateUserProfile() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name.getText().toString())
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Cập nhật Profile thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "Cập nhật Profile thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void getUserProfile(){

        if (user != null) {

            name.setText(user.getDisplayName());
            email.setText(user.getEmail());
//            Uri photoUrl = user.getPhotoUrl();
            if (user.getPhotoUrl() != null){
                Glide.with(Profile.this).load(user.getPhotoUrl()).into(avatar);
            }else {
                avatar.setImageResource(R.drawable.dummy_image);
            }

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();
            phone.setText(user.getPhoneNumber());

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
    private void AnhXa() {
        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);
        phone = (EditText) findViewById(R.id.txtPhone);
        dob = (EditText) findViewById(R.id.txtDOB);
        avatar = (ImageView) findViewById(R.id.avatar);
        update = (Button) findViewById(R.id.update);
        capture = (ImageView) findViewById(R.id.btnCapture);

    }
}