package com.example.leason.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class calculator extends AppCompatActivity {
    private Button Num[ ] = new Button[10];
    private Button  Clr, add, minus, multi, divide, equ;
    private boolean clr_flag = false;  // after count   ,when input then clear the textview
    private TextView result_text;
    private int btn[ ] = { R.id.btn_zero, R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_seven, R.id.btn_eight, R.id.btn_nine };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        for(int i=0; i<10; i++){
             Num[i] = (Button)findViewById(btn[i]);
        }

        add = (Button)findViewById(R.id.btn_add);
        minus = (Button)findViewById(R.id.btn_minus);
        multi = (Button)findViewById(R.id.btn_mutli);
        divide = (Button)findViewById(R.id.btn_divide);
        equ = (Button)findViewById(R.id.btn_equ);
        Clr = (Button)findViewById(R.id.btn_clr);
        result_text = (TextView)findViewById(R.id.TextView_result);
        result_text.setText("0");
        Clr.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                result_text.setText("0");
            }
        } );
        for(int i = 0; i<10; i++) {
            Num[i].setOnClickListener(NumClick);
        }
        add.setOnClickListener(NumClick);
        minus.setOnClickListener(NumClick);
        multi.setOnClickListener(NumClick);
        divide.setOnClickListener(NumClick);
        equ.setOnClickListener(equclick);
    }
    private Button.OnClickListener NumClick=new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sel = result_text.getText().toString();
            Button btn = (Button)findViewById(v.getId());
            if(sel.equals("0")) {
                if (!(btn.getText().toString().equals("X") || btn.getText().toString().equals("÷") || btn.getText().toString().equals("+") || btn.getText().toString().equals("0"))) {
                    // check the correct input
                    result_text.setText(btn.getText());
                }
                clr_flag = false;
            }
            else if(clr_flag == true) {
                if (btn.getText().toString().equals("X") || btn.getText().toString().equals("÷")||btn.getText().toString().equals("+")||btn.getText().toString().equals("-")) {
                    //contiune input
                    result_text.setText(sel + btn.getText());
                }
                else {
                    //clear textview
                    result_text.setText(btn.getText());
                }
                clr_flag = false;
            }
            else {
                result_text.setText(sel + btn.getText());
            }
        }

    };

    private Button.OnClickListener equclick = new Button.OnClickListener() {

        int searchmain_locate;
        String resstring;
        int after_locate,before_locate;
        int num1,num2;

        public void range() {
                // find the point for the next & last sign
            after_locate =resstring.length();
            before_locate=0;
            try {
                if (resstring.indexOf("X", searchmain_locate + 1) != -1) {
                    after_locate = resstring.indexOf("X", searchmain_locate + 1);
                }
                if (resstring.indexOf("÷", searchmain_locate + 1) != -1) {
                    after_locate = resstring.indexOf("÷", searchmain_locate + 1) < after_locate ? resstring.indexOf("÷", searchmain_locate + 1) : after_locate;
                }
                if (resstring.indexOf("-", searchmain_locate + 1) != -1) {
                    if (resstring.indexOf("-", searchmain_locate + 1) == searchmain_locate + 1) {
                            // ex. 1+-1+1    sign"-" is negative not minus
                        if (resstring.indexOf("-", searchmain_locate + 2) != -1) {
                            //search next sign ,if it gave sign"-"
                            after_locate = resstring.indexOf("-", searchmain_locate + 2) < after_locate ? resstring.indexOf("-", searchmain_locate + 1) : after_locate;
                        }
                    }
                    else
                        after_locate = resstring.indexOf("-", searchmain_locate + 1) < after_locate ? resstring.indexOf("-", searchmain_locate + 1) : after_locate;
                }

                if (resstring.indexOf("+", searchmain_locate + 1) != -1) {
                    after_locate = resstring.indexOf("+", searchmain_locate + 1) < after_locate ? resstring.indexOf("+", searchmain_locate + 1) : after_locate;
                }
                if (resstring.lastIndexOf("X", searchmain_locate - 1) != -1) {
                    before_locate = resstring.lastIndexOf("X", searchmain_locate - 1);
                }
                if (resstring.lastIndexOf("÷", searchmain_locate - 1) != -1) {
                   before_locate = resstring.lastIndexOf("÷", searchmain_locate - 1) > before_locate ? resstring.lastIndexOf("÷", searchmain_locate - 1) : before_locate;
                }
                if (resstring.lastIndexOf("-", searchmain_locate - 1) != -1) {
                    before_locate = resstring.lastIndexOf("-", searchmain_locate - 1) > before_locate ? resstring.lastIndexOf("-", searchmain_locate - 1) : before_locate;
                }
                if (resstring.lastIndexOf("+", searchmain_locate - 1) != -1) {
                    before_locate = resstring.lastIndexOf("+", searchmain_locate - 1) > before_locate ? resstring.lastIndexOf("+", searchmain_locate - 1) : before_locate;
                }

                if (before_locate == 0) {
                   // ex. 1+1, -1+1
                    num1 = Integer.parseInt(resstring.substring(before_locate, searchmain_locate));
                }
                else {
                    num1 = Integer.parseInt(resstring.substring(before_locate + 1, searchmain_locate));
                }
                if (searchmain_locate + 1 == after_locate) {
                    //ex.  1+-1
                    num2 = Integer.parseInt(resstring.substring(searchmain_locate, after_locate));
                }
                else {
                    num2 = Integer.parseInt(resstring.substring(searchmain_locate + 1, after_locate));
                }
            }
            catch(Exception e){
                result_text.setText("0");
                Toast.makeText(calculator.this,"ERROR",Toast.LENGTH_SHORT).show();
                num1 = num2 = 0;
                clr_flag = false;
            }
        }
        public void res(){
            if(before_locate == 0) {
                //adjust the range for substring()  for same satus
                resstring = resstring.replace(resstring.substring(before_locate, after_locate), String.valueOf(num1));
            }
            else {
                resstring = resstring.replace(resstring.substring(before_locate + 1, after_locate), String.valueOf(num1));
            }
        }
        @Override
        public void onClick(View v) {
            resstring = result_text.getText().toString();
             while(true) {
                if ((searchmain_locate = resstring.indexOf("X")) != -1) {
                    range();
                    num1 = num1 * num2;
                    res();
                 }

                else if (((searchmain_locate = resstring.indexOf("÷")) != -1)) {
                    range();
                    num1 = num1 / num2;
                    res();
                }
                else if (((searchmain_locate = resstring.indexOf("+")) != -1)) {
                    range();
                    num1 = num1 + num2;
                    res();
                }
                 else if (((searchmain_locate = resstring.indexOf("-")) != -1)) {
                    if((searchmain_locate ==0)&&(searchmain_locate = resstring.indexOf("-",1)) == -1) {
                           //final result for negative
                             result_text.setText(resstring);
                             clr_flag = true;
                             return;
                    }
                     range();
                     num1 = num1 - num2;
                     res();
                 }


                else {
                    //final result
                     result_text.setText(resstring);
                     clr_flag = true;
                    return;
                }

             }


        }
    };
}
