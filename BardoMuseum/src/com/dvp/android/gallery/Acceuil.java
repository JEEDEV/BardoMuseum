package com.dvp.android.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Acceuil extends Activity {

		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.acceuil);
	        Button btn1 = (Button)findViewById(R.id.button1);
	        Button btn2 = (Button)findViewById(R.id.button2);
	        Button btn3 = (Button)findViewById(R.id.button3);
	        Button btn4 = (Button)findViewById(R.id.button4);
	        Button btn5 = (Button)findViewById(R.id.button5);


           btn1.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Acceuil.this,Galeries.class);
                   startActivity(intent);
               }
           });

            btn2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Acceuil.this,Objets.class);
                    startActivity(intent);
                }
            });

            btn3.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Acceuil.this,Evennements.class);
                    startActivity(intent);
                }
            });

            btn4.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Acceuil.this,Infos.class);
                    startActivity(intent);
                }
            });
            btn5.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Acceuil.this,GPS.class);
                    startActivity(intent);
                }
            });


	}}


		                               







	


