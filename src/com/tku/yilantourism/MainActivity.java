package com.tku.yilantourism;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton b1 = (ImageButton)this.findViewById(R.id.imageButton1);
        b1.setOnClickListener(new Button.OnClickListener(){
       

			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new  Intent();
			   
			    intent.setClass(MainActivity.this, TourMapActivity.class);
			    startActivity(intent);
			   
			 
			}
        	
        });
        ImageButton b2 = (ImageButton)this.findViewById(R.id.imageButton2);
        b2.setOnClickListener(new Button.OnClickListener(){
       

			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new  Intent();
			    intent.setClass(MainActivity.this, TrafficActivity.class);
			    
			    startActivity(intent);
			    
			}
        	
        });
        ImageButton b3 = (ImageButton)this.findViewById(R.id.imageButton3);
        b3.setOnClickListener(new Button.OnClickListener(){
       

			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new  Intent();
			    intent.setClass(MainActivity.this, WeatherActivity.class);
			    startActivity(intent);
			    
			}
        	
        });
        ImageButton b4 = (ImageButton)this.findViewById(R.id.imageButton4);
        b4.setOnClickListener(new Button.OnClickListener(){
       

			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new  Intent();
			    intent.setClass(MainActivity.this, ScheduleActivity.class);
			    startActivity(intent);
			    
			}
        	
        });
        ImageButton b5 = (ImageButton)this.findViewById(R.id.imageButton5);
        b5.setOnClickListener(new Button.OnClickListener(){
       

			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new  Intent();
			    intent.setClass(MainActivity.this, AboutUSActivity.class);
			    startActivity(intent);
			    
			}
        	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
