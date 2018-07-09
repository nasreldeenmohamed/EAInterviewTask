package ae.emiratesauction.eainterviewtask.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

import ae.emiratesauction.eainterviewtask.R;
import ae.emiratesauction.eainterviewtask.presenter.CarsListImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SortingDialog extends Dialog implements android.view.View.OnClickListener {
    private Context context;
    private int sortingType;
    CarsListImpl carsListPresenter;

    @BindView(R.id.radioEndDate)
    RadioButton endDate_btn;
    @BindView(R.id.radioPrice)
    RadioButton price_btn;
    @BindView(R.id.radioYear)
    RadioButton year_btn;
    @BindView(R.id.buttonDialogDone)
    Button done_btn;

    public SortingDialog(Context context, CarsListImpl presenter) {
        super(context);
        this.context = context;
        this.carsListPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getContext(), R.layout.dialog_sorting, null);
        ButterKnife.bind(this, view);
        setContentView(view);

        done_btn.setOnClickListener(this);
    }

    public int getSortingType() {
        return sortingType;
    }

    private void setSortingType() {
        if (endDate_btn.isChecked())
            sortingType = 1;
        else if (price_btn.isChecked())
            sortingType = 2;
        else if (year_btn.isChecked())
            sortingType = 3;

        carsListPresenter.sortCarsList(sortingType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDialogDone:
                setSortingType();
                this.dismiss();
                break;
        }
    }


}
