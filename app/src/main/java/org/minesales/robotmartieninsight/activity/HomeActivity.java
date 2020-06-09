package org.minesales.robotmartieninsight.activity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.minesales.robotmartieninsight.R;
import org.minesales.robotmartieninsight.adapter.SolAdapter;
import org.minesales.robotmartieninsight.model.Sol;
import org.minesales.robotmartieninsight.service.NasaAPIService;
import org.minesales.robotmartieninsight.service.OnError;
import org.minesales.robotmartieninsight.service.OnResponse;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private NasaAPIService nasaAPIService;

    private ListView lvSols;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.lvSols = findViewById(R.id.lvSols);
        nasaAPIService = NasaAPIService.getInstance(getApplicationContext());
        nasaAPIService.getSolsPromise()
                .then(new OnResponse<List<Sol>>() {
                    @Override
                    public void execute(final List<Sol> sols) {
                        Toast.makeText(getApplicationContext(),"NB_SOLS : " + sols.size(), Toast.LENGTH_SHORT).show();
                        ArrayAdapter<Sol> adapter = new SolAdapter(
                                getApplicationContext(),
                                sols
                        );

                        lvSols.setAdapter(adapter);

                        lvSols.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                intent.putExtra("sol", sols.get(position));
                                startActivity(intent);
                            }
                        });

                    }
                }).catchError(new OnError() {
            @Override
            public void execute(String errorMessage) {
                Toast.makeText(getApplicationContext(), "ERROR: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
