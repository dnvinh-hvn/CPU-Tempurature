package com.vinhdn.cputemp.service.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CPUService {
    @FormUrlEncoded
    @POST("cpu/temp")
    Call<Object> postCpuTemp(@Field("temp") int temp);
}
