package com.vinhdn.cputemp.service;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;

import com.vinhdn.cputemp.cpu.CpuTimer;
import com.vinhdn.cputemp.listener.TempListener;

public class CpuConnector extends Connector {
    private CpuTimer timer;
    private TempListener listener;

    public CpuConnector(TempListener listener) {
        super(CpuService.class);
        this.listener = listener;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);

        CpuService.ThisBinder binder = (CpuService.ThisBinder) service;
        timer = binder.getTimer();
        timer.addListener(listener);
    }

    @Override
    public void unBind(Context context) {
        if (isBound()) {
            timer.excludeListener(listener);
            super.unBind(context);
        }
    }
}