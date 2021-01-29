package com.vinhdn.cputemp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vinhdn.cputemp.R;
import com.vinhdn.cputemp.listener.TempListener;
import com.vinhdn.cputemp.service.CpuConnector;
import com.vinhdn.cputemp.service.server.CPUService;
import com.vinhdn.cputemp.service.server.CPUServiceImpl;

public class MainActivity extends AppCompatActivity implements TempListener {

    private CpuConnector cpuConnector;
    private CPUService cpuService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpuConnector = new CpuConnector(this);
        cpuService = new CPUServiceImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cpuConnector.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cpuConnector.unBind(this);
    }

    @Override
    public void onTempChange(int value) {
        cpuService.postCpuTemp(value);
        runOnUiThread(() -> {
            TextView tvValue = findViewById(R.id.value);
            if(tvValue != null) {
                tvValue.setText("CPU Temperature : " + value + "°C");
            }
        });
    }
}
