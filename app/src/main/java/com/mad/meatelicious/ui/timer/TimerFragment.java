package com.mad.meatelicious.ui.timer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mad.meatelicious.R;
import com.mad.meatelicious.TimerActivity;

public class TimerFragment extends Fragment {

    private TimerViewModel timerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timer, container, false);


        // It is  called but replaces MainActivity,
        Intent myIntent = new Intent(getActivity(), TimerActivity.class);
        getActivity().startActivity(myIntent);

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