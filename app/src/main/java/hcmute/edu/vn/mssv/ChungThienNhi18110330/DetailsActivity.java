package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;

public class DetailsActivity extends AppCompatActivity {

    String name, image, rating, key;
    Integer amount=1;
    Integer price;
    ImageView foodImage, up, down, addCart;
    TextView itemName, itemPrice, itemRating, itemAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        AnhXa();
        turnBack();
        chooseAmount();
        addToCart();

        BindingData();
    }

    private void addToCart() {
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.CartList.size() >0){
                    int sl = amount;
                    boolean exists = false;
                    for (int i=0; i<MainActivity.CartList.size(); i++){
                        if (MainActivity.CartList.get(i).getKey().equals(key)){

                            MainActivity.CartList.get(i).setQuantity(MainActivity.CartList.get(i).getQuantity() + sl);
                            if (MainActivity.CartList.get(i).getQuantity() >= 10){
                                MainActivity.CartList.get(i).setQuantity(10);
                            }
                            MainActivity.CartList.get(i).setTotalPrice(price * MainActivity.CartList.get(i).getQuantity());
                            exists = true;
                            break;
                        }
                    }
                    if (exists == false){
                        int quantity = amount;
                        int totalPrice = quantity * price;
                        MainActivity.CartList.add(new CartModel(key, name, image, price, quantity, totalPrice));
                    }
                }else {
                    int quantity = amount;
                    int totalPrice = quantity * price;
                    MainActivity.CartList.add(new CartModel(key, name, image, price, quantity, totalPrice));
                }
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });
    }

    private void chooseAmount() {

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount ++;
                itemAmount.setText(amount.toString());
                if(amount >= 10){
                    up.setVisibility(View.INVISIBLE);
                    down.setVisibility(View.VISIBLE);
                }else if(amount <= 1){
                    up.setVisibility(View.VISIBLE);
                    down.setVisibility(View.INVISIBLE);
                } else if (amount >= 1){
                    up.setVisibility(View.VISIBLE);
                    down.setVisibility(View.VISIBLE);
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount --;
                itemAmount.setText(amount.toString());
                if(amount >= 10){
                    up.setVisibility(View.INVISIBLE);
                    down.setVisibility(View.VISIBLE);
                }else if(amount <= 1){
                    up.setVisibility(View.VISIBLE);
                    down.setVisibility(View.INVISIBLE);
                } else if (amount >= 1){
                    up.setVisibility(View.VISIBLE);
                    down.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void turnBack() {
        androidx.appcompat.widget.Toolbar mToolbar = (Toolbar) findViewById(R.id.btnBack);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setNavigationOnClickListener(view -> onBackPressed());
            }
        });
    }

    private void BindingData() {
        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = Integer.parseInt(intent.getStringExtra("price"));
        rating = intent.getStringExtra("rating");
        image = intent.getStringExtra("image");
        key = intent.getStringExtra("key");

        Glide.with(getApplicationContext()).load(image).into(foodImage);
        itemName.setText(name);
        itemPrice.setText(price+".000 VND");
        itemRating.setText(rating);
    }

    private void AnhXa() {
        foodImage = (ImageView) findViewById(R.id.food_image);
        addCart = (ImageView) findViewById(R.id.btnAddCart);
        up = (ImageView) findViewById(R.id.btnUp);
        down = (ImageView) findViewById(R.id.btnDown);
        itemAmount = (TextView) findViewById(R.id.txtAmount);
        itemName = (TextView) findViewById(R.id.txtName);
        itemPrice = (TextView) findViewById(R.id.txtPrice);
        itemRating = (TextView) findViewById(R.id.txtRating);

    }

}