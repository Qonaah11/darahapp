package com.qonaah.donor.app.service;

import com.qonaah.donor.app.model.Donor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by sukenda on 4/17/17.
 */

public class BaseService {

    private static Retrofit retrofit;
    private static BaseService singleton = new BaseService();
    private Donor result;
    private List<Donor> results;

    private BaseService() {
        String url = "http://maps.googleapis.com/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static BaseService getInstance() {
        if (singleton == null) {
            singleton = new BaseService();
        }

        return singleton;
    }

    public Donor find() {
        RequestInterface service = retrofit.create(RequestInterface.class);
        service.findBy().enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        result = response.body();
                    }
                }
            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {
                result = null;
            }
        });

        return result;
    }

    public List<Donor> findList() {
        RequestInterface service = retrofit.create(RequestInterface.class);
        service.find().enqueue(new Callback<List<Donor>>() {
            @Override
            public void onResponse(Call<List<Donor>> call, Response<List<Donor>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        results = response.body();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Donor>> call, Throwable t) {
                results = null;
            }
        });

        return results;
    }
}
