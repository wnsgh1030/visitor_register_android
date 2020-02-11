package com.example.Vistior_Register;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureActivity;


public class ZxingActivity extends CaptureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        /* TextVeiw를 설정하고 마지막엔 this.addContentView ! */
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(Color.parseColor("#ff55ff"));
        textView.setText("QR CODE SCAN");
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        this.addContentView(textView, params);

    }

}
