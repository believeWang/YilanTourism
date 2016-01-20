package com.tku.yilantourism;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutUSActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		Button b2 = (Button)this.findViewById(R.id.button2);
        b2.setOnClickListener(new Button.OnClickListener(){
       

			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();
			    
			}
        	
        });
	}

	

}
