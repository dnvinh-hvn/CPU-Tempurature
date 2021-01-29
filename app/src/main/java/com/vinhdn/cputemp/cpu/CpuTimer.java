package com.vinhdn.cputemp.cpu;

import android.os.Handler;

import com.vinhdn.cputemp.listener.TempListener;

import java.util.ArrayList;
import java.util.List;

public class CpuTimer extends Handler {
    private final int interval;
    private int value;
    private final List<TempListener> listeners = new ArrayList<>();

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                postDelayed(this, interval);
                setTemp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void setTemp() {
            if (listeners.isEmpty()) {
                return;
            }
            int cur = CpuTools.getTemp();
            if (cur == value) {
                return;
            }
            value = cur;
            onChange(value);
        }
    };

    public CpuTimer(int interval) {
        this.interval = interval;
    }

    public synchronized void addListener(TempListener listener) {
        excludeListener(listener);
        listener.onTempChange(value);
        listeners.add(listener);
    }

    public synchronized void excludeListener(TempListener listener) {
        listeners.remove(listener);
    }

    private void onChange(int value) {
        for (TempListener listener : listeners) {
            listener.onTempChange(value);
        }
    }

    public void start() {
        stop();
        postDelayed(runnable, 100);
    }

    public void stop() {
        removeCallbacks(runnable);
    }
}