package com.mad.meatelicious;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * This is my TimerActivity class.
 * <p>
 * This will show the user a timer. This is a built in timer allowing the user to have a count down function in only a few clicks
 * when time is up, a ringtone alarm will ring.
 */

public class TimerActivity extends AppCompatActivity {

    // Timer
    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    public Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timer);

        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        /////////////////////////////////////////////////////////////////////

    }

    /**
     * Timer functions
     */

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        long mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                alartUser();
                updateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }

    private void alartUser(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Your time is up! ");
        dialog.setTitle("Dialog Box");
        playSound();
        dialog.setPositiveButton("YES",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Enjoy! ",Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void playSound() {
        MediaPlayer alarm= MediaPlayer.create(TimerActivity.this,R.raw.ring);
        alarm.start();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        mTextViewCountDown.setText(timeLeftFormatted);

//        if(Integer.parseInt(timeLeftFormatted) == 0)
//        {
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setMessage("No Internet Connection. Check Your Wifi Or enter code hereMobile Data.");
//            //alertDialogBuilder.setIcon(R.drawable.ic_no_internet);
//            alertDialogBuilder.setTitle("Connection Failed");
//            alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener(){
//
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Log.d("internet","Ok btn pressed");
//                    finishAffinity();
//                    System.exit(0);
//                }
//            });
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        }
    }

    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Timer Button Handlers
     */
    public void buttonSetOnClick(View view) {
        String input = mEditTextInput.getText().toString();
        if (input.length() == 0) {
            Toast.makeText(TimerActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        long millisInput = Long.parseLong(input) * 60000;
        if (millisInput == 0) {
            Toast.makeText(TimerActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
            return;
        }
        setTime(millisInput);
        mEditTextInput.setText("");
    }

    public void buttonStartPauseOnClick(View view) {
        if (mTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }

    }

    public void buttonResetOnClick(View view) {
        resetTimer();
    }


}
