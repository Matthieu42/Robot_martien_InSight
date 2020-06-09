package org.minesales.robotmartieninsight.service;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.minesales.robotmartieninsight.model.Sol;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class NasaAPIService {

    private String API_KEY = "ub7EVINrgOA0sHp7IHchHARJmM2FhIYomBfPdUnm";

    private RequestQueue queue;

    private static NasaAPIService instance;

    private NasaAPIService(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static synchronized NasaAPIService getInstance(Context context) {
        if (instance == null) {
            instance = new NasaAPIService(context);
        }
        return instance;
    }

    public Promise<List<Sol>> getSolsPromise() {

        final Promise<List<Sol>> promise = new Promise<>();

        String url = String.format("https://api.nasa.gov/insight_weather/?api_key=%s&feedtype=json&ver=1.0", API_KEY);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Sol> sols = new ArrayList<>();
                        try {
                            JSONArray solKeys = response.getJSONArray("sol_keys");
                            for (int i=0; i < solKeys.length(); i++) {
                                JSONObject solObject = response.getJSONObject(solKeys.getString(i));
                                sols.add(new Sol(solKeys.getString(i),solObject));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (promise.promiseThen == null) return;
                        promise.promiseThen.execute(sols);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MY_ASTEROID_APP", error.getMessage());
                        if (promise.promiseCatch == null) return;
                        promise.promiseCatch.execute(error.getMessage());
                    }
                });

        queue.add(jsonObjectRequest);
        return promise;
    }

}



