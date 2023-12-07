package com.example.emicalc;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText P = (EditText) findViewById(R.id.editTextLoan);
        final EditText r = (EditText) findViewById(R.id.editTextRate);
        final EditText N = (EditText) findViewById(R.id.editTextTenure);
        final Switch s = (Switch) findViewById(R.id.switchYear);

        final EditText emi=(EditText)findViewById(R.id.editTextAns);
        Button emiBtn = (Button) findViewById(R.id.btnCalculate);
        Button resetBtn=(Button)findViewById(R.id.btnReset);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    String tenure = N.getText().toString();
                    int a=Integer.parseInt(tenure)/12 ;
                    N.setText(String.valueOf(a));
                } else {
                    String tenure = N.getText().toString();
                    int a=Integer.parseInt(tenure)*12 ;
                    N.setText(String.valueOf(a));
                }
            }
        });
        
        emiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loan = P.getText().toString();
                String rate = r.getText().toString();
                String tenure = N.getText().toString();
                if (TextUtils.isEmpty(loan)) {
                    P.setError("Enter loan amount");
                }

               else if (TextUtils.isEmpty(tenure)) {
                    N.setError("Enter the loan tenure");
                }else  if (TextUtils.isEmpty(rate)) {
                    r.setError("Enter rate");
                }else{
                    float p=Float.parseFloat(loan);
                    float r=Float.parseFloat(rate)/(12*100);
                    int t=0;
                    boolean isSwitch=s.isChecked();
                    if(s.isChecked()){
                        t=Integer.parseInt(tenure)*12;
                    }
                    else{
                        t=Integer.parseInt(tenure);
                    }
                    double e=p*r*(Math.pow(1+r,t))/(Math.pow(1+r,t)-1);

//               double e= doSomething(isSwitch,tenure,p,r);

                    emi.setText(String.valueOf(e));
                }

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                P.setText("");
                r.setText("");
                N.setText("");
            }
        });

    }
}
