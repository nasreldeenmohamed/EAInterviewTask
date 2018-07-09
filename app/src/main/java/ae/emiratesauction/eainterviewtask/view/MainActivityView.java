package ae.emiratesauction.eainterviewtask.view;

import ae.emiratesauction.eainterviewtask.data.CarsListData;

public interface MainActivityView {
    void showCarListWhenReady(CarsListData data);

    void showErrorMessage();

    void showLoadingMode();
}
