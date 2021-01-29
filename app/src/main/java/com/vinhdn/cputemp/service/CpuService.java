package com.vinhdn.cputemp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.vinhdn.cputemp.cpu.CpuTimer;

public class CpuService extends Service {
    public class ThisBinder extends Binder {
        public CpuTimer getTimer() {
            return timer;
        }
    }

    private final IBinder binder = new ThisBinder();
    private final CpuTimer timer = new CpuTimer(20 * 1000);

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer.start();
    }

    @Override
    public void onDestroy() {
        timer.stop();
        super.onDestroy();
    }
}