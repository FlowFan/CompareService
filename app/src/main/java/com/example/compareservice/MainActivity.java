package com.example.compareservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private myConn conn;
    private EditText etnum1, etnum2;
    private TextView etres;
    CompareService.MyBinder binder = new CompareService.MyBinder();
    IImoocAidl iImoocAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnum1 = findViewById(R.id.editTextNumber);
        etnum2 = findViewById(R.id.editTextNumber2);
        etres = findViewById(R.id.textView);
        findViewById(R.id.btn_comp).setOnClickListener(this);
        findViewById(R.id.btn1_comp).setOnClickListener(this);
        findViewById(R.id.btn2_comp).setOnClickListener(this);
        conn = new myConn();
        bindService1();
    }

    private class myConn implements ServiceConnection {  //连接服务
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (CompareService.MyBinder) service;
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    }

    public void onClick(View view) {
        int num1 = Integer.parseInt(etnum1.getText().toString());
        int num2 = Integer.parseInt(etnum2.getText().toString());
        switch (view.getId()) {
            case R.id.btn1_comp:
                try {
                    boolean res1 = iImoocAidl.add(num1, num2);
                    if (res1) {
                        etres.setText("较大的数：" + num1);
                    } else {
                        etres.setText("较大的数：" + num2);
                    }
                } catch (RemoteException e) {
                    etres.setText("有错误");
                }
            case R.id.btn_comp:
            case R.id.btn2_comp:
                boolean res2 = binder.compare(num1, num2);
                if (res2) {
                    etres.setText("较大的数：" + num1);
                } else {
                    etres.setText("较大的数：" + num2);
                }
        }
    }

    private void bindService1() {
        Intent intent = new Intent(this, CompareService.class);
//        intent.setComponent(new ComponentName("com.example.compareservice", "com.example.compareservice.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}