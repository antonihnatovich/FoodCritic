package com.example.yoant.foodcritic.helper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yoant.foodcritic.models.FoodProgram;
import com.example.yoant.foodcritic.models.Product;

import java.util.ArrayList;
import java.util.List;


public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabaseHelper sInstance;

    private static final String TAG = "SQLiteDatabaseHelper";
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 4;

    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_MENU_ELEMENTS = "products_menu";
    private static final String TABLE_FOOD_PROGRAM = "food_program";

    private static final String KEY_PRODUCTS_ID = "_id";
    private static final String KEY_PRODUCTS_NAME = "name";
    private static final String KEY_PRODUCTS_IMAGE_URL = "image";
    private static final String KEY_PRODUCTS_FAT = "fat";
    private static final String KEY_PRODUCTS_PROTEIN = "prot";
    private static final String KEY_PRODUCTS_CARBON = "carb";
    private static final String KEY_PRODUCTS_ENERGY = "energy";
    private static final String KEY_PRODUCTS_ADDITIONAL = "more";
    private static final String KEY_PRODUCTS_TYPE = "type";

    private static final String KEY_MENU_ID = "_id";
    private static final String KEY_MENU_NAME = "name";
    private static final String KEY_MENU_PROTEIN = "prot";
    private static final String KEY_MENU_FAT = "fat";
    private static final String KEY_MENU_CARBON = "carbon";
    private static final String KEY_MENU_ENERGY = "energy";
    private static final String KEY_MENU_WEIGHT = "weight";
    private static final String KEY_MENU_DAYNAME = "day_name";
    private static final String KEY_MENU_TIMENAME = "time_name";

    private static final String KEY_PROGRAM_ID = "_id";
    private static final String KEY_PROGRAM_NAME = "name";
    private static final String KEY_PROGRAM_DESCRIPTION = "description";
    private static final String KEY_PROGRAM_CALORIES = "calories";
    private static final String KEY_PROGRAM_FILTERS = "filters";
    private static final String KEY_PROGRAM_FAVORITE = "condition"; //-1 = rejected, 0 = normal, 1 = favorite


    private String DATABASE_CREATE_TABLE_PRODUCTS = "create table " + TABLE_PRODUCTS + "( "
            + KEY_PRODUCTS_ID + " INTEGER PRIMARY KEY, "
            + KEY_PRODUCTS_IMAGE_URL + " INTEGER, "
            + KEY_PRODUCTS_NAME + " TEXT NOT NULL, "
            + KEY_PRODUCTS_PROTEIN + " REAL, "
            + KEY_PRODUCTS_FAT + " REAL, "
            + KEY_PRODUCTS_CARBON + " REAL, "
            + KEY_PRODUCTS_ENERGY + " REAL, "
            + KEY_PRODUCTS_ADDITIONAL + " TEXT, "
            + KEY_PRODUCTS_TYPE + " TEXT);";

    private String DATABASE_CREATE_TABLE_MENU_ELEMENTS = "CREATE TABLE " + TABLE_MENU_ELEMENTS + "( "
            + KEY_MENU_ID + " INTEGER PRIMARY KEY, "
            + KEY_MENU_NAME + " TEXT NOT NULL, "
            + KEY_MENU_PROTEIN + " REAL, "
            + KEY_MENU_FAT + " REAL, "
            + KEY_MENU_CARBON + " REAL, "
            + KEY_MENU_ENERGY + " REAL, "
            + KEY_MENU_WEIGHT + " INTEGER, "
            + KEY_MENU_DAYNAME + " TEXT NOT NULL, "
            + KEY_MENU_TIMENAME + " TEXT NOT NULL);";

    private String DATABASE_CREATE_TABLE_FOOD_PROGRAM = "CREATE TABLE " + TABLE_FOOD_PROGRAM + "( "
            + KEY_PROGRAM_ID + " INTEGER PRIMARY KEY, "
            + KEY_PROGRAM_NAME + " TEXT NOT NULL, "
            + KEY_PROGRAM_FILTERS + " TEXT NOT NULL, "
            + KEY_PROGRAM_DESCRIPTION + " TEXT NOT NULL, "
            + KEY_PROGRAM_CALORIES + " INTEGER, "
            + KEY_PROGRAM_FAVORITE + " INTEGER);";


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
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_PRODUCTS);
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_MENU_ELEMENTS);
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_FOOD_PROGRAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteDatabaseHelper.class.getName(),
                "Deploying new version of the products database from version "
                        + oldVersion + " to " + newVersion);
        if (oldVersion < newVersion) {
            db.execSQL(DATABASE_CREATE_TABLE_FOOD_PROGRAM);
        }
    }

    /*
    Create a new product in the database.
    the addOrUpdate method being called because the product might already exists in the database.
     */
    //@TABLE_PRODUCTS
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

    //@TABLE_MENU
    public void addMenuProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            addOrUpdateMenuProduct(product);
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            Log.d(TAG, "Error while trying to insert value to database.");
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
    }

    //@TABLE_PROGRAM
    public void addFoodProgram(FoodProgram program) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            addOrUpdateFoodProgram(program);
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
    public long addOrUpdateProduct(Product product) {
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

    //@TABLE_MENU
    public long addOrUpdateMenuProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long productId = -1;
        try {
            ContentValues contentValues = getContentValuesMenuProduct(product);

            int rows = db.update(TABLE_MENU_ELEMENTS, contentValues, KEY_PRODUCTS_NAME + "= ? AND " + KEY_MENU_DAYNAME + " = ? AND " + KEY_MENU_TIMENAME + " = ?", new String[]{product.getName(), product.getDayName(), product.getTimeName()});

            //Value 1 is the value of flag, that the product exists in the database
            if (rows == 1) {
                String productSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ? AND %s = ? AND %s",
                        KEY_MENU_ID, TABLE_MENU_ELEMENTS, KEY_MENU_NAME, KEY_MENU_DAYNAME, KEY_MENU_TIMENAME);
                Cursor cursor = db.rawQuery(productSelectQuery, new String[]{product.getName(), product.getDayName(), product.getTimeName()});
                try {
                    if (cursor.moveToFirst()) {
                        productId = cursor.getInt(cursor.getColumnIndex(KEY_MENU_ID));
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            } else {
                //Will be called if we realised, that product with this name does not exists in the database
                productId = db.insertOrThrow(TABLE_MENU_ELEMENTS, null, contentValues);
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

    //@TABLE_PROGRAM
    public long addOrUpdateFoodProgram(FoodProgram program) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long programId = -1;
        try {
            ContentValues contentValues = getContentValuesFoodProgram(program);
            int rows = db.update(TABLE_FOOD_PROGRAM, contentValues, KEY_PROGRAM_NAME + "= ? AND " + KEY_PROGRAM_FILTERS + "= ?", new String[]{program.getName(), program.getFilters()});
            if (rows == 1) {
                String programSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ? AND %s = ? AND %s", KEY_PROGRAM_ID, TABLE_FOOD_PROGRAM, KEY_PROGRAM_NAME, KEY_PROGRAM_FILTERS);
                Cursor cursor = db.rawQuery(programSelectQuery, new String[]{program.getName(), program.getFilters()});
                try {
                    if (cursor.moveToFirst()) {
                        programId = cursor.getInt(cursor.getColumnIndex(KEY_PROGRAM_ID));
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }
            } else {
                programId = db.insertOrThrow(TABLE_FOOD_PROGRAM, null, contentValues);
                db.setTransactionSuccessful();
            }
        } catch (SQLiteException e) {
            Log.d(TAG, "Error happened while inserting or updating product in the database.");
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
        return programId;
    }

    //@TABLE_PRODUCTS
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
                    Product product = new Product(productId, image, name, additional, energy, carbon, protein, fat, ttype, 0, null, null);
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

    //@TABLE_MENU
    public List<Product> getAllMenuProduct() {
        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU_ELEMENTS, null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(KEY_MENU_NAME));
                    String dayName = cursor.getString(cursor.getColumnIndex(KEY_MENU_DAYNAME));
                    String timeName = cursor.getString(cursor.getColumnIndex(KEY_MENU_TIMENAME));
                    double protein = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_PROTEIN));
                    double fat = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_FAT));
                    double carbon = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_CARBON));
                    double energy = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_ENERGY));
                    int weight = cursor.getInt(cursor.getColumnIndex(KEY_MENU_WEIGHT));
                    int productId = cursor.getInt(cursor.getColumnIndex(KEY_MENU_ID));
                    Product product = new Product(productId, 0, name, "", energy, carbon, protein, fat, "", weight, dayName, timeName);
                    list.add(product);
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
        return list;
    }

    //@TABLE_PROGRAM
    public List<FoodProgram> getAllFoodProgramList() {
        List<FoodProgram> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FOOD_PROGRAM, null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(KEY_PROGRAM_NAME));
                    String description = cursor.getString(cursor.getColumnIndex(KEY_PROGRAM_DESCRIPTION));
                    String filters = cursor.getString(cursor.getColumnIndex(KEY_PROGRAM_FILTERS));
                    int calories = cursor.getInt(cursor.getColumnIndex(KEY_PROGRAM_CALORIES));
                    int condition = cursor.getInt(cursor.getColumnIndex(KEY_PROGRAM_FAVORITE));
                    FoodProgram program = new FoodProgram(name, description, filters, calories, condition);
                    list.add(program);
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
        return list;
    }

    //@TABLE_MENU
    public List<Product> getAllMenuProductsByDayName(String dayNamee) {
        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU_ELEMENTS + " WHERE " + KEY_MENU_DAYNAME + " LIKE '" + dayNamee + "%'", null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(KEY_MENU_NAME));
                    String dayName = cursor.getString(cursor.getColumnIndex(KEY_MENU_DAYNAME));
                    String timeName = cursor.getString(cursor.getColumnIndex(KEY_MENU_TIMENAME));
                    double protein = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_PROTEIN));
                    double fat = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_FAT));
                    double carbon = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_CARBON));
                    double energy = cursor.getDouble(cursor.getColumnIndex(KEY_MENU_ENERGY));
                    int weight = cursor.getInt(cursor.getColumnIndex(KEY_MENU_WEIGHT));
                    int productId = cursor.getInt(cursor.getColumnIndex(KEY_MENU_ID));
                    Product product = new Product(productId, 0, name, "", energy, carbon, protein, fat, "", weight, dayName, timeName);
                    list.add(product);
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
        return list;
    }

    //@TABLE_PRODUCTS
    public void deleteAllProductsFromDatabaseProducts() {
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

    //@TABLE_MENU
    public void deleteAllProductsFromMenu() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_MENU_ELEMENTS, null, null);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.d(TAG, "SQLiteException caught while trying to insert all values from database " + TABLE_PRODUCTS);
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
    }

    //@TABLE_PROGRAM
    public void deleteAllFoodPrograms() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_FOOD_PROGRAM, null, null);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.d(TAG, "SQLiteException caught while trying to insert all values from database " + TABLE_PRODUCTS);
        } catch (Exception e) {
            Log.d(TAG, "Some unexpected exception has been caught: " + e);
        } finally {
            db.endTransaction();
        }
    }

    //@TABLE_PRODUCTS
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


    //@TABLE_MENU
    public boolean deleteProductFromMenuTable(String name, String dayName, String timeName) {
        boolean flag = false;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_MENU_ELEMENTS, KEY_MENU_NAME + "='" + name + "' and " + KEY_MENU_DAYNAME + "='" + dayName + "' and " + KEY_MENU_TIMENAME + "='" + timeName + "'", null);
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

    //@TABLE_PROGRAM
    public boolean deleteFoodProgramByNameAndFilters(String name, String filters) {
        boolean flag = false;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_FOOD_PROGRAM, KEY_PROGRAM_NAME + "='" + name + "' and " + KEY_PROGRAM_FILTERS + "='" + filters, null);
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

    //@TABLE_PRODUCTS
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

    //@TABLE_MENU
    private ContentValues getContentValuesMenuProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(KEY_MENU_NAME, product.getName());
        values.put(KEY_MENU_PROTEIN, product.getProtein());
        values.put(KEY_MENU_FAT, product.getFat());
        values.put(KEY_MENU_CARBON, product.getCarb());
        values.put(KEY_MENU_ENERGY, product.getEnergeticValue());
        values.put(KEY_MENU_WEIGHT, product.getWeight());
        values.put(KEY_MENU_TIMENAME, product.getTimeName());
        values.put(KEY_MENU_DAYNAME, product.getDayName());
        return values;
    }

    //@TABLE_PRODUCTS
    private ContentValues getContentValuesFoodProgram(FoodProgram program) {
        ContentValues values = new ContentValues();
        values.put(KEY_PROGRAM_NAME, program.getName());
        values.put(KEY_PROGRAM_DESCRIPTION, program.getDescription());
        values.put(KEY_PROGRAM_CALORIES, program.getCaloriesValue());
        values.put(KEY_PROGRAM_FAVORITE, program.getCondition());
        values.put(KEY_PROGRAM_FILTERS, program.getFilters());
        return values;
    }
}