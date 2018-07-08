package ae.emiratesauction.eainterviewtask.models;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import ae.emiratesauction.eainterviewtask.api.GetCarsListAPI;
import ae.emiratesauction.eainterviewtask.data.CarsListData;
import ae.emiratesauction.eainterviewtask.presenter.CarsListPresenter;
import ae.emiratesauction.eainterviewtask.utils.ConnectionDetector;
import ae.emiratesauction.eainterviewtask.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsListModel {
    private CarsListData carsListData;
    private SharedPref sharedPref;
    private Gson gson;

    private CarsListPresenter presenter;

    public CarsListModel(Context context, CarsListPresenter presenter1) {
        presenter = presenter1;
        sharedPref = new SharedPref(context);
        gson = new Gson();

        if (ConnectionDetector.isConnectingToInternet(context)) {
            fetchDataFromServer();
        } else {
            fetchDataFromLocalDB();
        }
    }

    private void fetchDataFromServer() {
        GetCarsListAPI getCarsListAPI = GetCarsListAPI.retrofit.create(GetCarsListAPI.class);
        Call<CarsListData> call = getCarsListAPI.getCarsList();
        call.enqueue(new Callback<CarsListData>() {
            @Override
            public void onResponse(Call<CarsListData> call, Response<CarsListData> response) {
                if (response.isSuccessful()) {
                    carsListData = response.body();

                    sharedPref.setString("carsList", gson.toJson(carsListData));

                    presenter.receiveDataFromModel(carsListData);
                } else {
                    try {
                        Log.e("responseError", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    fetchDataFromLocalDB();
                }
            }

            @Override
            public void onFailure(Call<CarsListData> call, Throwable t) {
                Log.e("serverError", t.getLocalizedMessage());
            }
        });
    }

    private void fetchDataFromLocalDB() {
        Type type = new TypeToken<CarsListData>() {
        }.getType();
        carsListData = gson.fromJson(sharedPref.getString("carsList"), type);

        if (carsListData != null)
            presenter.receiveDataFromModel(carsListData);
        else
            presenter.showNoDataToView();
    }

    public void setCarsListData(CarsListData carsListData) {
        this.carsListData = carsListData;
    }

    public CarsListData getCarsListData() {
        return carsListData;
    }
}
