package org.minesales.robotmartieninsight.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sol implements Serializable {
    private String id;

    private double avgTemp;
    private double minTemp;
    private double maxTemp;

    private double avgPressure;
    private double minPressure;
    private double maxPressure;

    private ArrayList<Wind> winds;


    public Sol(String id, JSONObject jsonObject) throws JSONException {
        this.id = id;
        this.fromJSON(jsonObject);
    }

    private void fromJSON(JSONObject jsonObject) throws JSONException {
        DecimalFormat df = new DecimalFormat("#.##");

        JSONObject at = jsonObject.getJSONObject("AT");
        this.avgTemp = Double.parseDouble(df.format(at.getDouble("av")));
        this.minTemp = Double.parseDouble(df.format(at.getDouble("mn")));
        this.maxTemp = Double.parseDouble(df.format(at.getDouble("mx")));

        JSONObject PRE = jsonObject.getJSONObject("PRE");
        this.avgPressure = Double.parseDouble(df.format(at.getDouble("av")));
        this.minPressure = Double.parseDouble(df.format(at.getDouble("mn")));
        this.maxPressure = Double.parseDouble(df.format(at.getDouble("mx")));

        this.winds = new ArrayList<>();
        JSONObject WD = jsonObject.getJSONObject("WD");
        for(int i =0 ; i<=15; i++){
            if(WD.has(Integer.toString(i))){
                Double degrees = WD.getJSONObject(Integer.toString(i)).getDouble("compass_degrees");
                int value = WD.getJSONObject(Integer.toString(i)).getInt("ct");
                this.winds.add(new Wind(degrees,value));
            }
        }

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getAvgPressure() {
        return avgPressure;
    }

    public void setAvgPressure(double avgPressure) {
        this.avgPressure = avgPressure;
    }

    public double getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(double minPressure) {
        this.minPressure = minPressure;
    }

    public double getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(double maxPressure) {
        this.maxPressure = maxPressure;
    }

    public ArrayList<Wind> getWinds() {
        return winds;
    }

    public void setWinds(ArrayList<Wind> winds) {
        this.winds = winds;
    }
}
