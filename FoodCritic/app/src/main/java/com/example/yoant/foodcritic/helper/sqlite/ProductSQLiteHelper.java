package com.example.yoant.foodcritic.helper.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ProductSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "products.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PRODUCTS = "products";
    public static final String PRODUCTS_COLUMN_ID = "_id";
    public static final String PRODUCT_COLUMN_FAT = "fat";
    public static final String PRODUCT_COLUMN_PROTEIN = "prot";
    public static final String PRODUCT_COLUMN_CARBON = "carb";
    public static final String PRODUCT_COLUMN_ENERGY = "energy";
    public static final String PRODUCT_COLUMN_ADDITIONAL = "more";
    public static final String PRODUCT_COLUMN_FAVORITE = "star";

    private static final String DATABASE_CREATE = "create table " + TABLE_PRODUCTS + "( "
            + PRODUCTS_COLUMN_ID + " integer primary key autoincrement, "
            + PRODUCT_COLUMN_PROTEIN + " text not null, "
            + PRODUCT_COLUMN_FAT + " text not null, "
            + PRODUCT_COLUMN_CARBON + " text not null, "
            + PRODUCT_COLUMN_ENERGY + " text not null, "
            + PRODUCT_COLUMN_ADDITIONAL + " text, "
            + PRODUCT_COLUMN_FAVORITE + " text not null);";

    public ProductSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVerion) {
        Log.w(ProductSQLiteHelper.class.getName(),
                "Deploying new version of the products database from version "
                        + oldVersion + " to " + newVerion);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }
}
