package com.hafsa.finalproject.machinslotgame.ImageViewScrolling;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hafsa.finalproject.machinslotgame.R;

public class ImageViewScrolling extends FrameLayout {

    private  static int ANIMATION_DUR=150;
    int last_result=0,old_value=0;
    ImageView current_image,next_image;

    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling,this);
        current_image=findViewById(R.id.currennt_image);
        next_image=findViewById(R.id.next_image);

        next_image.setTranslationY(getHeight());

    }


    public void setValueRandom(final int image, final int rotate_count){

        current_image.animate().translationY(-getHeight()).setDuration(ANIMATION_DUR).start();
        next_image.setTranslationY(next_image.getHeight());
        next_image.animate().translationY(0).setDuration(ANIMATION_DUR).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                setImage(current_image,old_value%6); // because we have 6 images ,we will need to mod for 6
                current_image.setTranslationY(0);
                if(old_value!=rotate_count){
                     //if old value still not equal rotate count,we wiil rool
                    setValueRandom(image,rotate_count);
                    old_value++;
                } else  //if rotate is reached

                    {

                    last_result=0;
                    old_value=0;
                    setImage(next_image,image);
                     eventEnd.eventEnd(image%6,rotate_count);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void setImage(ImageView image_view, int value) {
            if(value==Util.BAR){

                image_view.setImageResource(R.drawable.bar_done);
            }
            else if(value==Util.SEVEN){
                image_view.setImageResource(R.drawable.sevent_done);
            }else if(value==Util.LEMON){
                image_view.setImageResource(R.drawable.lemon_done);
            }else if(value==Util.ORANGE){
                image_view.setImageResource(R.drawable.orange_done);
            }else if(value==Util.TRIPLE){
                image_view.setImageResource(R.drawable.triple_done);
            }else if(value==Util.WATERMELON){
                image_view.setImageResource(R.drawable.waternelon_done);
            }
            image_view.setTag(value);
            last_result=value;

    }

    public  int getValue(){
        return  Integer.parseInt(next_image.getTag().toString());

    }
}
