package com.example.androidhtt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhtt.CartItem;
import com.example.androidhtt.DBHelper.DBHelper;
import com.example.androidhtt.Model.Product;
import com.example.androidhtt.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<Product> cartList;
    DBHelper dbHelper;

    public CartAdapter(Context context, List<Product> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product cartItem = cartList.get(position);
        holder.itemName.setText(cartItem.getName());
        holder.itemDetails.setText(cartItem.getCategory());
        holder.itemPrice.setText(String.valueOf(cartItem.getTotalPrice()));
        holder.itemImage.setImageResource(cartItem.getImageResId());
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName, itemDetails, itemPrice,itemQuantity;
        ImageButton decreaseQuantity,increaseQuantity,deleteButton;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemDetails = itemView.findViewById(R.id.itemDetails);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            decreaseQuantity = itemView.findViewById(R.id.decreaseQuantity);
            increaseQuantity = itemView.findViewById(R.id.increaseQuantity);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
