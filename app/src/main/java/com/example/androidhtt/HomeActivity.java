package com.example.androidhtt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhtt.Adapter.ProductAdapter;
import com.example.androidhtt.DBHelper.DBHelper;
import com.example.androidhtt.Model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private DBHelper databaseHelper;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                navigateToHome();
                return true;
            } else if (itemId == R.id.profile) {
                navigateToProfile();
                return true;
            } else if (itemId == R.id.cart) {
                navigateToCart();
                return true;
            } else if (itemId == R.id.favorites) {
                navigateToFavorites();
                return true;
            }
            return false;
        });
        databaseHelper = new DBHelper(this);

        if(databaseHelper.getAllProducts().isEmpty()){
            databaseHelper.addProduct(new Product("Cheeseburger", R.drawable.pngwing_7, "Wendy's Burger", 4.5,45));
            databaseHelper.addProduct(new Product("Hamburger",R.drawable.pngwing_7, "Veggie Burger", 4.9,50));
            databaseHelper.addProduct(new Product("Chicken Burger",R.drawable.pngwing_7, "Chicken Burger", 4.4,20));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger",5,30));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger", 4.3,40));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger", 4.2,10));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger", 4,15));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger",4.6,25));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger", 4.8,30));
            databaseHelper.addProduct(new Product("Fried Chicken Burger", R.drawable.pngwing_7,"Chicken Burger", 4.1,20));
        }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        // Lấy danh sách sản phẩm từ SQLite
        List<Product> productList = databaseHelper.getAllProducts();

        // Thiết lập Adapter cho RecyclerView
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }
    private void navigateToHome() {
        // Logic for navigating to the Home screen
        Toast.makeText(this, "Navigating to Home", Toast.LENGTH_SHORT).show();
    }

    private void navigateToProfile() {
        // Logic for navigating to the Profile screen
        Toast.makeText(this, "Navigating to Profile", Toast.LENGTH_SHORT).show();
    }

    private void navigateToCart() {
        // Logic for navigating to the Cart screen
        Toast.makeText(this, "Navigating to Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, CartItem.class);
        startActivity(intent);
    }

    private void navigateToFavorites() {
        // Logic for navigating to the Favorites screen
        Toast.makeText(this, "Navigating to Favorites", Toast.LENGTH_SHORT).show();
    }
}