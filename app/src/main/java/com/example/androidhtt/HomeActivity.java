package com.example.androidhtt;

import android.os.Bundle;

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

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private DBHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseHelper = new DBHelper(this);

        if(databaseHelper.getAllProducts().isEmpty()){
            databaseHelper.addProduct(new Product("Cheeseburger",R.drawable.pngwing_7, "Wendy's Burger", 4.5,45));
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
}