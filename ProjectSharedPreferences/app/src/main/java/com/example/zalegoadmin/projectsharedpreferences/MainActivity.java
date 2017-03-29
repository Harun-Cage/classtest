package com.example.zalegoadmin.projectsharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button submit,clear;
    EditText namme;
    TextView blank;
    localstorege storeRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeRep= new localstorege(this);

        submit=(Button)findViewById(R.id.submitBtn);

        clear=(Button)findViewById(R.id.clearBtn);

        namme=(EditText)findViewById(R.id.nameET);

        blank=(TextView)findViewById(R.id.blankTV);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = namme.getText().toString();
                storeRep.storeData(name);

              String nameSaved=  storeRep.getData();
                namme.setText(nameSaved);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeRep.clearData();

            }
        });

    }
}
