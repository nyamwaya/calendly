package com.disruptmidwest.one20.networking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.disruptmidwest.one20.model.OverviewResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aleckson on 11/12/17.
 */

@Singleton
public class OverviewRepository {
    private BooksApi booksApi;

    @Inject
    public OverviewRepository(BooksApi booksApi) {
        this.booksApi = booksApi;
    }

    public LiveData<OverviewResponse> getOverview() {
        final MutableLiveData<OverviewResponse> data = new MutableLiveData<>();

        booksApi.getOverview().enqueue(new Callback<OverviewResponse>() {
            @Override
            public void onResponse(Call<OverviewResponse> call, Response<OverviewResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OverviewResponse> call, Throwable t) {
                //TODO better error handleing
                data.setValue(null);
            }
        });
        return data;
    }


    //TODO live data for details?

}
