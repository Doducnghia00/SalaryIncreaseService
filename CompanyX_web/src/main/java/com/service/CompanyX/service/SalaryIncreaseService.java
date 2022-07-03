package com.service.CompanyX.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.service.CompanyX.model.ResponseTask;
import com.service.CompanyX.model.SalaryLevel;
import org.springframework.web.bind.annotation.PathVariable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SalaryIncreaseService {
    //link http://localhost:9090/task/

    Gson gson = new GsonBuilder()
            .setDateFormat("yyy-MM-dd HH:,,:ss")
            .create();

    SalaryIncreaseService service = new Retrofit.Builder()
            .baseUrl("http://localhost:9090/task/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SalaryIncreaseService.class);


    @GET("check/{id}")
    Call<ResponseTask> checkSalaryIncrease(@Path("id") Long id);


    @GET("salary/{id}")
    Call<SalaryLevel> getSalaryLevel(@Path("id") Long id);
}
