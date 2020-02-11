package com.example.Vistior_Register;

import android.content.Context;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.zxing.integration.android.IntentIntegrator;

public class MainFragment extends Fragment {
    MainActivity activity;
    private IntentIntegrator qrScan;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //이제 더이상 엑티비티 참조가안됨
        activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview  = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        Button qrcode  = (Button)rootview.findViewById(R.id.qrcode);
        Button pincode = (Button)rootview.findViewById(R.id.pincode);

        pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onChangeFragment(1);
            }
        });
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan = new IntentIntegrator(getActivity());
                qrScan.setCaptureActivity(ZxingActivity.class);
                qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });
        return rootview;
    }
}
