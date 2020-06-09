package org.minesales.robotmartieninsight.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import org.minesales.robotmartieninsight.R;
import org.minesales.robotmartieninsight.model.Sol;
import org.minesales.robotmartieninsight.model.Wind;

import java.util.ArrayList;

public class SolWindView extends View {

    private Paint backgroundPaint;
    private Paint windPaint;
    private Paint blackPaint;

    private Sol sol;

    public SolWindView(Context context) {
        super(context);
        init();
    }

    public SolWindView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SolWindView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources resources = getResources();

        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(resources.getColor(R.color.colorBackground));
        this.backgroundPaint.setStyle(Paint.Style.FILL);

        this.windPaint = new Paint();
        this.windPaint.setColor(resources.getColor(R.color.colorWind));
        this.windPaint.setStyle(Paint.Style.FILL);
        //this.windPaint.setStrokeWidth(4f);

        this.blackPaint = new Paint();
        this.blackPaint.setColor(resources.getColor(R.color.black));
        this.blackPaint.setStyle(Paint.Style.STROKE);
        this.blackPaint.setStrokeWidth(4f);
    }

    public void setSol(Sol sol) {
        this.sol = sol;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();

        canvas.translate(width/2, width/2);

        // Draw background
        canvas.drawCircle(0,0,width/2, backgroundPaint);

        canvas.drawLine(0,-width/2,0,width/2,blackPaint);
        canvas.drawLine(-width/2,0,width/2,0,blackPaint);

        ArrayList<Wind> winds  = this.sol.getWinds();

        int maxWindPower = 0;
        for(int i = 0; i < winds.size(); i++){
            if(winds.get(i).getWindPower() > maxWindPower){
                maxWindPower = winds.get(i).getWindPower();
            }
        }

        for(int i = 0; i < winds.size(); i++){
            Wind wind = winds.get(i);
            float degree = (float) wind.getDegrees()- 90;
            float ratio = (float) wind.getWindPower() / (float) maxWindPower;
            float x1 = ratio * width/2;
            float x2 = ratio * width/2 - (ratio * 30);
            float y = (360/16) * (float) Math.PI *  (ratio * (width/2) )/200;
            canvas.rotate(degree,0,0);
            Path path = new Path();
            path.lineTo(x1, 0);
            path.lineTo(x2, y);
            path.lineTo(0,0);
            path.close();
            canvas.drawPath(path, windPaint);
            canvas.drawPath(path, blackPaint);
            canvas.rotate(-degree,0,0);
        }
    }
}
