package com.cscorner.lap3_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_ADD_EXPENSE = 1001;

    // Keys for sending data between activities
    public static final String KEY_AMOUNT = "key_amount";
    public static final String KEY_CURRENCY = "key_currency";
    public static final String KEY_CATEGORY = "key_category";
    public static final String KEY_REMARK = "key_remark";
    public static final String KEY_CREATED_DATE = "key_created_date";

    // UI components
    private TextView tvTitle, tvLastExpense;
    private Button btnAddNew, btnViewDetail;
    private ImageView imgLogo;

    // Last expense data
    private String lastAmount = "0";
    private String lastCurrency = "";
    private String lastCategory = "";
    private String lastRemark = "";
    private String lastCreatedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvLastExpense = findViewById(R.id.tvLastExpense);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnViewDetail = findViewById(R.id.btnViewDetail);
        imgLogo = findViewById(R.id.imgLogo);

        // Title text
        tvTitle.setText("Expense Tracker - PA BORASY");

        // Initial state
        updateLastExpenseText();
        btnViewDetail.setEnabled(false); // disabled until first expense added

        // Add new expense
        btnAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivityForResult(intent, REQ_ADD_EXPENSE);
        });

        // View detail
        btnViewDetail.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExpenseDetailActivity.class);
            intent.putExtra(KEY_AMOUNT, lastAmount);
            intent.putExtra(KEY_CURRENCY, lastCurrency);
            intent.putExtra(KEY_CATEGORY, lastCategory);
            intent.putExtra(KEY_REMARK, lastRemark);
            intent.putExtra(KEY_CREATED_DATE, lastCreatedDate);
            startActivity(intent);
        });
    }

    private void updateLastExpenseText() {
        if (lastAmount.equals("0") || lastAmount.isEmpty()) {
            tvLastExpense.setText("My last expense was 0");
        } else {
            tvLastExpense.setText("My last expense was " + lastAmount + " " + lastCurrency);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ADD_EXPENSE && resultCode == RESULT_OK && data != null) {
            // receive data from AddExpenseActivity
            lastAmount = data.getStringExtra(KEY_AMOUNT);
            lastCurrency = data.getStringExtra(KEY_CURRENCY);
            lastCategory = data.getStringExtra(KEY_CATEGORY);
            lastRemark = data.getStringExtra(KEY_REMARK);
            lastCreatedDate = data.getStringExtra(KEY_CREATED_DATE);

            updateLastExpenseText();
            btnViewDetail.setEnabled(true);
        }
    }
}