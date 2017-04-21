package com.example.yoant.foodcritic.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;

public class CreateProductActivity extends AppCompatActivity implements View.OnClickListener {

    private int mPictureId;

    private EditText mProductName;
    private EditText mProductFatValue;
    private EditText mProductProteinValue;
    private EditText mProductCarbonValue;
    private EditText mProductEnergyValue;

    private Button mCreateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        mProductName = (EditText) findViewById(R.id.edit_text_product_name_create_product);
        mProductFatValue = (EditText) findViewById(R.id.edit_text_product_fat_create_product);
        mProductProteinValue = (EditText) findViewById(R.id.edit_text_product_protein_create_product);
        mProductCarbonValue = (EditText) findViewById(R.id.edit_text_product_carbon_create_product);
        mProductEnergyValue = (EditText) findViewById(R.id.edit_text_product_energetic_create_product);

        mCreateButton = (Button) findViewById(R.id.create_button_create_product);
        mCreateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_button_create_product:
                SQLiteDatabaseHelper db = SQLiteDatabaseHelper.getsInstance(getApplicationContext());
                Product product = new Product(0, R.drawable.vitamins_fruit_logo, mProductName.getText().toString(), "", Double.parseDouble(mProductEnergyValue.getText().toString()), Double.parseDouble(mProductCarbonValue.getText().toString()), Double.parseDouble(mProductProteinValue.getText().toString()), Double.parseDouble(mProductFatValue.getText().toString()), false);
                db.addProduct(product);
                Intent intent = new Intent(this, ProductsActivity.class);
                startActivity(intent);
                Toast.makeText(this, "The product '" + mProductName.getText().toString() + "' has succesfully added!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
