package com.example.yoant.foodcritic.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProductActivity extends AppCompatActivity {

    private String mType;

    @BindView(R.id.create_product_image_button)
    ImageButton mImageButton;
    @BindView(R.id.edit_text_product_name_create_product)
    EditText mProductName;
    @BindView(R.id.edit_text_product_fat_create_product)
    EditText mProductFatValue;
    @BindView(R.id.edit_text_product_protein_create_product)
    EditText mProductProteinValue;
    @BindView(R.id.edit_text_product_carbon_create_product)
    EditText mProductCarbonValue;
    @BindView(R.id.edit_text_product_energetic_create_product)
    EditText mProductEnergyValue;
    @BindView(R.id.create_button_create_product)
    Button mCreateButton;

    @OnClick(R.id.create_button_create_product)
    public void onCLick() {
        SQLiteDatabaseHelper db = SQLiteDatabaseHelper.getsInstance(getApplicationContext());
        Product product = new Product(0, R.drawable.vitamins_fruit_logo, mProductName.getText().toString(), "", Double.parseDouble(mProductEnergyValue.getText().toString()), Double.parseDouble(mProductCarbonValue.getText().toString()), Double.parseDouble(mProductProteinValue.getText().toString()), Double.parseDouble(mProductFatValue.getText().toString()), mType, 0, "", "");
        if (mType != null) {
            db.addProduct(product);
            Intent intent = new Intent(this, ProductsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_product);
        mType = getIntent().getStringExtra("type");
        toolbar.setTitle("New " + mType.substring(0, 1) + mType.substring(1, mType.length()).toLowerCase());
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mImageButton.setImageResource(R.drawable.vitamins_fruit_logo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ProductsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}