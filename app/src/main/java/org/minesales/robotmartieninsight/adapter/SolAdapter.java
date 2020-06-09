package org.minesales.robotmartieninsight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.minesales.robotmartieninsight.R;
import org.minesales.robotmartieninsight.model.Sol;

import java.util.List;

public class SolAdapter extends ArrayAdapter<Sol> {

    private List<Sol> asteroids;
    private Context context;
    private LayoutInflater inflater;


    public SolAdapter(Context context, List<Sol> asteroids) {
        super(context, -1, asteroids);
        this.inflater = LayoutInflater.from(context);
        this.asteroids = asteroids;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.sol_line, parent, false);

            TextView tvId = convertView.findViewById(R.id.tvId);
            TextView tvTemp = convertView.findViewById(R.id.tvTemp);
            TextView tvPressure = convertView.findViewById(R.id.tvPressure);

            MyHolder holder = new MyHolder();
            holder.tvId = tvId;
            holder.tvTemp = tvTemp;
            holder.tvPressure = tvPressure;

            convertView.setTag(holder);
        }

        MyHolder holder = (MyHolder) convertView.getTag();

        Sol sol = this.asteroids.get(position);
        holder.tvId.setText( context.getResources().getString(R.string.sol_nb)  + sol.getId());
        String temp = context.getResources().getString(R.string.tempTV) + sol.getAvgTemp();
        holder.tvTemp.setText(temp);
        String pressure = context.getResources().getString(R.string.pressionTV) + sol.getAvgPressure();
        holder.tvPressure.setText(pressure);
        return convertView;

    }


    private class MyHolder {
        TextView tvId;
        TextView tvTemp;
        TextView tvPressure;
    }
}
