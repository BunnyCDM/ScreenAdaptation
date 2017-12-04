package com.example.mac.screenadaptation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mTextView1 = (TextView) findViewById(R.id.screen_size);
        TextView mTextView2 = (TextView) findViewById(R.id.screen_density);

        DisplayMetrics dm = new DisplayMetrics();
        Log.d("Utils", "onCreate: dm=" + dm);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.d("Utils", "onCreate: dm=" + dm);

        dm = getResources().getDisplayMetrics();

        int densityDPI = dm.densityDpi;//屏幕密度（每寸像素：120/160/240/320）
        float density = dm.density;//屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        float scaledDensity = dm.scaledDensity;//字体显示的缩放因子，和density一样
        float xdpi = dm.xdpi;//水平方向dpi
        float ydpi = dm.ydpi;//竖直方向dpi
        Log.d("Utils", "onCreate: densityDPI=" + densityDPI);
        Log.d("Utils", "onCreate: density=" + density);
        Log.d("Utils", "onCreate: scaledDensity=" + scaledDensity);
        Log.d("Utils", "onCreate: xdpi=" + xdpi);
        Log.d("Utils", "onCreate: ydpi=" + ydpi);


        int screenWidth = dm.widthPixels;//屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels;//屏幕高（像素，如800px）

        Log.d("Utils", "onCreate: " + mTextView1.getText().toString());


        //注释mTextView1、mTextView2可根据其显示，查看values使用的那个values文件
        mTextView1.setText(screenWidth + "*" + screenHeight);
        mTextView2.setText(density + "");

    }

    /**
     * android.util.TypedValue类提供了一个函数,支持把所有的单位换算到px
     *
     * @param unit
     * @param value
     * @param metrics
     * @return
     */
    public static float applyDimension(int unit, float value, DisplayMetrics metrics) {

        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX://px：pixel
                return value;

            case TypedValue.COMPLEX_UNIT_DIP://dp（dip）
                return value * metrics.density;

            case TypedValue.COMPLEX_UNIT_SP://sp
                return value * metrics.scaledDensity;

            case TypedValue.COMPLEX_UNIT_PT://pt,1/72英寸
                return value * metrics.xdpi * (1.0f / 72);

            case TypedValue.COMPLEX_UNIT_IN://in,inch 英寸
                return value * metrics.xdpi;

            case TypedValue.COMPLEX_UNIT_MM://mm ： 毫米  1英寸=25.4毫米
                return value * metrics.xdpi * (1.0f / 25.4f);

            default:
                return 0;
        }

    }


    private void test() {
        float dp1 = getResources().getDimension(R.dimen.activity_vertical_margin_dp);
        int dp2 = getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin_dp);
        int dp3 = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin_dp);


        float sp1 = getResources().getDimension(R.dimen.activity_vertical_margin_sp);
        int sp2 = getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin_sp);
        int sp3 = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin_sp);


        float px1 = getResources().getDimension(R.dimen.activity_vertical_margin_px);
        int px2 = getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin_px);
        int px3 = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin_px);


        Log.d("Utils", "onCreate:"
                + "\n" + "dp1=" + dp1 + "\tdp2=" + dp2 + "\tdp3=" + dp3
                + "\n" + "sp1=" + sp1 + "\tsp2=" + sp2 + "\tsp3=" + sp3
                + "\n" + "px1=" + px1 + "\t\t\tpx2=" + px2 + "\tpx3=" + px3);
    }

    /**
     * dp转成px
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue, DisplayMetrics metrics) {
        return (int) (dipValue * metrics.density + 0.5f);
    }

    /**
     * px转成dp
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue, DisplayMetrics metrics) {
        return (int) (pxValue / metrics.density + 0.5f);
    }


    /**
     * sp转成px
     *
     * @param spValue
     * @param type
     * @return
     */
    public static int sp2px(float spValue, int type, DisplayMetrics metrics) {

        switch (type) {
//            case CHINESE:
            case 1:
                return (int) (spValue * metrics.scaledDensity);

//            case NUMBER_OR_CHARACTER:
            case 2:
                return (int) (spValue * metrics.scaledDensity * 10.0f / 18.0f);

            default:
                return (int) (spValue * metrics.scaledDensity);
        }

    }

}
