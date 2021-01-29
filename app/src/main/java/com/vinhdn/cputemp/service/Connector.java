package com.vinhdn.cputemp.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class Connector implements ServiceConnection {
    private Class<? extends Service> serviceClass;
    private boolean bound;

    public Connector(Class<? extends Service> serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        bound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        bound = false;
    }

    protected boolean isBound() {
        return bound;
    }

    protected Intent getIntent(Context context) {
        return new Intent(context, serviceClass);
    }

    public void bind(Context context) {
        context.bindService(getIntent(context), this, Context.BIND_AUTO_CREATE);
    }

    public void unBind(Context context) {
        if (isBound()) {
            context.unbindService(this);
            bound = false;
        }
    }
}