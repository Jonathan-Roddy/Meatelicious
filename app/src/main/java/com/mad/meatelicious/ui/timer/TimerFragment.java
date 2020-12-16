package com.mad.meatelicious.ui.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mad.meatelicious.R;

public class TimerFragment extends Fragment {

    private TimerViewModel timerViewModel;

//    // Timer
    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    public Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;
//    private CountDownTimer mCountDownTimer;
//    private boolean mTimerRunning;
//    private long mStartTimeInMillis;
//    private long mTimeLeftInMillis;
//    private long mEndTime;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timer, container, false);
        //final TextView textView = root.findViewById(R.id.text_timer);
//        timerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//        mEditTextInput = findViewById(R.id.edit_text_input);
//        mTextViewCountDown = findViewById(R.id.text_view_countdown);
//        mButtonSet = findViewById(R.id.button_set);
//        mButtonStartPause = findViewById(R.id.button_start_pause);
//        mButtonReset = findViewById(R.id.button_reset);

        //mTextViewCountDown.setText("Test");
        return root;
    }

    @Override
    public void onPause() {
        // save the instance variables

//        SharedPreferences.Editor editor = savedValues.edit();
//        editor.putInt("roundCount", roundCount);
//        editor.putInt("player1Points", player1Points);
//        editor.putInt("player2Points", player2Points);
//        editor.putBoolean("player1Turn", player1Turn);
//        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();
//        new MainActivity();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
//                    if(getFragmentManager().getBackStackEntryCount() > 0) {
//                        getFragmentManager().popBackStack();
//                    }
//
//                    return true;
//
//                }
//
//                return false;
//            }
//        });
    }




}