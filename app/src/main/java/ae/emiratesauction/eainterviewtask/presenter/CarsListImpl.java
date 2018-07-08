package ae.emiratesauction.eainterviewtask.presenter;

import android.content.Context;

import ae.emiratesauction.eainterviewtask.activities.MainActivity;
import ae.emiratesauction.eainterviewtask.data.CarsListData;
import ae.emiratesauction.eainterviewtask.models.CarsListModel;
import ae.emiratesauction.eainterviewtask.view.MainActivityView;

public class CarsListImpl implements CarsListPresenter{
    Context context;
    CarsListData data;
    CarsListModel carsListModel;
    MainActivityView activityView;

    public CarsListImpl(Context con, MainActivityView view) {
        context = con;
        activityView = view;

        fetchCarsList();
    }

    private void fetchCarsList(){
        carsListModel = new CarsListModel(context, this);
    }

    @Override
    public void receiveDataFromModel(CarsListData data) {
        activityView.showCarListWhenReady(data);
    }

    @Override
    public void showNoDataToView() {
        activityView.showErrorMessage();
    }
}
