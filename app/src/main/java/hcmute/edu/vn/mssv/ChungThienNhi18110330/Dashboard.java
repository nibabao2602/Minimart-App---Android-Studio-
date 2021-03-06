package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.jetbrains.annotations.NotNull;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter.AsiaFoodAdapter;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter.PopularFoodAdapter;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.AsiaFood;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference drinkReference, foodReference;
    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageView cart;

    NotificationBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        AnhXa();

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Cart.class);
                startActivity(intent);
            }
        });

        // now here we will add some dummy data to out model class

        ArrayList<PopularFood> popularFoodList = new ArrayList<>();

        drinkReference = FirebaseDatabase.getInstance().getReference().child("Drink");
        drinkReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                popularFoodList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    PopularFood popularFood = snapshot.getValue(PopularFood.class);
                    popularFoodList.add(popularFood);
                }
                PopularFoodAdapter adapter = new PopularFoodAdapter(Dashboard.this, popularFoodList);

                setPopularRecycler(popularFoodList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        List<AsiaFood> asiaFoodList = new ArrayList<>();
        foodReference = FirebaseDatabase.getInstance().getReference().child("Food");
        foodReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                asiaFoodList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AsiaFood asiaFood = snapshot.getValue(AsiaFood.class);
                    asiaFoodList.add(asiaFood);
                }
                AsiaFoodAdapter adapter = new AsiaFoodAdapter(Dashboard.this, asiaFoodList);

                setAsiaRecycler(asiaFoodList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<AsiaFood> asiaFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this, asiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }

    private void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.menu);

        badge = findViewById(R.id.badge);
        badge.setNumber(MainActivity.CartList.size());
        cart = findViewById(R.id.cart);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

    }
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.home:
                break;
            case R.id.profile:
                Intent intent = new Intent(Dashboard.this, Profile.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}