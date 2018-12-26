package com.example.dustinchen.othello_chen_deshun;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by dustinchen on 22/10/15.
 */

public class Card extends FrameLayout{

    public Card(Context context) {
        super(context);
        chess = new ImageView(context);
        chessBackground = new ImageView(context);
        chessBackground.setBackgroundColor(0x66999933);
        animation1.setDuration(500);
        animation2.setDuration(500);

        chess.setScaleType(ImageView.ScaleType.FIT_CENTER);

        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(4, 4, 0, 0);

        addView(chessBackground, lp);
        addView(chess, lp);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                chess.setAnimation(null);
                chess.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                chess.setAnimation(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private int num = 0;


    public int getNum() {
        return num;
    }

    public void setNum(int Chessnumber) {
        if(num != Chessnumber){

            chess.startAnimation(animation1);
            chess.setImageResource(ChessStyle[Chessnumber]);
            chess.setVisibility(View.VISIBLE);
            chess.startAnimation(animation2);
        }
        else{
            chess.setImageResource(ChessStyle[Chessnumber]);
        }

        num = Chessnumber;
    }

    public void setHint(){
        chess.setImageResource(ChessStyle[4]);
    }
    public void moveHInt() {chess.setImageResource(ChessStyle[0]);}

    public boolean equals(Card o) {
        return getNum() == o.getNum();
    }


    private ImageView chess;
    private ImageView chessBackground;

    private ScaleAnimation animation1 = new ScaleAnimation(1, 0, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.5f,Animation.RELATIVE_TO_PARENT, 0.5f );

    private ScaleAnimation animation2 = new ScaleAnimation(0, 1, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.5f,Animation.RELATIVE_TO_PARENT, 0.5f );

    private int[] ChessStyle = {
            R.drawable.transparent,
            R.drawable.black_chess,
            R.drawable.white_chess,
            R.drawable.transparent,
            R.drawable.black_chess_t};
}
