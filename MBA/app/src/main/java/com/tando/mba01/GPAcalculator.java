package com.tando.mba01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GPAcalculator extends AppCompatActivity {
    //Declare an array to store units type is double
    public static ArrayList<Double> unitsAdd = new ArrayList<Double>();
    //Declare an array to store grade points
    public static ArrayList<Double> gradeAdd = new ArrayList<Double>();
    //Declare an array to store the result of units * gradepoints
    public static ArrayList<Double> gradePoints = new ArrayList<Double>();
    //Declare the spinners
    Spinner spinner1, spinner2;
    //Declare adapter to connect array in string value
    ArrayAdapter<CharSequence> adapter1;
    //Adapter 2
    ArrayAdapter<CharSequence> adapter2;
    //Declare some double variables
    Double numOfUnits, numOfGrades, gradepoints, totalGPA, totalGrades = 0.00, totalUnits = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);

        //Link spinners to their IDs
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        //link adaters to their strings and style the drop-down menu
        adapter1 = ArrayAdapter.createFromResource(GPAcalculator.this, R.array.units,
                android.R.layout.simple_spinner_item);
        //Set Drop-down style
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2 = ArrayAdapter.createFromResource(GPAcalculator.this, R.array.grade_points,
                android.R.layout.simple_spinner_item);
        //Set Drop-down style
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //display the adapter contain the lists
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        //declare buttons
        Button bntInsert = (Button) findViewById(R.id.bntInsert);
        Button bntCountGPA = (Button) findViewById(R.id.calcGPA);
        Button bntClear = (Button) findViewById(R.id.bntClear);

        //textView for total units
        final TextView resultTotalUnits = (TextView) findViewById(R.id.resultTotalUnits);
        //textView for total grade points
        final TextView resultTotalGrades = (TextView) findViewById(R.id.resultTotalGrades);
        //textView for GPA
        final TextView resultGPA = (TextView) findViewById(R.id.gpa);
        //Insert onClick function
        bntInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare a string for selected Unit_items
                String unitSelected = spinner1.getSelectedItem().toString();
                //Covert string to double
                numOfUnits = Double.parseDouble(unitSelected);
                //add double value into array
                unitsAdd.add(numOfUnits);
                //Declare a string for selected Grades_items
                String gradeSelected = spinner2.getSelectedItem().toString();
                //numOfGrades = Double.parseDouble(gradeSelected);
                if (gradeSelected.equals("A")){
                    numOfGrades = 4.00;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("A-")) {
                    numOfGrades = 3.70;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("B+")) {
                    numOfGrades = 3.33;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("B")) {
                    numOfGrades = 3.00;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("B-")) {
                    numOfGrades = 2.70;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("C+")) {
                    numOfGrades = 2.30;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("C")) {
                    numOfGrades = 2.00;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("C-")) {
                    numOfGrades = 1.70;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("D+")) {
                    numOfGrades = 1.30;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("D")) {
                    numOfGrades = 1.00;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("D-")) {
                    numOfGrades = 0.70;
                    gradeAdd.add(numOfGrades);
                }
                else if (gradeSelected.equals("F")) {
                    numOfGrades = 0.00;
                    gradeAdd.add(numOfGrades);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error!!!", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(),
                        "You have inserted: " + numOfUnits + " units and " + numOfGrades + " Grade Points",
                        Toast.LENGTH_SHORT).show();
                //Function of GradePoints
                gradepoints = numOfUnits * numOfGrades;
                if (gradepoints >= 0) {
                    gradePoints.add(gradepoints);
                } else {
                    Toast.makeText(getApplicationContext(), "Error!!!", Toast.LENGTH_LONG).show();
                }
                spinner1.setSelection(0);
                spinner2.setSelection(0);
            }
        });

        //Count GPA onClick function
        bntCountGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat format = new DecimalFormat("#.#");
                //Have to declare totalUnits = 0.00 from the beginning
                for(int i=0;i<unitsAdd.size();i++){
                    //sum of all elements(units) in the array called units
                    totalUnits +=  (unitsAdd.get(i));
                }
                //sum of all elements(gradePoint = numOfUnits * numOfGrades) in the array gradePoints
                for(int j=0;j<gradePoints.size();j++){
                    totalGrades += (gradePoints.get(j));
                }
                //Count GPA
                totalGPA = totalGrades / totalUnits;
                //Display the results in the TextView sections
                resultTotalUnits.setText("Total units: "+Double.toString(totalUnits));
                //Display total gradePoints
                resultTotalGrades.setText("Total grade points: "+format.format(totalGrades));
                //Display GPA
                resultGPA.setText("GPA: "+format.format(totalGPA));
                //Clear the array after button clicked
                totalGPA = 0.00;
                totalUnits = 0.00;
                totalGrades = 0.00;
                unitsAdd.clear();
                gradeAdd.clear();
                gradePoints.clear();

            }
        });

        //Clear function
        bntClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setSelection(0);
                spinner2.setSelection(0);
                resultTotalUnits.setText("empty");
                resultTotalGrades.setText("empty");
                resultGPA.setText("empty");
                gradePoints.clear();
                unitsAdd.clear();
                gradeAdd.clear();
                gradePoints.clear();
                Toast.makeText(GPAcalculator.this, "Clear!", Toast.LENGTH_LONG).show();
            }
        });

    }


    //back to homepage logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
    //link to Facebook page
    public void fbFunc (View viewFb) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cbpamba")));
    }
    //link to Instagram page
    public void igFunc (View viewIg) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mba_csusb/")));
    }
    //link to Linkedin page
    public void lnFunc (View viewLn) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/mbacsusb/")));
    }
    //link to Twitter page
    public void twFunc (View viewTw) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MBACSUSB")));
    }
}
