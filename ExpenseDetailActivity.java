package com.cscorner.lap3_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ExpenseDetailActivity extends AppCompatActivity {

    private TextView tvAmount, tvCurrency, tvCategory, tvRemark, tvDate;
    private Button btnAddNew, btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        tvAmount = findViewById(R.id.tvAmount);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvCategory = findViewById(R.id.tvCategory);
        tvRemark = findViewById(R.id.tvRemark);
        tvDate = findViewById(R.id.tvDate);

        btnAddNew = findViewById(R.id.btnAddNewFromDetail);
        btnBackHome = findViewById(R.id.btnBackHome);

        // Retrieve data from MainActivity
        Intent intent = getIntent();
        String amount = intent.getStringExtra(MainActivity.KEY_AMOUNT);
        String currency = intent.getStringExtra(MainActivity.KEY_CURRENCY);
        String category = intent.getStringExtra(MainActivity.KEY_CATEGORY);
        String remark = intent.getStringExtra(MainActivity.KEY_REMARK);
        String date = intent.getStringExtra(MainActivity.KEY_CREATED_DATE);

        // Display values
        tvAmount.setText("Amount: " + (amount != null ? amount : ""));
        tvCurrency.setText("Currency: " + (currency != null ? currency : ""));
        tvCategory.setText("Category: " + (category != null ? category : ""));
        tvRemark.setText("Remark: " + (remark != null ? remark : ""));
        tvDate.setText("Created Date: " + (date != null ? date : ""));

        // Navigation
        btnAddNew.setOnClickListener(v -> {
            Intent addIntent = new Intent(ExpenseDetailActivity.this, AddExpenseActivity.class);
            startActivity(addIntent);
            finish();
        });

        btnBackHome.setOnClickListener(v -> finish());
    }
}