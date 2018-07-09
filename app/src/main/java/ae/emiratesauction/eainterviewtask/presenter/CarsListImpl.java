package ae.emiratesauction.eainterviewtask.presenter;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import ae.emiratesauction.eainterviewtask.data.CarsListData;
import ae.emiratesauction.eainterviewtask.models.CarsListModel;
import ae.emiratesauction.eainterviewtask.utils.SharedPref;
import ae.emiratesauction.eainterviewtask.view.MainActivityView;

public class CarsListImpl implements CarsListPresenter {
    private Context context;
    private CarsListModel carsListModel;
    private MainActivityView activityView;

    private SharedPref sharedPref;
    private CountDownTimer countDownTimer;

    public CarsListImpl(Context con, MainActivityView view) {
        context = con;
        activityView = view;

        carsListModel = new CarsListModel(context, this);
        sharedPref = new SharedPref(context);

        fetchCarsList();
    }

    public void startTimer() {
            countDownTimer = new CountDownTimer(Long.valueOf(sharedPref.getString("ticks")),
                    1000 * sharedPref.getInteger("refreshInterval")) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.e("TIMER", millisUntilFinished + " to refresh");
                }

                @Override
                public void onFinish() {
                    activityView.showLoadingMode();
                    Log.e("TIMER", "Finished, Refreshing...");
                    fetchCarsList();
                }
            }.start();
    }

    public void fetchCarsList() {
        carsListModel.getCarsListData();
    }

    @Override
    public void receiveDataFromModel(CarsListData data) {
        activityView.showCarListWhenReady(data);
    }

    @Override
    public void showNoDataToView() {
        activityView.showErrorMessage();
    }


    public void destroyTimer() {
        if (!sharedPref.getString("ticks").equals("")) {
            countDownTimer.cancel();
        }
    }
}
