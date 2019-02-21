package com.labs.fitnesscenter;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int Age = 5;
    int gender = 0;
    float bmiCalculate = 0.0f;

    String genderString = "";
    String nameString = "";
    String ageString = "";
    String functionType = "";
    String CategoryTest = "";

    TextView txt_Name;
    TextView txt_Age;
    TextView txt_Gender;
    TextView txt_Category;
    TextView txt_Risk;
    TextView txt_BMI;

    EditText editText_Height;
    EditText editText_Inches;
    EditText editText_Weight;

    ImageView image;

    Button btn_CancelOD;
    Button btn_EnterOD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void giveYourInfo(View view, String buttonType) {

        if (!nameString.equals("") && !ageString.equals("") && !genderString.equals("")) {

            float user_Height = 0.0f;
            float user_Inch = 0.0f;
            float user_Weight = 0.0f;

            try {

                txt_Name = findViewById(R.id.editText_Name);
                txt_Age = findViewById(R.id.editText_Age);
                txt_Gender = findViewById(R.id.editText_Gender);
                txt_Category = findViewById(R.id.editText_Category);
                txt_Risk = findViewById(R.id.editText_Risk);
                image = findViewById(R.id.iv_BmiResults);

                txt_Name.setText(nameString);
                txt_Age.setText(ageString);
                txt_Gender.setText(genderString);

                editText_Height = findViewById(R.id.editText_Feet);
                if (editText_Height != null && !editText_Height.getText().toString().equals("") && editText_Height.getText() != null) {
                    user_Height = Float.valueOf(editText_Height.getText().toString());
                }

                editText_Inches = findViewById(R.id.textBox_Inches);
                if (editText_Inches != null && !editText_Inches.getText().toString().equals("") && editText_Inches.getText() != null) {
                    user_Inch = Float.valueOf(editText_Inches.getText().toString());
                }
                float body_inch = (user_Height * 12) + user_Inch;

                editText_Weight = findViewById(R.id.editText_Weight);
                if (editText_Weight != null && !editText_Weight.getText().toString().equals("") && editText_Weight.getText() != null) {
                    user_Weight = Float.valueOf(editText_Weight.getText().toString());
                }

                if (body_inch != 0 && user_Weight != 0) {
                    bmiCalculate = (user_Weight / (body_inch * body_inch)) * 703;

                    txt_BMI = findViewById(R.id.txt_BmiIndicator);
                    txt_BMI.setTextColor(Color.BLACK);

                    if (buttonType == "BMITYPE") {

                        txt_BMI.setText("");
                        txt_BMI.setText("BMI: " + String.format("%.2f", bmiCalculate));

                        if (bmiCalculate < 15) {
                            CategoryTest = "Very severely underweight ";
                            txt_BMI.setBackgroundColor(Color.YELLOW);

                        } else if (bmiCalculate >= 15 && bmiCalculate < 18.5) {
                            CategoryTest = "Very underweight ";
                            txt_BMI.setBackgroundColor(Color.YELLOW);

                        } else if (bmiCalculate > 18.5 && bmiCalculate <= 25) {
                            CategoryTest = "Normal (healthy weight) ";
                            txt_BMI.setBackgroundColor(Color.GREEN);

                        } else if (bmiCalculate > 25 && bmiCalculate <= 30) {
                            CategoryTest = "Overweight ";
                            txt_BMI.setBackgroundColor(Color.RED);

                        } else if (bmiCalculate > 30 && bmiCalculate <= 35) {
                            CategoryTest = "Obese Class I ";
                            txt_BMI.setBackgroundColor(Color.RED);

                        } else if (bmiCalculate > 35 && bmiCalculate <= 40) {
                            CategoryTest = "Obese Class II ";
                            txt_BMI.setBackgroundColor(Color.RED);

                        } else if (bmiCalculate > 40) {
                            CategoryTest = "Obese Class III ";
                            txt_BMI.setBackgroundColor(Color.RED);
                        }
                    }

                    txt_Category.setText(CategoryTest);

                    if (bmiCalculate > 27.5) {
                        txt_Risk.setText("High risk of developing heart disease, hgh blood pressure, stroke, diabetes.");
                    } else if (bmiCalculate > 23 && bmiCalculate <= 27.4) {
                        txt_Risk.setText("Moderate risk of developing heart disease, hgh blood pressure, stroke, diabetes.");
                    } else if (bmiCalculate > 18.5 && bmiCalculate <= 22.9) {
                        txt_Risk.setText("Low Risk (healthy range).");
                    } else if (bmiCalculate <= 18.4) {
                        txt_Risk.setText("Risk of developing problems such as nutritional deficiency and osteoporosis.");
                    }

                    if (bmiCalculate <= 17.5) {
                        if (gender == 1)
                            image.setImageResource(R.drawable.mimg1);
                        else if (gender == 0)
                            image.setImageResource(R.drawable.img1);
                    } else if (bmiCalculate > 17.5 && bmiCalculate <= 18.5) {
                        if (gender == 1)
                            image.setImageResource(R.drawable.mimg2);
                        else if (gender == 0) image.setImageResource(R.drawable.img2);
                    } else if (bmiCalculate > 18.5 && bmiCalculate <= 22) {
                        if (gender == 1)
                            image.setImageResource(R.drawable.mimg3);
                        else if (gender == 0) image.setImageResource(R.drawable.img3);
                    } else if (bmiCalculate > 22 && bmiCalculate <= 24.9) {
                        if (gender == 1)
                            image.setImageResource(R.drawable.mimg4);
                        else if (gender == 0) image.setImageResource(R.drawable.img4);
                    } else if (bmiCalculate > 24.9 && bmiCalculate <= 30) {
                        if (gender == 1)
                            image.setImageResource(R.drawable.mimg5);
                        else if (gender == 0) image.setImageResource(R.drawable.img5);
                    } else if (bmiCalculate > 30) {
                        if (gender == 1)
                            image.setImageResource(R.drawable.mimg6);
                        else if (gender == 0) image.setImageResource(R.drawable.img6);
                    }


                    if (buttonType == "BodyWtType") {
                        float bodyFat = (bmiCalculate * 1.2f) + (Age * 0.23f) - (gender * 10.8f) - 5.4f;

                        txt_BMI.setText("");
                        txt_BMI.setText("Body Fat: \n" + String.format("%.2f", bodyFat));
                    }

                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please give your height/weight.",
                            Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Log.e("E1", e.getStackTrace().toString());
            }

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Please fill all the field(s) under Options button",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void calculate_BMI(View view) {
        giveYourInfo(view, "BMITYPE");

    }

    public void calculate_BodyFat(View view) {
        giveYourInfo(view, "BodyWtType");

    }

    public void popup(View view) {
        final Dialog MyDialog = new Dialog(MainActivity.this);
        MyDialog.setContentView(R.layout.optionsdialog);
        MyDialog.setTitle("My Custom Dialog");

        btn_EnterOD = MyDialog.findViewById(R.id.btn_EnterOD);
        btn_EnterOD.setEnabled(true);
        btn_EnterOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    nameString = ((EditText) MyDialog.findViewById(R.id.editText_NameOD)).getText().toString();
                    ageString = ((EditText) MyDialog.findViewById(R.id.editText_AgeOD)).getText().toString();
                    Age = Integer.valueOf(ageString);

                    RadioButton rb_f = MyDialog.findViewById(R.id.rb_FemaleOD);
                    RadioButton rb_m = MyDialog.findViewById(R.id.rb_MaleOD);
                    if (rb_m.isChecked()) {
                        gender = 1;
                        genderString = "Male";
                    } else if (rb_f.isChecked()) {
                        gender = 0;
                        genderString = "Female";
                    }


                    Toast.makeText(
                            getApplicationContext(),
                            "Name: " + nameString + "\nAge: " + ageString + "\nGender: " + genderString,

                            Toast.LENGTH_LONG).show();
                    MyDialog.dismiss();

                } catch (Exception ex) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please fill out all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_CancelOD = MyDialog.findViewById(R.id.btn_CancelOD);
        btn_CancelOD.setEnabled(true);
        btn_CancelOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });
        MyDialog.show();
    }

    public void reset(View view) {

        genderString = "";
        nameString = "";
        ageString = "";
        txt_Name = findViewById(R.id.editText_Name);
        txt_Age = findViewById(R.id.editText_Age);
        txt_Gender = findViewById(R.id.editText_Gender);
        txt_Risk =  findViewById(R.id.editText_Risk);
        txt_BMI = findViewById(R.id.txt_BmiIndicator);
        image = findViewById(R.id.iv_BmiResults);

        txt_Name.setText("");
        txt_Age.setText("");
        txt_Gender.setText("");
        txt_Category.setText("");
        txt_Risk.setText("");
        txt_BMI.setText("BMI -");

        image.setImageDrawable(null);

        txt_BMI.setBackgroundColor(Color.TRANSPARENT);
        txt_BMI.setTextColor(Color.WHITE);
        functionType = "";
        editText_Height.setText("");
        editText_Inches.setText("");
        editText_Weight.setText("");
        CategoryTest = "";
        bmiCalculate = 0.0f;
    }
}
