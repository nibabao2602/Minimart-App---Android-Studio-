package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;

public class DoneBill extends AppCompatActivity {

    Button shopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_bill);

        MainActivity.CartList = new ArrayList<CartModel>();
        shopping = findViewById(R.id.continueShopping);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneBill.this, Dashboard.class);
            }
        });
    }
}