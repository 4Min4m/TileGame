package com.example.am123;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.am123.R.drawable;

public class MainActivity extends Activity  {
customImageView image1;
customImageView image2;
customImageView image3;
customImageView image4;
customImageView image5;
customImageView image6;
customImageView image7;
customImageView image8;
customImageView image9;

Button resartButton;
TextView timerTextView;

    Handler handler=new Handler();

int[]  bmpArray;
boolean isFirst=true;
customImageView tempCustomImageView;
int score=0;
int bombRes= drawable.bomb;
int time=0;
boolean isGameOver=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmpArray= new int[9];        

        bmpArray[0]=drawable.a;
        bmpArray[1]=drawable.b;
        bmpArray[2]=drawable.c;
        bmpArray[3]=drawable.d;
        bmpArray[4]=drawable.a;
        bmpArray[5]=drawable.b;
        bmpArray[6]=drawable.c;
        bmpArray[7]=drawable.d;
        bmpArray[8]=drawable.bomb;
        
        
        bmpArray=randomize(bmpArray);
        
        
        InitView();
        fitInScreen();


        timerTextView.setText("0:0");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time++;
                int min=time/60;
                int sec=time%60;
                timerTextView.setText(min+":"+sec);
                if(!isGameOver)
                handler.postDelayed(this,1000);
            }
        },1000);





        
    }
    private int[] randomize(int[] bmparr){
		Random rgen = new Random();  // Random number generator			
		 
		for (int i=0; i<bmparr.length; i++) {
		    int randomPosition = rgen.nextInt(bmparr.length);
		    int temp = bmparr[i];
		    bmparr[i] = bmparr[randomPosition];
		    bmparr[randomPosition] = temp;
		}
 
		return bmparr;
    }
    private void fitInScreen(){
        android.view.Display display = ((android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
    	LinearLayout.LayoutParams llp1=new LinearLayout.LayoutParams((display.getWidth()*25)/100, (display.getWidth()*25)/100);
    	
    	llp1.gravity= Gravity.CENTER;
        
    	image1.setLayoutParams(llp1);
        image2.setLayoutParams(llp1);
        image3.setLayoutParams(llp1);
        image4.setLayoutParams(llp1);
        image5.setLayoutParams(llp1);
        image6.setLayoutParams(llp1);
        image7.setLayoutParams(llp1);
        image8.setLayoutParams(llp1);
        image9.setLayoutParams(llp1);


    }
    private void InitView(){
        image1=(customImageView)findViewById(R.id.image1);
        image2=(customImageView)findViewById(R.id.image2);
        image3=(customImageView)findViewById(R.id.image3);
        image4=(customImageView)findViewById(R.id.image4);
        image5=(customImageView)findViewById(R.id.image5);
        image6=(customImageView)findViewById(R.id.image6);
        image7=(customImageView)findViewById(R.id.image7);
        image8=(customImageView)findViewById(R.id.image8);
        image9=(customImageView)findViewById(R.id.image9);



        image1.context=getBaseContext();
        image2.context=getBaseContext();
        image3.context=getBaseContext();
        image4.context=getBaseContext();
        image5.context=getBaseContext();
        image6.context=getBaseContext();
        image7.context=getBaseContext();
        image8.context=getBaseContext();
        image9.context=getBaseContext();
    	
    	
    	image1.setOnClickListener(onclick());
    	image2.setOnClickListener(onclick());
    	image3.setOnClickListener(onclick());
    	image4.setOnClickListener(onclick());
    	image5.setOnClickListener(onclick());
    	image6.setOnClickListener(onclick());
    	image7.setOnClickListener(onclick());
    	image8.setOnClickListener(onclick());
    	image9.setOnClickListener(onclick());


        resartButton=(Button)findViewById(R.id.restartButton);

        resartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });


        timerTextView=(TextView)findViewById(R.id.timerTextView);

    }
    
private OnClickListener onclick(){
	return new View.OnClickListener() {
		
		@Override
		public void onClick(final View arg0) {
            int imageRes=bmpArray[Integer.parseInt(((customImageView)arg0).getTag().toString())-1];
            if (((customImageView)arg0).isFreez() || ((customImageView)arg0).tempFreez || ((customImageView)arg0).equals(tempCustomImageView) ) {
               
                return;
            }

                ((customImageView) arg0).startFlip(true,imageRes);
            if (((customImageView)arg0).face==bombRes){
                gameOver(true);
                return;
            }

            if (isFirst){

                tempCustomImageView=((customImageView) arg0);
                isFirst=false;
            }else{
                freezAll();

                int tempImageRes=bmpArray[Integer.parseInt(tempCustomImageView.getTag().toString())-1];

                if (tempImageRes==imageRes){
                    tempCustomImageView.setFreez();
                    ((customImageView) arg0).setFreez();
                    score++;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    unFreezAll();
                                }
                            });
                        }
                    },2500);
                        if (score==4) gameOver(false);
                }else{
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tempCustomImageView.startFlip(false, 0);
                                    ((customImageView) arg0).startFlip(false, 0);
                                    unFreezAll();

                                }
                            });
                        }
                    }, 2500);

                }
                isFirst=true;
            }

			
			
		}
	};

}


private void freezAll(){
    image1.tempFreez=true;
    image2.tempFreez=true;
    image3.tempFreez=true;
    image4.tempFreez=true;
    image5.tempFreez=true;
    image6.tempFreez=true;
    image7.tempFreez=true;
    image8.tempFreez=true;
    image9.tempFreez=true;}
private void unFreezAll(){
        image1.tempFreez=false;
        image2.tempFreez=false;
        image3.tempFreez=false;
        image4.tempFreez=false;
        image5.tempFreez=false;
        image6.tempFreez=false;
        image7.tempFreez=false;
        image8.tempFreez=false;
        image9.tempFreez=false;

    }

private void gameOver(boolean lost){
	isGameOver=true;
    final Dialog dialog=new Dialog(this);
    dialog.setContentView(R.layout.dialog_layout);
    TextView timeTextView=(TextView)dialog.findViewById(R.id.gameOverDialogTimeTextView);
    TextView bestScoreTextView =(TextView)dialog.findViewById(R.id.bestScore);
    ImageButton resetButton=(ImageButton)dialog.findViewById(R.id.restartButton);
    
    SharedPreferences shp =getSharedPreferences("score",MODE_WORLD_WRITEABLE);
    
    resetButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
            
        	recreate();
            dialog.dismiss();
        }
    });
    if (lost){
        timeTextView.setText("you Lost");
    }else{
        timeTextView.setText("you win. your time "+ time/60+":"+time%60);
        bestScoreTextView.setText("");
         
            if (shp.getInt("bestScore",0)<time){
            SharedPreferences.Editor shpeditor=shp.edit();
                shpeditor.putInt("bestScore",time).commit();
            }
    }
    int x=shp.getInt("bestScore", 0);
    if (x!=0)
    bestScoreTextView.setText(x/60+":"+x%60);
    
        dialog.setCancelable(false);
   
        dialog.show();
}

}


