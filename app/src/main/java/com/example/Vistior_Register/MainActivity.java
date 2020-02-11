package com.example.Vistior_Register;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Vistior_Register.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,new com.example.Vistior_Register.MainFragment()).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
//                Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(MainActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    searchPIN(obj);

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void searchPIN(JSONObject obj) throws JSONException {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("pin", obj.getString("pin"))
                .build();

        Request request = new Request.Builder()

                .url("https://visitor-register.herokuapp.com/and/decode")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(searchPINCallback);

    }

    private Callback searchPINCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("TEST", "ERROR Message : " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String responseData = response.body().string();

            try {
                JSONObject obj = new JSONObject(responseData);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new com.example.Vistior_Register.ResultFragment().newInstance( obj.getString("message"))).commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    };

    public void onChangeFragment(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new com.example.Vistior_Register.MainFragment()).commit();
        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, new com.example.Vistior_Register.PinFragment()).commit();
        }
    }
}










