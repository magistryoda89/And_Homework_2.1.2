package com.kolomiytsev.and_homework_212;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnOk;
    private Spinner spinCountry;
    private Spinner spinCity;
    private Spinner spinHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        showAddress();
    }

    private void initViews() {
        btnOk = findViewById(R.id.btn_Ok);
        spinCountry = findViewById(R.id.spin_Country);
        spinCity = findViewById(R.id.spin_City);
        spinHouse = findViewById(R.id.spin_House);
        initSpinnerCountries();
        initSpinnerHouse();
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCountry.setAdapter(adapter);

        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Russia":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities, android.R.layout.simple_spinner_item);
                break;
            case "Ukraine":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities, android.R.layout.simple_spinner_item);
                break;
            case "Belarus":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinCity.setAdapter(adapter);
        }
    }

    private void initSpinnerHouse() {
        Integer[] houses = new Integer[50];
        for (int i = 0; i < houses.length; i++) {
            houses[i] = i + 1;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, houses);
        spinHouse.setAdapter(adapter);
    }

    private void showAddress() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, spinCountry.getSelectedItem().toString()
                    + "\n" + spinCity.getSelectedItem().toString()
                    + "\n" + spinHouse.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}