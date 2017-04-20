//package com.example.yoant.foodcritic.dao;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.yoant.foodcritic.models.Product;
//import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductsDataSource {
//
//    private SQLiteDatabase mDatabase;
//    private SQLiteDatabaseHelper mDatabaseHelper;
//    private String[] allColumns = {SQLiteDatabaseHelper.PRODUCTS_COLUMN_ID,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_IMAGE,
//            SQLiteDatabaseHelper.PRODUCTS_COLUMN_NAME,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_FAT,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_PROTEIN,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_CARBON,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_ADDITIONAL,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_ENERGY,
//            SQLiteDatabaseHelper.PRODUCT_COLUMN_FAVORITE,
//    };
//
//    public ProductsDataSource(Context context) {
//        mDatabaseHelper = new SQLiteDatabaseHelper(context);
//    }
//
//    public void openWritable() throws SQLException {
//        mDatabase = mDatabaseHelper.getWritableDatabase();
//    }
//
//    public void openReadable() throws SQLException {
//        mDatabase = mDatabaseHelper.getReadableDatabase();
//    }
//
//    public void close() {
//        mDatabaseHelper.close();
//    }
//
//    public Product createProduct(String productName, int productImage, String proteinValue, String fatValue, String carbonValue, String energyValue, String addtionalValue, String favoriteValue) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLiteDatabaseHelper.PRODUCTS_COLUMN_NAME, productName);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_IMAGE, productImage);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_PROTEIN, proteinValue);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_FAT, fatValue);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_CARBON, carbonValue);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_ENERGY, energyValue);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_ADDITIONAL, addtionalValue);
//        contentValues.put(SQLiteDatabaseHelper.PRODUCT_COLUMN_FAVORITE, favoriteValue);
//
//        long insertID = mDatabase.insert(SQLiteDatabaseHelper.TABLE_PRODUCTS, null, contentValues);
//
//        Cursor cursor = mDatabase.query(SQLiteDatabaseHelper.TABLE_PRODUCTS, allColumns, SQLiteDatabaseHelper.PRODUCTS_COLUMN_ID + " = " + insertID, null, null, null, null);
//        cursor.moveToFirst();
//        Product product = convertCursorToProduct(cursor);
//        cursor.close();
//        return product;
//
//    }
//
//    public void deleteProduct(Product product) {
//        long id = product.getId();
//        mDatabase.delete(SQLiteDatabaseHelper.TABLE_PRODUCTS,
//                SQLiteDatabaseHelper.PRODUCTS_COLUMN_ID + " = " + id, null);
//    }
//
//    public List<Product> getAllProducts() {
//        List<Product> products = new ArrayList<>();
//
//        Cursor cursor = mDatabase.query(SQLiteDatabaseHelper.TABLE_PRODUCTS,
//                allColumns, null, null, null, null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Product product = convertCursorToProduct(cursor);
//            products.add(product);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return products;
//    }
//
//    private Product convertCursorToProduct(Cursor cursor) {
//        Product product = new Product();
//        product.setId(cursor.getLong(0));
//        product.setName(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.PRODUCTS_COLUMN_NAME)));
//        product.setProtein(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.PRODUCT_COLUMN_PROTEIN))));
//        product.setFat(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.PRODUCT_COLUMN_FAT))));
//        product.setCarb(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.PRODUCT_COLUMN_CARBON))));
//        product.setEnergeticValue(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.PRODUCT_COLUMN_ENERGY))));
//        product.setPicID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.PRODUCT_COLUMN_IMAGE))));
//        return product;
//    }
//}
