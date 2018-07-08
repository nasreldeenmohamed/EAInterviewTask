package ae.emiratesauction.eainterviewtask.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ae.emiratesauction.eainterviewtask.R;
import ae.emiratesauction.eainterviewtask.adapters.CarsAdapter;
import ae.emiratesauction.eainterviewtask.data.CarsListData;
import ae.emiratesauction.eainterviewtask.presenter.CarsListImpl;
import ae.emiratesauction.eainterviewtask.view.MainActivityView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    @BindView(R.id.textViewLoading)
    TextView loading_tv;
    @BindView(R.id.recyclerViewCarsList)
    RecyclerView recyclerView;

    CarsListImpl carsListPresenter;
    CarsAdapter carsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        carsListPresenter = new CarsListImpl(MainActivity.this, this);
    }

    @Override
    public void showCarListWhenReady(CarsListData data) {
        if(data != null) {
            loading_tv.setVisibility(View.GONE);

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(mLayoutManager);

            carsAdapter = new CarsAdapter(MainActivity.this, data.getCars(), "en");
            recyclerView.setAdapter(carsAdapter);
        }
    }

    @Override
    public void showErrorMessage() {
        loading_tv.setText(R.string.no_data);
    }
}
