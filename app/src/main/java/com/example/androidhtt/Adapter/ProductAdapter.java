package com.example.androidhtt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhtt.Model.Product;
import com.example.androidhtt.ProductDetailActivity;
import com.example.androidhtt.R;
import com.example.androidhtt.SignInActivity;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductCategory.setText(product.getCategory());
        holder.tvRating.setText(String.valueOf(product.getRating()));
        holder.imgProduct.setImageResource(product.getImageResId());
        AtomicBoolean isFavorite = new AtomicBoolean(true);
        holder.ivFavorite.setOnClickListener(v ->{
            if(isFavorite.get()){
                Toast.makeText(context, product.getName() + " added to favorites", Toast.LENGTH_SHORT).show();
                holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_25);
                isFavorite.set(false);
            }
            else{
                Toast.makeText(context, product.getName() + " removed to favorites", Toast.LENGTH_SHORT).show();
                holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
                isFavorite.set(true);
            }
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_name",product.getName());
            intent.putExtra("img_product",product.getImageResId());
            intent.putExtra("product_rating",product.getRating());
            intent.putExtra("product_price",product.getPrice());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, ivFavorite;
        TextView tvProductName, tvProductCategory, tvRating;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            ivFavorite = itemView.findViewById(R.id.tvFavorite);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductCategory = itemView.findViewById(R.id.tvProductCategory);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
