package com.cscorner.lap3_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etAmount, etRemark, etDate;
    private Spinner spCurrency, spCategory;
    private Button btnAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etAmount = findViewById(R.id.etAmount);
        etRemark = findViewById(R.id.etRemark);
        etDate = findViewById(R.id.etDate);
        spCurrency = findViewById(R.id.spCurrency);
        spCategory = findViewById(R.id.spCategory);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        // Spinner setup
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(
                this, R.array.currency_array, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(currencyAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        // Date picker
        etDate.setOnClickListener(v -> showDatePicker());

        // Add expense button
        btnAddExpense.setOnClickListener(v -> addExpense());
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                (DatePicker datePicker, int year, int month, int day) -> {
                    String dateStr = String.format("%02d/%02d/%04d", day, month + 1, year);
                    etDate.setText(dateStr);
                }, y, m, d);
        dpd.show();
    }

    private void addExpense() {
        String amount = etAmount.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String currency = spCurrency.getSelectedItem().toString();
        String category = spCategory.getSelectedItem().toString();

        if (amount.isEmpty()) {
            Toast.makeText(this, "Please enter the amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (date.isEmpty()) {
            Toast.makeText(this, "Please select the date", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent result = new Intent();
        result.putExtra(MainActivity.KEY_AMOUNT, amount);
        result.putExtra(MainActivity.KEY_CURRENCY, currency);
        result.putExtra(MainActivity.KEY_CATEGORY, category);
        result.putExtra(MainActivity.KEY_REMARK, remark);
        result.putExtra(MainActivity.KEY_CREATED_DATE, date);

        setResult(RESULT_OK, result);
        finish();
    }
}