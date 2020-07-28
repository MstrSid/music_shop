package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int orderCol = 0;
    Spinner spinnerOptions;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(spinnerAdapter);
    }

    void createSpinner() {
        spinnerOptions = findViewById(R.id.spinnerOptions);
        spinnerOptions.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Гитара");
        spinnerArrayList.add("Клавиши");
        spinnerArrayList.add("Ударные");
    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Гитара", 500.0);
        goodsMap.put("Клавиши", 800.0);
        goodsMap.put("Ударные", 1500.0);
    }

    public void increaseQuantity(View view) {
        TextView textViewQuantityVal = findViewById(R.id.textViewQuantityVal);
        ++orderCol;
        textViewQuantityVal.setText(Integer.toString(orderCol));
        TextView textViewPriceVal = findViewById(R.id.textViewPriceVal);
        textViewPriceVal.setText(Double.toString(price * orderCol));
    }

    public void descreaseQuantity(View view) {
        TextView textViewQuantityVal = findViewById(R.id.textViewQuantityVal);
        --orderCol;
        orderCol = orderCol < 0 ? orderCol = 0 : orderCol;
        textViewQuantityVal.setText(Integer.toString(orderCol));
        TextView textViewPriceVal = findViewById(R.id.textViewPriceVal);
        textViewPriceVal.setText(Double.toString(price * orderCol));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textViewPriceVal = findViewById(R.id.textViewPriceVal);
        goodsName = spinnerOptions.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        textViewPriceVal.setText(Double.toString(price * orderCol));
        ImageView imageViewItems = findViewById(R.id.imageViewItems);

        switch (goodsName) {
            case "Клавиши":
                imageViewItems.setImageResource(R.drawable.keyboard);
                break;
            case "Ударные":
                imageViewItems.setImageResource(R.drawable.drums);
                break;
            case "Гитара":
            default:
                imageViewItems.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}