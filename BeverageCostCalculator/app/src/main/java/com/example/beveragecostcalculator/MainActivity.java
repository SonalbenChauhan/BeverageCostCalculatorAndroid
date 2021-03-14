package com.example.beveragecostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String selected;
    String flavor;
    boolean checked1 = false;
    boolean checked2 = false;
    RadioButton coffee;
    RadioButton tea;
    RadioButton none;
    RadioButton f1;
    RadioButton f2;
    EditText txtName;
    CheckBox milk;
    String provinceName;
    String sizeBeverage;
    CheckBox sugar;
    AutoCompleteTextView pro;
    RadioGroup bSelection;
    String text;
    String checkedCost;
    double costSize;
    double costAdOn = 0;
    double costFlavor;
    double total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button buttonExe = (Button) findViewById(R.id.buttonExe);
        coffee = (RadioButton) findViewById(R.id.radioCoffee);
        tea = (RadioButton) findViewById(R.id.radioTea);
        none = (RadioButton) findViewById(R.id.radioNo);
        f1 = (RadioButton) findViewById(R.id.radioF1);
        f2 = (RadioButton) findViewById(R.id.radioF2);
        txtName = (EditText) findViewById(R.id.txtName);
        milk = (CheckBox) findViewById(R.id.checkMilk);
        sugar = (CheckBox) findViewById(R.id.checkSugar);
        bSelection = (RadioGroup) findViewById(R.id.groupBeverage);

        //size array
        String[] sizes = { "small", "medium", "large"};
        final Spinner size =(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sizes);
        size.setAdapter(adapter);

        //province array
        String[] pro = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon"};
        final AutoCompleteTextView prov = (AutoCompleteTextView) findViewById(R.id.autoPro);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, pro);
        prov.setAdapter(adapter1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonExe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cusName = txtName.getText().toString();
                //getting beverage info
                if (coffee.isChecked()) {
                    selected = "coffee";
                } else if (tea.isChecked()) {
                    selected = "tea";
                }


                //getting adon info
                if (milk.isChecked()) {
                    checked1 = true;
                    costAdOn = costAdOn + 1.25;
                }
                if (sugar.isChecked()) {
                    checked2 = true;
                    costAdOn = costAdOn + 1;
                }


                if (none.isChecked()) {
                    flavor = "no";

                }
                if (f1.isChecked()) {
                    flavor = f1.getText().toString();
                    if(flavor.equals("Vanilla"))
                    {
                        costFlavor = 0.25;
                    }
                    if(flavor.equals("Chocolate"))
                    {
                        costFlavor = 0.75;
                    }

                }
                if (f2.isChecked()) {
                    flavor = f2.getText().toString();
                    if(flavor.equals("Lemon"))
                    {
                        costFlavor = 0.25;
                    }
                    if(flavor.equals("Mint"))
                    {
                        costFlavor = 0.50;
                    }
                }

                if(size.equals("small"))
                {
                    costSize = 1.50;
                }
                if(size.equals("medium"))
                {
                    costSize = 2.50;
                }if(size.equals("large"))
                {
                    costSize = 3.25;
                }


                provinceName = prov.getText().toString();
                total = costSize + costFlavor + costAdOn;

                text = "For " + cusName + " from " + provinceName + ", a "+size+" "+selected+" "+flavor+" cost $"+total;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

            }


        });


        bSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (coffee.isChecked()) {
                    f1.setText("Vanilla");
                    f2.setText("Chocolate");
                }
                if (tea.isChecked()) {
                    f1.setText("Lemon");
                    f2.setText("Mint");
                }
            }
        });

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sizeBeverage = parent.getItemAtPosition(position).toString();
    }


}