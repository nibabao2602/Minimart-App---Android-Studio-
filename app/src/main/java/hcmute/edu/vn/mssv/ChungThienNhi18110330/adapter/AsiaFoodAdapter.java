package hcmute.edu.vn.mssv.ChungThienNhi18110330.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.DetailsActivity;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.AsiaFood;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.R;
import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.PopularFood;

import java.util.List;


public class AsiaFoodAdapter extends RecyclerView.Adapter<AsiaFoodAdapter.AsiaFoodViewHolder> {

    Context context;
    List<AsiaFood> asiaFoodList;



    public AsiaFoodAdapter(Context context, List<AsiaFood> asiaFoodList) {
        this.context = context;
        this.asiaFoodList = asiaFoodList;
    }

    @NonNull
    @Override
    public AsiaFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.asia_food_row_item, parent, false);
        return new AsiaFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AsiaFoodViewHolder holder, int position) {

        Glide.with(context)
                .load(asiaFoodList.get(position).getImage())
                .into(holder.foodImage);
        holder.name.setText(asiaFoodList.get(position).getName());
        holder.price.setText(asiaFoodList.get(position).getPrice() + ".000 VND");
        holder.rating.setText(asiaFoodList.get(position).getRating().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("name", asiaFoodList.get(position).getName());
                i.putExtra("price", asiaFoodList.get(position).getPrice());
                i.putExtra("rating", asiaFoodList.get(position).getRating().toString());
                i.putExtra("image", asiaFoodList.get(position).getImage());
                i.putExtra("key", asiaFoodList.get(position).getKey());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asiaFoodList.size();
    }


    public static final class AsiaFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public AsiaFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);



        }
    }

}