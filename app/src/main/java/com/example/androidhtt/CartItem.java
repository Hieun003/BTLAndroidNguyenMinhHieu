package com.example.androidhtt;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhtt.Adapter.CartAdapter;
import com.example.androidhtt.Adapter.ProductAdapter;
import com.example.androidhtt.DBHelper.DBHelper;
import com.example.androidhtt.Model.Product;

import java.util.List;

public class CartItem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private DBHelper databaseHelper;
    private TextView subtotalValue, totalValue,deliveryValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });
        subtotalValue = findViewById(R.id.subtotalValue);
        totalValue = findViewById(R.id.totalValue);
        deliveryValue = findViewById(R.id.deliveryValue);

        databaseHelper = new DBHelper(this);
        int userID = databaseHelper.getUserId(this);
        recyclerView = findViewById(R.id.itemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Product> caList = databaseHelper.getAllCart(userID);

        cartAdapter = new CartAdapter(this,caList,deliveryValue,subtotalValue,totalValue);
        recyclerView.setAdapter(cartAdapter);

        double subtotal = databaseHelper.SubTotalPrice(userID);
        double delivery = Double.parseDouble(deliveryValue.getText().toString().replace("$",""));

        double total = subtotal + delivery;

        String formattedSubtotal = "$" + String.format("%.2f", subtotal);
        String formattedtotal = "$" + String.format("%.2f", total);

        subtotalValue.setText(formattedSubtotal);
        totalValue.setText(formattedtotal);
    }
}