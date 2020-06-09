package org.minesales.robotmartieninsight.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Wind implements Serializable {
    private double degrees;
    private int windPower;

    public Wind(Double degrees, int windPower) throws JSONException {
        this.degrees = degrees;
        this.windPower = windPower;
    }

    private void fromJSON(JSONObject jsonObject) throws JSONException {
        this.degrees =jsonObject.getDouble("compass_degrees");
        this.windPower = jsonObject.getInt("ct");
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public int getWindPower() {
        return windPower;
    }

    public void setWindPower(int windPower) {
        this.windPower = windPower;
    }
}
