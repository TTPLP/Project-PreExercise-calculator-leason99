package com.example.leason.calculator;

import android.location.GpsStatus;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class calculator extends AppCompatActivity {
    private Button  [ ] Num = new Button[10];
    private Button  Clr,add,minus,multi,divide,equ;
    boolean clr_flag=false;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Num[0]=(Button)findViewById(R.id.button11);
        Num[1]=(Button)findViewById(R.id.button);
        Num[2]=(Button)findViewById(R.id.button2);
        Num[3]=(Button)findViewById(R.id.button3);
        Num[4]=(Button)findViewById(R.id.button4);
        Num[5]=(Button)findViewById(R.id.button5);
        Num[6]=(Button)findViewById(R.id.button6);
        Num[7]=(Button)findViewById(R.id.button7);
        Num[8]=(Button)findViewById(R.id.button8);
        Num[9]=(Button)findViewById(R.id.button9);
        add=(Button)findViewById(R.id.button13);
        minus=(Button)findViewById(R.id.button14);
        multi=(Button)findViewById(R.id.button15);
        divide=(Button)findViewById(R.id.button16);
        equ=(Button)findViewById(R.id.button12);
        Clr=(Button)findViewById(R.id.button10);
        text=(TextView)findViewById(R.id.textView);
        text.setText("0");
        Clr.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("0");
            }

        });
        for(int i=0;i<10;i++)
            Num[i].setOnClickListener(NumClick);
        add.setOnClickListener(NumClick);
        minus.setOnClickListener(NumClick);
        multi.setOnClickListener(NumClick);
        divide.setOnClickListener(NumClick);
        equ.setOnClickListener(resclick);



    }
    private Button.OnClickListener NumClick=new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sel=text.getText().toString();
            Button btn=(Button)findViewById(v.getId());
            if(sel.equals("0")) {
                if (btn.getText().toString().equals("X") || btn.getText().toString().equals("÷")||btn.getText().toString().equals("+")||btn.getText().toString().equals("0")) ;
                else
                    text.setText(btn.getText());
                clr_flag=false;
            }
            else if(clr_flag==true) {
                if (btn.getText().toString().equals("X") || btn.getText().toString().equals("÷")||btn.getText().toString().equals("+")||btn.getText().toString().equals("-"))
                    text.setText(sel+btn.getText());
                else
                    text.setText(btn.getText());
                clr_flag=false;
            }
            else
                text.setText(sel+btn.getText());

            }

    };

    private Button.OnClickListener resclick=new Button.OnClickListener() {

        int searchmain;
        String resstring;
        int after,before;
        int num1,num2;

        public void range() {

            after=resstring.length();
            before=0;
            try {
                if (resstring.indexOf("X", searchmain + 1) != -1)
                    after = resstring.indexOf("X", searchmain + 1);
                if (resstring.indexOf("÷", searchmain + 1) != -1)
                    after = resstring.indexOf("÷", searchmain + 1) < after ? resstring.indexOf("÷", searchmain + 1) : after;
                if (resstring.indexOf("-", searchmain + 1) != -1) {
                    if (resstring.indexOf("-", searchmain + 1) == searchmain + 1) {
                        if (resstring.indexOf("-", searchmain + 2) != -1)
                            after = resstring.indexOf("-", searchmain + 2) < after ? resstring.indexOf("-", searchmain + 1) : after;
                    }
                    else
                        after = resstring.indexOf("-", searchmain + 1) < after ? resstring.indexOf("-", searchmain + 1) : after;
                }

                if (resstring.indexOf("+", searchmain + 1) != -1)
                    after = resstring.indexOf("+", searchmain + 1) < after ? resstring.indexOf("+", searchmain + 1) : after;
                if (resstring.lastIndexOf("X", searchmain - 1) != -1)
                    before = resstring.lastIndexOf("X", searchmain - 1);
                if (resstring.lastIndexOf("÷", searchmain - 1) != -1)
                    before = resstring.lastIndexOf("÷", searchmain - 1) > before ? resstring.lastIndexOf("÷", searchmain - 1) : before;
                if (resstring.lastIndexOf("-", searchmain - 1) != -1)

                    before = resstring.lastIndexOf("-", searchmain - 1) > before ? resstring.lastIndexOf("-", searchmain - 1) : before;
                if (resstring.lastIndexOf("+", searchmain - 1) != -1)
                    before = resstring.lastIndexOf("+", searchmain - 1) > before ? resstring.lastIndexOf("+", searchmain - 1) : before;


                if (before == 0)
                    num1 = Integer.parseInt(resstring.substring(before, searchmain));
                else
                    num1 = Integer.parseInt(resstring.substring(before + 1, searchmain));
                if (searchmain + 1 == after)
                    num2 = Integer.parseInt(resstring.substring(searchmain, after));
                else
                    num2 = Integer.parseInt(resstring.substring(searchmain + 1, after));
            }
            catch(Exception e){
                text.setText("0");
                Toast.makeText(calculator.this,"ERROR",Toast.LENGTH_SHORT).show();
                num1=num2=0;
                clr_flag=false;
                return;
            }
        }
        public void res(){
            if(before==0)
                resstring=resstring.replace(resstring.substring(before, after), String.valueOf(num1));
            else
                resstring=resstring.replace(resstring.substring(before + 1, after), String.valueOf(num1));

        }
        @Override
        public void onClick(View v) {



            resstring=text.getText().toString();

             while(true) {
                if ((searchmain = resstring.indexOf("X")) != -1) {
                    range();
                    num1 = num1 * num2;
                    res();

                 }

                else if (((searchmain = resstring.indexOf("÷")) != -1)) {
                    range();
                    num1 = num1 / num2;
                    res();
                }
                else if (((searchmain =resstring.indexOf("+")) != -1)) {
                    range();
                    num1 = num1 + num2;
                    res();
                }
                 else if (((searchmain =resstring.indexOf("-")) != -1)) {
                    if(searchmain==0) {
                         if((searchmain =resstring.indexOf("-",1)) != -1);
                         else {
                             text.setText(resstring);
                             clr_flag=true;
                             return;
                         }
                    }

                     range();
                     num1 = num1 - num2;
                     res();
                 }


                else {
                    text.setText(resstring);
                    clr_flag=true;
                    return;
                }

             }


        }
    };
}
