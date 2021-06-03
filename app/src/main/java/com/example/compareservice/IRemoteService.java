package com.example.compareservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class IRemoteService extends Service {
    public IRemoteService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IImoocAidl.Stub() {
        @Override
        public boolean add(int num1, int num2) throws RemoteException {
            Log.d("TAG", "add: laile" + num1 + num2);
            return num1 > num2;
        }
    };
}