package hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.Cart;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.DetailsActivity;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.Dashboard;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.MainActivity;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.R;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.PopularFood;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>  {
    Context context;
    List<CartModel> cartModelList;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Glide.with(context)
                .load(cartModelList.get(position).getImage())
                .into(holder.foodImage);
        holder.name.setText(cartModelList.get(position).getName());
        holder.quantity.setText(cartModelList.get(position).getQuantity().toString());
        holder.quantity1.setText("x " + cartModelList.get(position).getQuantity().toString());
        holder.totalPrice.setText(cartModelList.get(position).getTotalPrice().toString() + ".000 VND");
        holder.price.setText(cartModelList.get(position).getPrice().toString() +".000 VND");

        if(cartModelList.get(position).getQuantity() >= 10){
            holder.up.setVisibility(View.INVISIBLE);
            holder.down.setVisibility(View.VISIBLE);
        }else if(cartModelList.get(position).getQuantity() <= 1){
            holder.up.setVisibility(View.VISIBLE);
            holder.down.setVisibility(View.INVISIBLE);
        } else if (cartModelList.get(position).getQuantity() >= 1){
            holder.up.setVisibility(View.VISIBLE);
            holder.down.setVisibility(View.VISIBLE);
        }

        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(holder.quantity.getText().toString()) + 1;
                int currentQuantity = MainActivity.CartList.get(position).getQuantity();
                int currentPrice = MainActivity.CartList.get(position).getTotalPrice();
                MainActivity.CartList.get(position).setQuantity(newQuantity);
                int newPrice = (currentPrice * newQuantity) / currentQuantity;
                MainActivity.CartList.get(position).setTotalPrice(newPrice);
                if(newQuantity >= 10){
                    holder.up.setVisibility(View.INVISIBLE);
                    holder.down.setVisibility(View.VISIBLE);
                }else if(newQuantity <= 1){
                    holder.up.setVisibility(View.VISIBLE);
                    holder.down.setVisibility(View.INVISIBLE);
                } else if (newQuantity >= 1){
                    holder.up.setVisibility(View.VISIBLE);
                    holder.down.setVisibility(View.VISIBLE);
                }
                holder.totalPrice.setText(String.valueOf(newPrice) +".000 VND");
                holder.quantity.setText(String.valueOf(newQuantity));
                holder.quantity1.setText("x " + String.valueOf(newQuantity));
                Cart.bindingData();
            }
        });
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(holder.quantity.getText().toString()) - 1;
                int currentQuantity = MainActivity.CartList.get(position).getQuantity();
                int currentPrice = MainActivity.CartList.get(position).getTotalPrice();
                MainActivity.CartList.get(position).setQuantity(newQuantity);
                int newPrice = (currentPrice * newQuantity) / currentQuantity;
                MainActivity.CartList.get(position).setTotalPrice(newPrice);
                holder.totalPrice.setText(String.valueOf(newPrice) +".000 VND");
                holder.quantity.setText(String.valueOf(newQuantity));
                holder.quantity1.setText("x " + String.valueOf(newQuantity));
                if(newQuantity >= 10){
                    holder.up.setVisibility(View.INVISIBLE);
                    holder.down.setVisibility(View.VISIBLE);
                }else if(newQuantity <= 1){
                    holder.up.setVisibility(View.VISIBLE);
                    holder.down.setVisibility(View.INVISIBLE);
                } else if (newQuantity >= 1){
                    holder.up.setVisibility(View.VISIBLE);
                    holder.down.setVisibility(View.VISIBLE);
                }
                Cart.bindingData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }


    public static final class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImage, up, down;
        TextView price, totalPrice, name, quantity, quantity1;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            up = itemView.findViewById(R.id.btnUp);
            down = itemView.findViewById(R.id.btnDown);
            foodImage = itemView.findViewById(R.id.food_image);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.unitPrice);
            quantity1 = itemView.findViewById(R.id.quantity1);
            name = itemView.findViewById(R.id.name);
        }
    }
}