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

import hcmute.edu.vn.mssv.ChungThienNhi18110330.DetailsActivity;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.Dashboard;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.R;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.PopularFood;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder> {

    Context context;
    List<PopularFood> popularFoodList;



    public PopularFoodAdapter(Context context, List<PopularFood> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_food_row_item, parent, false);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodViewHolder holder, int position) {
        Glide.with(context)
                .load(popularFoodList.get(position).getImage())
                .into(holder.foodImage);
        holder.name.setText(popularFoodList.get(position).getName());
        holder.price.setText(popularFoodList.get(position).getPrice()+".000 \n VND");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("name", popularFoodList.get(position).getName());
                i.putExtra("price", popularFoodList.get(position).getPrice());
                i.putExtra("rating", popularFoodList.get(position).getRating().toString());
                i.putExtra("image", popularFoodList.get(position).getImage());
                i.putExtra("key", popularFoodList.get(position).getKey());

                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }


    public static final class PopularFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);

        }
    }
}