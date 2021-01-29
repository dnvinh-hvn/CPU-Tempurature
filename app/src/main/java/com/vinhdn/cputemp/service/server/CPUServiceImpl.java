package com.vinhdn.cputemp.service.server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CPUServiceImpl implements CPUService {

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.humaxdigital.com/")
            .build();

    CPUService service = retrofit
            .create(CPUService.class);

    @Override
    public Call<Object> postCpuTemp(int temp) {
        return service.postCpuTemp(temp);
    }
}
