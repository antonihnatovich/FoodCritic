package com.example.yoant.foodcritic.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.yoant.foodcritic.core.ProductHeh;
import com.example.yoant.foodcritic.helper.sqlite.ProductSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductsDataSource {

    private SQLiteDatabase mDatabase;
    private ProductSQLiteHelper mDatabaseHelper;
    private String[] allColumns = {ProductSQLiteHelper.PRODUCTS_COLUMN_ID,
            ProductSQLiteHelper.PRODUCT_COLUMN_IMAGE,
            ProductSQLiteHelper.PRODUCTS_COLUMN_NAME,
            ProductSQLiteHelper.PRODUCT_COLUMN_FAT,
            ProductSQLiteHelper.PRODUCT_COLUMN_PROTEIN,
            ProductSQLiteHelper.PRODUCT_COLUMN_CARBON,
            ProductSQLiteHelper.PRODUCT_COLUMN_ADDITIONAL,
            ProductSQLiteHelper.PRODUCT_COLUMN_ENERGY,
            ProductSQLiteHelper.PRODUCT_COLUMN_FAVORITE,
    };

    public ProductsDataSource(Context context) {
        mDatabaseHelper = new ProductSQLiteHelper(context);
    }

    public void openWritable() throws SQLException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void openReadable() throws SQLException {
        mDatabase = mDatabaseHelper.getReadableDatabase();
    }

    public void close() {
        mDatabaseHelper.close();
    }

    public ProductHeh createProduct(String productName, int productImage, String proteinValue, String fatValue, String carbonValue, String energyValue, String addtionalValue, String favoriteValue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductSQLiteHelper.PRODUCTS_COLUMN_NAME, productName);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_IMAGE, productImage);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_PROTEIN, proteinValue);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_FAT, fatValue);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_CARBON, carbonValue);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_ENERGY, energyValue);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_ADDITIONAL, addtionalValue);
        contentValues.put(ProductSQLiteHelper.PRODUCT_COLUMN_FAVORITE, favoriteValue);

        long insertID = mDatabase.insert(ProductSQLiteHelper.TABLE_PRODUCTS, null, contentValues);

        Cursor cursor = mDatabase.query(ProductSQLiteHelper.TABLE_PRODUCTS, allColumns, ProductSQLiteHelper.PRODUCTS_COLUMN_ID + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();
        ProductHeh product = converCursorToProduct(cursor);
        cursor.close();
        return product;

    }

    public void deleteProduct(ProductHeh productHeh) {
        long id = productHeh.getId();
        mDatabase.delete(ProductSQLiteHelper.TABLE_PRODUCTS,
                ProductSQLiteHelper.PRODUCTS_COLUMN_ID + " = " + id, null);
    }

    public List<ProductHeh> getAllProducts() {
        List<ProductHeh> products = new ArrayList<>();

        Cursor cursor = mDatabase.query(ProductSQLiteHelper.TABLE_PRODUCTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductHeh productHeh = converCursorToProduct(cursor);
            products.add(productHeh);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }

    private ProductHeh converCursorToProduct(Cursor cursor) {
        ProductHeh productHeh = new ProductHeh();
        productHeh.setId(cursor.getLong(0));
        productHeh.setName(cursor.getString(cursor.getColumnIndex(ProductSQLiteHelper.PRODUCTS_COLUMN_NAME)));
        productHeh.setProtein(Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductSQLiteHelper.PRODUCT_COLUMN_PROTEIN))));
        productHeh.setFat(Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductSQLiteHelper.PRODUCT_COLUMN_FAT))));
        productHeh.setCarb(Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductSQLiteHelper.PRODUCT_COLUMN_CARBON))));
        productHeh.setEnergeticValue(Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductSQLiteHelper.PRODUCT_COLUMN_ENERGY))));
        productHeh.setPicID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProductSQLiteHelper.PRODUCT_COLUMN_IMAGE))));
        return productHeh;
    }
}
