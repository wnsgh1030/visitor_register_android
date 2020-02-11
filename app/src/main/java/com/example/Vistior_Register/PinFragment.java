package com.example.Vistior_Register;

import android.content.Context;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PinFragment extends Fragment {
    com.example.Vistior_Register.MainActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (com.example.Vistior_Register.MainActivity) getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMM", Locale.getDefault());
        String first = format.format(currentTime);
        ViewGroup rootview  = (ViewGroup) inflater.inflate(R.layout.fragment_pin, container, false);
        Button pin_button = (Button)rootview.findViewById(R.id.pin_button);
        Button back_button = (Button)rootview.findViewById(R.id.back_button);
        final EditText pin_text1 = (EditText)rootview.findViewById(R.id.pin_text1);
        final EditText pin_text2 = (EditText)rootview.findViewById(R.id.pin_text2);
        pin_text1.setText(first);

        pin_button.setOnClickListener(new OnSingleClickListener(){

            @Override
            public void onSingleClick(View v) {
                String pin = pin_text1.getText().toString();
                pin = pin + "-";
                pin = pin + pin_text2.getText().toString();
                try {
                    JSONObject json = new JSONObject();
                    json.put("pin",pin);
                    activity.searchPIN(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activity.onChangeFragment(0);
            }
        });
        return rootview;
    }
    public abstract static class OnSingleClickListener implements View.OnClickListener{

        //중복 클릭 방지 시간 설정 ( 해당 시간 이후에 다시 클릭 가능 )
        private static final long MIN_CLICK_INTERVAL = 600;
        private long mLastClickTime = 0;

        public abstract void onSingleClick(View v);

        @Override
        public final void onClick(View v) {
            long currentClickTime = SystemClock.uptimeMillis();
            long elapsedTime = currentClickTime - mLastClickTime;
            mLastClickTime = currentClickTime;

            // 중복클릭 아닌 경우
            if (elapsedTime > MIN_CLICK_INTERVAL) {
                onSingleClick(v);
            }
        }
    }
}
