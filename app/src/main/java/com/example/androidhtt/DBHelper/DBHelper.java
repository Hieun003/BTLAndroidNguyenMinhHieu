package com.example.androidhtt.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.androidhtt.Model.Cart;
import com.example.androidhtt.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product_db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_USERS = "Account";
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_IMAGE_RES_ID = "imageResId";
    public static final String COLUMN_PRICE = "price";

    private static final String TABLE_CART = "Cart";
    private static final String CART_ID = "id";
    private static final String CART_PRODUCT_ID = "productId";
    private static final String CART_USER_ID = "userId";
    private static final String CART_QUANTITY = "quantity";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean checkUserExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + USER_NAME + " = ?" , new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
    public boolean checkAccountExists(String name, String mk) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + USER_NAME + " = ? AND " + USER_PASSWORD + " = ?", new String[]{name, mk});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " TEXT NOT NULL, "
                + USER_EMAIL + " TEXT UNIQUE NOT NULL, "
                + USER_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_RATING + " REAL,"
                + COLUMN_IMAGE_RES_ID + " INTEGER,"
                + COLUMN_PRICE + " REAL"
                + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + " ("
                + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_PRODUCT_ID + " INTEGER, "
                + CART_USER_ID + " INTEGER, "
                + CART_QUANTITY + " INTEGER, "
                + "FOREIGN KEY (" + CART_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_ID + "), "
                + "FOREIGN KEY (" + CART_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + USER_ID + "))";
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }
    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, name);
        values.put(USER_EMAIL, email);
        values.put(USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        if (result == -1) {
            Log.e("DBHelper", "Failed to insert user");
        } else {
            Log.d("DBHelper", "User added successfully");
        }
        db.close();
    }
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_RATING, product.getRating());
        values.put(COLUMN_IMAGE_RES_ID, product.getImageResId());
        values.put(COLUMN_PRICE, product.getPrice());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
    public void addToCart(int productId, int userId, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CART_PRODUCT_ID, productId);
        values.put(CART_USER_ID, userId);
        values.put(CART_QUANTITY, quantity);
        db.insert(TABLE_CART, null, values);
        db.close();
    }
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            do {
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
                int ratingIndex = cursor.getColumnIndex(COLUMN_RATING);
                int imageResIdIndex = cursor.getColumnIndex(COLUMN_IMAGE_RES_ID);
                int PriceIndex = cursor.getColumnIndex(COLUMN_PRICE);

                if (nameIndex != -1 && categoryIndex != -1 && ratingIndex != -1 && imageResIdIndex != -1) {
                    Product product = new Product(
                            cursor.getString(nameIndex),
                            cursor.getInt(imageResIdIndex),
                            cursor.getString(categoryIndex),
                            cursor.getDouble(ratingIndex),
                            cursor.getDouble(PriceIndex)
                    );
                    productList.add(product);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }
    public List<Cart> getAllCart() {
        List<Cart> CartList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART, null);

        if (cursor.moveToFirst()) {
            do {
                int productIdIndex = cursor.getColumnIndex(CART_PRODUCT_ID);
                int userIdIndex = cursor.getColumnIndex(CART_USER_ID);
                int quantityIndex = cursor.getColumnIndex(CART_QUANTITY);

                if (productIdIndex != -1 && userIdIndex != -1 && quantityIndex != -1) {
                    // Create a Cart object and add it to the list
                    Cart cart = new Cart(
                            cursor.getInt(productIdIndex), // Product ID
                            cursor.getInt(userIdIndex), // User ID
                            cursor.getInt(quantityIndex) // Quantity
                    );
                    CartList.add(cart);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return CartList;
    }
}
