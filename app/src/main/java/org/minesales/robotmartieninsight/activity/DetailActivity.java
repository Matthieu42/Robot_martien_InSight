package org.minesales.robotmartieninsight.activity;

import android.content.Context;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.minesales.robotmartieninsight.R;
import org.minesales.robotmartieninsight.model.Sol;
import org.minesales.robotmartieninsight.view.SolWindView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvIdDetail;
    private TextView tvTempDetail;
    private TextView tvPressureDetail;

    private SolWindView solWindView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.tvIdDetail = findViewById(R.id.tvIdDetail);
        this.tvTempDetail = findViewById(R.id.tvTempDetail);
        this.tvPressureDetail = findViewById(R.id.tvPressureDetail);
        this.solWindView = findViewById(R.id.solWindView);

        final Sol sol = (Sol) getIntent().getSerializableExtra("sol");
        solWindView.setSol(sol);
        Context context = getApplicationContext();
        this.tvIdDetail.setText(context.getResources().getString(R.string.sol_nb) + sol.getId());
        this.tvTempDetail.setText(context.getResources().getString(R.string.tempTV) +getString(R.string.avgLabel) + sol.getAvgTemp() + getString(R.string.minLabel) + sol.getMinTemp()
                + " max:" + sol.getMaxTemp());
        this.tvPressureDetail.setText(context.getResources().getString(R.string.pressionTV) + getString(R.string.avgLabel) + sol.getAvgPressure() + getString(R.string.minLabel) + sol.getMinPressure()
                + " max:" + sol.getMaxPressure());
    }
}
