package com.vinhdn.cputemp.service.server;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CPUService {

    String serverUrl = "https://api.humaxdigital.com/";
    String apiPostTemp = "cpu/temp";

    @POST(CPUService.apiPostTemp)
    Call<Object> postCpuTemp(@Body JSONObject data);
}
