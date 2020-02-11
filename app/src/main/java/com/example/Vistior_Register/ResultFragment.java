package com.example.Vistior_Register;

import android.content.Context;
import android.graphics.Color;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;



public class ResultFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    com.example.Vistior_Register.MainActivity activity;
    private String mParam1;

    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (com.example.Vistior_Register.MainActivity) getActivity();
    }

    public static ResultFragment newInstance(String param1) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootview  = (ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);
        ImageButton result_button  = (ImageButton) rootview.findViewById(R.id.result_button);
        TextView result_text = (TextView) rootview.findViewById(R.id.result_text);
        result_text.setText(mParam1);

        if(mParam1.compareTo("WRONG") == 0){
            result_text.setTextColor(Color.RED);
            result_button.setImageResource(R.drawable.ic_wrong);
        }
        else{
            result_button.setImageResource(R.drawable.ic_correct);
        }

        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mParam1.compareTo("WRONG") == 0){
                    activity.onChangeFragment(1);
                }
                else{
                    activity.onChangeFragment(0);
                }
            }
        });
        return rootview;
    }

}
