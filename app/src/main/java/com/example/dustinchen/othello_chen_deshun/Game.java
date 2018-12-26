package com.example.dustinchen.othello_chen_deshun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {

    public Game() {
        Game = this;
    }

    private ImageButton buttonNewGame;
    private ChessBoard chessBoard;
    private TextView tvBlackCount;
    private TextView tvWhiteCount;
    private ImageView ivCurrentTurn;
    private Chronometer chTime;
    private ImageButton buttonRetract;
    private ImageButton buttonHint;

    public LinearLayout getLlDialog(int i) {
        LinearLayout llDialog = null;
        switch (i){
            case 0:
                llDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.draw,null);
                break;
            case 1:
                llDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.black_win,null);
                break;
            case 2:
                llDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.white_win,null);
                break;
            case 3:
                llDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.retract,null);
                break;
            case 4:
                llDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.new_game_layout,null);
                break;
            case 5:
                llDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.pass,null);
                break;
            default:
                break;
        }
        return llDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        chessBoard = (ChessBoard) findViewById(R.id.ChessBoard);
        buttonNewGame = (ImageButton) findViewById(R.id.new_game);
        tvBlackCount = (TextView) findViewById(R.id.black_count);
        tvWhiteCount = (TextView) findViewById(R.id.white_count);
        ivCurrentTurn = (ImageView) findViewById(R.id.current_turn);
        chTime = (Chronometer) findViewById(R.id.cosumed_time);
        buttonRetract = (ImageButton) findViewById(R.id.retract);
        buttonHint = (ImageButton) findViewById(R.id.ButtonHint);

        toast = Toast.makeText(this, "Only one move can be retracted!", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(this,"Please put the first move!",Toast.LENGTH_SHORT);

        chTime.setFormat("Timing: %s");
        chTime.setBase(SystemClock.elapsedRealtime());
        chTime.start();

        buttonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chessBoard.hint_switch();
            }
        });

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotfirstStep(false);
                LinearLayout ll = getLlDialog(4);

                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                builder.setMessage("Are you sure to start a new game？");
                builder.setView(ll);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        chessBoard.startNewGame();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        buttonRetract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!notfirstStep){
                    toast2.show();
                    return;
                }
                retractCount++;

                if(retractCount>=2){
                    toast.show();
                }
                else {
                    LinearLayout ll = getLlDialog(3);

                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setMessage("Are you sure to retract？");
                    builder.setView(ll);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chessBoard.retract();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            retractCount--;
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }

            }
        });
    }

    public void clearRecord(){
        tvBlackCount.setText("2");
        tvWhiteCount.setText("2");
        ivCurrentTurn.setImageResource(R.drawable.black_chess);
        chTime.setBase(SystemClock.elapsedRealtime());
        chTime.start();
    }

    public void setIvCurrentTurn(int n){
        ivCurrentTurn.setImageResource(chessColor[n]);
    }

    public void setTvBlackCount(int n){
        tvBlackCount.setText(n+"");
    }

    public void setTvWhiteCount(int n){
        tvWhiteCount.setText(n+"");
    }

    private static int[] chessColor = new int[] {0,R.drawable.black_chess,R.drawable.white_chess};
    private static Game Game;

    public void setRetractCount(int retractCount) {
        this.retractCount = retractCount;
    }

    private int retractCount=0;

    public  void setNotfirstStep(boolean b){ notfirstStep = b;}
    private Toast toast,toast2;
    private boolean notfirstStep=false;

    public static Game getGame() {
        return Game;
    }
}
