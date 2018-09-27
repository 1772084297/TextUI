package lyxh.sdnu.com.testui.fragment;


import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lyxh.sdnu.com.testui.Data.GsonStuNum;
import lyxh.sdnu.com.testui.Utils.NetClient;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.Utils;

public class NumStuFragment extends Fragment {

    private BarChart barChart;
    private List<String> xAxisValue;
    private List<Float> yAxisValue;
    private List<Float> yAxisValue1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_stu, container, false);
        barChart = view.findViewById(R.id.hriBarChart);
        initData();
        return view;
    }

    public void initData() {
        xAxisValue = new ArrayList<>();
        yAxisValue = new ArrayList<>();
        yAxisValue1 = new ArrayList<>();

        NetClient.getInstance().startRequest("http://148.70.111.56:8055/api/classnum", callBack);

    }

    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {
            parseData(result);
        }

        @Override
        public void onFailure(int code) {
            Toast.makeText(getContext(), "网络连接异常", Toast.LENGTH_SHORT).show();
        }
    };

    private void parseData(String result) {

        result = "{\"classnum\":"+"[{\"ClassName\":\"计算机1601\",\"Mannum\":30,\"Womannum\":22},{\"ClassName\":\"计算机1602\",\"Mannum\":31,\"Womannum\":21},{\"ClassName\":\"计算机1603\",\"Mannum\":31,\"Womannum\":21},{\"ClassName\":\"计算机1604\",\"Mannum\":31,\"Womannum\":22},{\"ClassName\":\"计算机1605\",\"Mannum\":31,\"Womannum\":22},{\"ClassName\":\"通信1601\",\"Mannum\":20,\"Womannum\":28},{\"ClassName\":\"通信1602\",\"Mannum\":20,\"Womannum\":29},{\"ClassName\":\"通信1603\",\"Mannum\":20,\"Womannum\":29},{\"ClassName\":\"物联网1601\",\"Mannum\":13,\"Womannum\":37},{\"ClassName\":\"计师本1601\",\"Mannum\":17,\"Womannum\":33}]"+"}";

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("classnum");
        Gson gson = new Gson();
        List<GsonStuNum> jsonlist = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            GsonStuNum data = gson.fromJson(element, new TypeToken<GsonStuNum>() {
            }.getType());
            jsonlist.add(data);
        }
        for (int i = 0; i < jsonlist.size(); i++) {
            xAxisValue.add(jsonlist.get(i).getClassName());
            yAxisValue.add(Float.valueOf(jsonlist.get(i).getMannum()));
            yAxisValue1.add(Float.valueOf(jsonlist.get(i).getWomannum()));
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setTwoBarChart(barChart, xAxisValue, yAxisValue, yAxisValue1, "男生", "女生");
            }
        });
    }


    public static void setTwoBarChart(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2, String bartilte1, String bartitle2) {
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.setExtraBottomOffset(10);
        barChart.setExtraTopOffset(30);
        barChart.setScaleYEnabled(false);
        barChart.setPinchZoom(false);
        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));


        Matrix m = new Matrix();
        m.postScale(3f, 1f); //xy轴的缩放比例
        barChart.getViewPortHandler().refresh(m, barChart, false);
        barChart.animateY(3000, Easing.EasingOption.EaseInOutBack);
        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        //设置坐标轴最大最小值
        Float yMin1 = Collections.min(yAxisValue1);
        Float yMin2 = Collections.min(yAxisValue2);
        Float yMax1 = Collections.max(yAxisValue1);
        Float yMax2 = Collections.max(yAxisValue2);
        Float yMin = Double.valueOf((yMin1 < yMin2 ? yMin1 : yMin2) * 0.1).floatValue();
        Float yMax = Double.valueOf((yMax1 > yMax2 ? yMax1 : yMax2) * 1.1).floatValue();
        leftAxis.setAxisMaximum(yMax);
        leftAxis.setAxisMinimum(yMin);

        barChart.getAxisRight().setEnabled(false);


        //图例设置
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);

        //设置柱状图数据
        setTwoBarChartData(barChart, xAxisValue, yAxisValue1, yAxisValue2, bartilte1, bartitle2);

        barChart.animateX(1500);//数据显示动画，从左往右依次显示
        barChart.invalidate();
    }

    /**
     * 设置柱状图数据源
     */
    private static void setTwoBarChartData(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2, String bartilte1, String bartitle2) {
        float groupSpace = 0.04f;
        float barSpace = 0.03f;
        float barWidth = 0.45f;
        // (0.45 + 0.03) * 2 + 0.04 = 1，即一个间隔为一组，包含两个柱图 -> interval per "group"

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0, n = yAxisValue1.size(); i < n; ++i) {
            entries1.add(new BarEntry(i, yAxisValue1.get(i)));
            entries2.add(new BarEntry(i, yAxisValue2.get(i)));
        }

        BarDataSet dataset1, dataset2;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            dataset1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            dataset2 = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            dataset1.setValues(entries1);
            dataset2.setValues(entries2);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            dataset1 = new BarDataSet(entries1, bartilte1);
            dataset2 = new BarDataSet(entries2, bartitle2);

            dataset1.setColor(Color.rgb(129, 216, 200));
            dataset2.setColor(Color.rgb(181, 194, 202));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataset1);
            dataSets.add(dataset2);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    return Utils.double2String(value, 2);
                }
            });

            barChart.setData(data);
        }

        barChart.getBarData().setBarWidth(barWidth);
        barChart.getXAxis().setAxisMinimum(0);
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size() + 0);
        barChart.groupBars(0, groupSpace, barSpace);
    }


}
