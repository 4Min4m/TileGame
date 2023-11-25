package com.example.am123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class mainmenu extends Activity {
    ImageView playGameImageView;
    ImageView exitImageView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        playGameImageView=(ImageView)findViewById(R.id.playGameImageView);
        exitImageView=(ImageView)findViewById(R.id.ExitImageView);


        playGameImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainmenu.this,MainActivity.class));
            }
        });
        
        exitImageView.setOnClickListener(new View.OnClickListener() {
        			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});

    }
}