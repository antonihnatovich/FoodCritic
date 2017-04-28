package com.example.yoant.foodcritic.helper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yoant.foodcritic.models.Product;

import java.util.ArrayList;
import java.util.List;


public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabaseHelper sInstance;

    private static final String TAG = "SQLiteDatabaseHelper";
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_PRODUCTS = "products";

    public static final String KEY_PRODUCTS_ID = "_id";
    public static final String KEY_PRODUCTS_NAME = "name";
    public static final String KEY_PRODUCTS_IMAGE_URL = "image";
    public static final String KEY_PRODUCTS_FAT = "fat";
    public static final String KEY_PRODUCTS_PROTEIN = "prot";
    public static final String KEY_PRODUCTS_CARBON = "carb";
    public static final String KEY_PRODUCTS_ENERGY = "energy";
    public static final String KEY_PRODUCTS_ADDITIONAL = "more";
    public static final String KEY_PRODUCTS_TYPE = "type";


    /*
    The constructor is set to be private, because i'm using the singletone pattern
    to get the instance of the SQLiteDatabaseHelper class to avoid memory leaks.
     */
    private SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    The static getInstance method helps me to avoid the memory leak
    with an Activity's context thanks to application context.
     */
    public static synchronized SQLiteDatabaseHelper getsInstance(Context context) {
        if (sInstance == null)
            sInstance = new SQLiteDatabaseHelper(context.getApplicationContext());
        return sInstance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String DATABASE_CREATE = "create table " + TABLE_PRODUCTS + "( "
                + KEY_PRODUCTS_ID + " INTEGER PRIMARY KEY, "
                + KEY_PRODUCTS_IMAGE_URL + " INTEGER, "
                + KEY_PRODUCTS_NAME + " TEXT NOT NULL, "
                + KEY_PRODUCTS_PROTEIN + " REAL, "
                + KEY_PRODUCTS_FAT + " REAL, "
                + KEY_PRODUCTS_CARBON + " REAL, "
                + KEY_PRODUCTS_ENERGY + " REAL, "
                + KEY_PRODUCTS_ADDITIONAL + " TEXT, "
                + KEY_PRODUCTS_TYPE + " TEXT);";

        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteDatabaseHelper.class.getName(),
                "Deploying new version of the products database from version "
                        + oldVersion + " to " + newVersion);
        if (oldVersion < newVersion) {
            db.execSQL("ALTER TABLE " + TABLE_PRODUCTS + " ADD COLUMN " + KEY_PRODUCTS_TYPE + " TEXT");
        }
    }

    /*
    Create a new product in the database.
    the addOrUpdate method being called because the product might already exists in the database.
     */
    public void addProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            addOrUpdateProduct(product);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.d(TAG, "Error while trying to insert value to database.");
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
    }

    /*
    This method allows me to properly update product values if it already exists in the database
    and add a new one if none of this product with this name exists in the database.
     */
    private long addOrUpdateProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long productId = -1;

        try {
            ContentValues contentValues = getContentValuesProduct(product);

            int rows = db.update(TABLE_PRODUCTS, contentValues, KEY_PRODUCTS_NAME + "= ?", new String[]{product.getName()});

            //Value 1 is the value of flag, that the product exists in the database
            if (rows == 1) {
                String productSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_PRODUCTS_ID, TABLE_PRODUCTS, KEY_PRODUCTS_NAME);

                Cursor cursor = db.rawQuery(productSelectQuery, new String[]{String.valueOf(product.getName())});

                try {
                    if (cursor.moveToFirst()) {
                        productId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            } else {
                //Will be called if we realised, that product with this name does not exists in the database
                productId = db.insertOrThrow(TABLE_PRODUCTS, null, contentValues);
                db.setTransactionSuccessful();
            }
        } catch (SQLiteException e) {
            Log.d(TAG, "Error happened while inserting or updating product in the database.");
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
        return productId;
    }

    public List<Product> getAllProducts(String type) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_PRODUCTS_TYPE + " LIKE '" + type + "%'", null);

        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int image = cursor.getInt(1);
                    String name = cursor.getString(cursor.getColumnIndex(KEY_PRODUCTS_NAME));
                    String additional = cursor.getString(cursor.getColumnIndex(KEY_PRODUCTS_ADDITIONAL));
                    double protein = cursor.getDouble(cursor.getColumnIndex(KEY_PRODUCTS_PROTEIN));
                    double fat = cursor.getDouble(cursor.getColumnIndex(KEY_PRODUCTS_FAT));
                    double carbon = cursor.getDouble(cursor.getColumnIndex(KEY_PRODUCTS_CARBON));
                    double energy = cursor.getDouble(cursor.getColumnIndex(KEY_PRODUCTS_ENERGY));
                    int productId = cursor.getInt(cursor.getColumnIndex(KEY_PRODUCTS_ID));
                    String ttype = cursor.getString(cursor.getColumnIndex(KEY_PRODUCTS_TYPE));
                    Product product = new Product(productId, image, name, additional, energy, carbon, protein, fat, ttype);
                    products.add(product);
                    cursor.moveToNext();
                }
            }
        } catch (SQLiteException e) {
            Log.d(TAG, "SQLiteException caught while trying to insert all values from database " + TABLE_PRODUCTS);
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return products;
    }

    public void deleteAllProductsFromDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_PRODUCTS, null, null);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.d(TAG, "SQLiteException caught while trying to insert all values from database " + TABLE_PRODUCTS);
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
    }

    public boolean deleteProductFromDatabaseByName(String name) {
        boolean flag = false;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_PRODUCTS, KEY_PRODUCTS_NAME + "=?", new String[]{name});
            db.setTransactionSuccessful();
            flag = true;
        } catch (SQLiteException e) {
            Log.d(TAG, "SQLiteException caught while trying to insert all values from database " + TABLE_PRODUCTS);
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
        return flag;
    }

    private ContentValues getContentValuesProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCTS_NAME, product.getName());
        values.put(KEY_PRODUCTS_PROTEIN, product.getProtein());
        values.put(KEY_PRODUCTS_FAT, product.getFat());
        values.put(KEY_PRODUCTS_CARBON, product.getCarb());
        values.put(KEY_PRODUCTS_ENERGY, product.getEnergeticValue());
        values.put(KEY_PRODUCTS_ADDITIONAL, product.getProductDescription());
        values.put(KEY_PRODUCTS_TYPE, product.getType());
        return values;
    }
}
