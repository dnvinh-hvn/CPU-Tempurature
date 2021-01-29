package com.vinhdn.cputemp.tool;

import java.io.Closeable;

public class IOTools {
    public static void close(Closeable o) {
        if (o == null) {
            return;
        }
        try {
            o.close();
        } catch (Exception e) {
        }
    }
}