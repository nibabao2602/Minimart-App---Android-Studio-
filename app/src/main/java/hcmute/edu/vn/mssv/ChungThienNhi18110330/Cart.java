package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter.CartAdapter;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter.PopularFoodAdapter;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.PopularFood;

public class Cart extends AppCompatActivity {
    RecyclerView cartRecyclerList;
    CartAdapter cartAdapter;
    Button buy, shopping;
    public static TextView totalPrice;
    public static TextView billPrice;

    TextView ship;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
        setCartRecycler();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(cartRecyclerList);
        bindingData();
        
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuaSam();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, DoneBill.class);
                startActivity(intent);
            }
        });
    }

    public static void bindingData() {
        int sum=0;
        for (int i=0; i< MainActivity.CartList.size(); i++){
            sum+= MainActivity.CartList.get(i).getTotalPrice();
        }
        totalPrice.setText(String.valueOf(sum) + ".000 VND");
        billPrice.setText(String.valueOf(sum+15)+".000 VND");
    }

    private void MuaSam() {
        Intent intent = new Intent(Cart.this, Dashboard.class);
        startActivity(intent);
    }

    public void AnhXa() {
        buy = findViewById(R.id.btnBuy);
        shopping = findViewById(R.id.continueShopping);
        totalPrice = findViewById(R.id.totalPrice);
        billPrice = findViewById(R.id.billPrice);
    }

    private void setCartRecycler() {

        cartRecyclerList = findViewById(R.id.cart_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        cartRecyclerList.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(this, MainActivity.CartList);
        cartRecyclerList.setAdapter(cartAdapter);
    }
    ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            MainActivity.CartList.remove(viewHolder.getAdapterPosition());
            Toast.makeText(Cart.this, "Đã xóa", Toast.LENGTH_SHORT).show();
        }
    };
}