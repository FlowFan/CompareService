package com.example.compareservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CompareService extends Service {
    public CompareService() {
    }

    static class MyBinder extends Binder {
        public boolean compare(int num1, int num2) {
            return num1 > num2;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}