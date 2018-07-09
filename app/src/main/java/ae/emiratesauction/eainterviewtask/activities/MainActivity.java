package ae.emiratesauction.eainterviewtask.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import ae.emiratesauction.eainterviewtask.R;
import ae.emiratesauction.eainterviewtask.adapters.CarsAdapter;
import ae.emiratesauction.eainterviewtask.data.CarsListData;
import ae.emiratesauction.eainterviewtask.presenter.CarsListImpl;
import ae.emiratesauction.eainterviewtask.view.MainActivityView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private static final String TAG = "MainActivity";
    boolean isRefreshed = false;

    @BindView(R.id.textViewLoading)
    TextView loading_tv;
    @BindView(R.id.recyclerViewCarsList)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout refreshLayout;

    CarsListImpl carsListPresenter;
    CarsAdapter carsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.e("lang", Locale.getDefault().getDisplayLanguage());

        carsListPresenter = new CarsListImpl(MainActivity.this, this);

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                        isRefreshed = true;
                        showLoadingMode();

                        carsListPresenter.fetchCarsList();
                    }
                }
        );

    }

    @Override
    public void showCarListWhenReady(CarsListData data) {
        if (data != null) {
            loading_tv.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            if (isRefreshed)
                refreshLayout.setRefreshing(false);

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(mLayoutManager);

            carsAdapter = new CarsAdapter(MainActivity.this, data.getCars());
            recyclerView.setAdapter(carsAdapter);

            carsListPresenter.startTimer();
        } else {
            loading_tv.setText(R.string.no_data);
        }
    }

    @Override
    public void showErrorMessage() {
        loading_tv.setText(R.string.no_data);
    }

    @Override
    public void showLoadingMode() {
        loading_tv.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        carsListPresenter.destroyTimer();
        super.onDestroy();
    }
}
