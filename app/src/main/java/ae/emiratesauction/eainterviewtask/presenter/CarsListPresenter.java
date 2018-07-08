package ae.emiratesauction.eainterviewtask.presenter;

import ae.emiratesauction.eainterviewtask.data.CarsListData;

public interface CarsListPresenter {
    void receiveDataFromModel(CarsListData data);

    void showNoDataToView();
}
