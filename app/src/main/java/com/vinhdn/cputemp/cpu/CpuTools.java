package com.vinhdn.cputemp.cpu;

import com.vinhdn.cputemp.tool.IOTools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CpuTools {
    private static final String CPU_TEMP = "cat sys/class/thermal/thermal_zone0/temp";

    public static int getTemp() {
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