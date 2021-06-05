package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.listener.ICartLoadListener;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.listener.IDrinkLoadListener;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.DrinkModel;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.utils.SpaceItemDecoration;

public class test extends AppCompatActivity{
    @BindView(R.id.recycler_drink)
    RecyclerView recyclerDrink;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.notification)
    ImageView notification;
    @BindView(R.id.cart)
    ImageView cart;

    IDrinkLoadListener drinkLoadListener;
    ICartLoadListener cartLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        init();
//        LoadDrinkFromFirebase();
    }

//    private void LoadDrinkFromFirebase() {
//        List<DrinkModel> drinkModels = new ArrayList<>();
//        FirebaseDatabase.getInstance()
//                .getReference("Drink")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()){
//                            for (DataSnapshot drinkSnapshot:snapshot.getChildren()){
//                                DrinkModel drinkModel = drinkSnapshot.getValue(DrinkModel.class);
//                                drinkModel.setKey(drinkSnapshot.getKey());
//                                drinkModels.add(drinkModel);
//                            }
//                            drinkLoadListener.onDrinkLoadFailed("Can't find");
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        drinkLoadListener.onDrinkLoadFailed(error.getMessage());
//                    }
//                });
//    }

//    private void init() {
//        ButterKnife.bind(this);
//
//        drinkLoadListener = this;
//        cartLoadListener = this;
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        recyclerDrink.setLayoutManager(gridLayoutManager);
//        recyclerDrink.addItemDecoration(new SpaceItemDecoration());
//    }
//
//    @Override
//    public void onDrinkLoadSuccess(List<DrinkModel> drinkModelList) {
//        MyDrinkAdapter adapter = new MyDrinkAdapter(this, drinkModelList);
//        recyclerDrink.setAdapter(adapter);
//    }
//
//    @Override
//    public void onDrinkLoadFailed(String message) {
//        Snackbar.make(drawerLayout, message, Snackbar.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onCartSuccess(List<CartModel> cartModelList) {
//
//    }
//
//    @Override
//    public void onCartLoadFailed(String message) {
//
//    }
}