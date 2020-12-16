package com.mad.meatelicious.ui.timer;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class TimerViewModel extends ViewModel{

    private MutableLiveData<String> mText;
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
    private long mEndTime;

    public TimerViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is a Timer TEST");
        //prepareTimer();
    }
//
//
//    public LiveData<String> getText() {
//        return mText;
//    }
//    /**
//     * Timer functions
//     */
//    private void prepareTimer() {
//        setContentView( R.layout.fragment_timer);
//        mEditTextInput = findViewById(R.id.edit_text_input);
//        mTextViewCountDown = findViewById(R.id.text_view_countdown);
//        mButtonSet = findViewById(R.id.button_set);
//        mButtonStartPause = findViewById(R.id.button_start_pause);
//        mButtonReset = findViewById(R.id.button_reset);
//
//        mTextViewCountDown.setText("Test");
//    }
//
//    private void setTime(long milliseconds) {
//        mStartTimeInMillis = milliseconds;
//        resetTimer();
//        closeKeyboard();
//    }
//    private void startTimer() {
//        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
//                updateWatchInterface();
//            }
//        }.start();
//        mTimerRunning = true;
//        updateWatchInterface();
//    }
//    private void pauseTimer() {
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
//        updateWatchInterface();
//    }
//    private void resetTimer() {
//        mTimeLeftInMillis = mStartTimeInMillis;
//        updateCountDownText();
//        updateWatchInterface();
//    }
//    private void updateCountDownText() {
//        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
//        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//        String timeLeftFormatted;
//        if (hours > 0) {
//            timeLeftFormatted = String.format(Locale.getDefault(),
//                    "%d:%02d:%02d", hours, minutes, seconds);
//        } else {
//            timeLeftFormatted = String.format(Locale.getDefault(),
//                    "%02d:%02d", minutes, seconds);
//        }
//        mTextViewCountDown.setText(timeLeftFormatted);
//    }
//    private void updateWatchInterface() {
//        if (mTimerRunning) {
//            mEditTextInput.setVisibility(View.INVISIBLE);
//            mButtonSet.setVisibility(View.INVISIBLE);
//            mButtonReset.setVisibility(View.INVISIBLE);
//            mButtonStartPause.setText("Pause");
//        } else {
//            mEditTextInput.setVisibility(View.VISIBLE);
//            mButtonSet.setVisibility(View.VISIBLE);
//            mButtonStartPause.setText("Start");
//            if (mTimeLeftInMillis < 1000) {
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//            } else {
//                mButtonStartPause.setVisibility(View.VISIBLE);
//            }
//            if (mTimeLeftInMillis < mStartTimeInMillis) {
//                mButtonReset.setVisibility(View.VISIBLE);
//            } else {
//                mButtonReset.setVisibility(View.INVISIBLE);
//            }
//        }
//    }
//    private void closeKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
//
//    /**
//     * Timer Button Handlers
//     */
//    public void buttonSetOnClick(View view) {
//        String input = mEditTextInput.getText().toString();
//        if (input.length() == 0) {
//            Toast.makeText(TimerViewModel.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        long millisInput = Long.parseLong(input) * 60000;
//        if (millisInput == 0) {
//            Toast.makeText(TimerViewModel.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        setTime(millisInput);
//        mEditTextInput.setText("");
//    }
//    public void buttonStartPauseOnClick(View view) {
//        if (mTimerRunning) {
//            pauseTimer();
//        } else {
//            startTimer();
//        }
//
//    }
//    public void buttonResetOnClick(View view) {
//        resetTimer();
//    }


}