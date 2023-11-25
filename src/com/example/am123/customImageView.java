package com.example.am123;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class customImageView extends ImageView implements Animation.AnimationListener  {
    Context context;
    boolean isMiddle=false;
    boolean showFace;
    int tile=R.drawable.ic_launcher;
    int face;
    private boolean freez=false;
    public boolean tempFreez=false;
    public customImageView(Context context) {

        super(context);
        this.context= context;
    }
    public customImageView(Context context, AttributeSet attrs, int defStyle,
                           int mode){
        super(context, attrs, defStyle);

    }
    public customImageView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (isMiddle){
            if (showFace){
                setImageResource(face);

            }
            else{
                setImageResource(tile);

            }


            clearAnimation();
            Animation anim2=AnimationUtils.loadAnimation(context,R.anim.flip2);
            anim2.setAnimationListener(this);
            setAnimation(anim2);
            startAnimation(anim2);
            isMiddle=false;

        }
        tempFreez=false;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public void startFlip(boolean showFace,int face){

        this.face=face;
        this.showFace=showFace;
        clearAnimation();
        Animation anim1= AnimationUtils.loadAnimation(context,R.anim.flip1);
        anim1.setAnimationListener(this);
        setAnimation(anim1);
        startAnimation(anim1);
        isMiddle=true;





    }
    public void setFreez(){
        freez=true;
    }
    public boolean isFreez(){
        return freez;
    }
    
   
}
