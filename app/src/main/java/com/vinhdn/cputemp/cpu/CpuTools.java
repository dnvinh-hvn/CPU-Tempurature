package com.vinhdn.cputemp.cpu;

import android.content.Context;
import android.os.HardwarePropertiesManager;

import com.vinhdn.cputemp.App;
import com.vinhdn.cputemp.tool.IOTools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CpuTools {
    private static final String CPU_TEMP = "cat sys/class/thermal/thermal_zone0/temp";

    public static int getTemp() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Object objService = App.instance.getSystemService(Context.HARDWARE_PROPERTIES_SERVICE);
            if(objService instanceof HardwarePropertiesManager) {
                HardwarePropertiesManager hardware = (HardwarePropertiesManager) objService;
                float[] cpuTemperature;
                try {
                    cpuTemperature = hardware.getDeviceTemperatures(HardwarePropertiesManager.DEVICE_TEMPERATURE_CPU, HardwarePropertiesManager.TEMPERATURE_CURRENT);
                    float total = 0;
                    for (float v : cpuTemperature) {
                        total = total + v;
                    }
                    double percentAvail = total / cpuTemperature.length;
                    return (int) (Math.round(percentAvail * 10) / 10.0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        BufferedReader reader = null;
        try {
            Process process = Runtime.getRuntime().exec(CPU_TEMP);
            process.waitFor();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                float temp = Float.parseFloat(line);
                if (temp > 1000) {
                    return (int) temp / 1000;
                }
                return (int) temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOTools.close(reader);
        }
        return 0;
    }
}