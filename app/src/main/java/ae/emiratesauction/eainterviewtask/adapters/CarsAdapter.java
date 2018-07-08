package ae.emiratesauction.eainterviewtask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ae.emiratesauction.eainterviewtask.R;
import ae.emiratesauction.eainterviewtask.data.Car;

/**
 * Created by ASUS on 03/05/2016.
 */
public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.MyViewHolder> {

    private Context context;
    private List<Car> CarsList;
    private String LangCode;

    public CarsAdapter(Context con, List<Car> list, String lang) {
        context = con;
        this.CarsList = list;
        this.LangCode = lang;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Car car = CarsList.get(position);
//        holder.category_tv.setText(Day.getCategory());
        if (LangCode.equals("ar")) {
            holder.title_tv.setText(String.format("%s %s %s %d", car.getMakeAr(),
                    car.getModelAr(), car.getBodyAr(), car.getYear()));
            holder.currency_tv.setText(car.getAuctionInfo().getCurrencyAr());
        } else {
            holder.title_tv.setText(String.format("%s %s %s %d", car.getMakeEn(), car.getModelEn(),
                    car.getBodyEn(), car.getYear()));
            holder.currency_tv.setText(car.getAuctionInfo().getCurrencyEn());
        }

        holder.price_tv.setText(String.valueOf(car.getAuctionInfo().getCurrentPrice()));
        holder.bids_tv.setText(String.valueOf(car.getAuctionInfo().getBids()));
        holder.lot_tv.setText(String.valueOf(car.getAuctionInfo().getLot()));
        holder.timeLeft_tv.setText(car.getAuctionInfo().getEndDateEn());

        String url = car.getImage();
        url = url.replace("[w]", "0");
        url = url.replace("[h]", "0");
        Picasso.with(context).load(url).fit().into(holder.image);

        holder.linearLayout.setTag(position);

        final Animation animAlpha = AnimationUtils.loadAnimation(context, R.anim.anim_alpha);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);

            }
        });
    }

    @Override
    public int getItemCount() {
        return CarsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        private TextView title_tv, price_tv, lot_tv, bids_tv, timeLeft_tv, currency_tv;
        private ImageView image;

        public MyViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.linearLayoutItemCar);

            image = view.findViewById(R.id.imageViewItemCar);

            title_tv = view.findViewById(R.id.textViewItemTitle);
            price_tv = view.findViewById(R.id.textViewItemPrice);
            currency_tv = view.findViewById(R.id.textViewCurrency);
            bids_tv = view.findViewById(R.id.textViewItemBids);
            lot_tv = view.findViewById(R.id.textViewItemLot);
            timeLeft_tv = view.findViewById(R.id.textViewItemTimeLeft);

        }
    }
}
