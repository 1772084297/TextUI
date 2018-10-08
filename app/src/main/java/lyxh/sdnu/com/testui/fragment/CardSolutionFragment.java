package lyxh.sdnu.com.testui.fragment;


import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lyxh.sdnu.com.testui.Utils.NetClient;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.Utils;

public class CardSolutionFragment extends Fragment {

    private PieChart pieChart;
    private BarChart barChart;
    private Map<String, Float> pieValues;
    private List<String> barXValues;
    private List<Float> barYValues1;
    private List<Float> barYValues2;
    private List<Float> barYValues3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_solution, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        pieChart = view.findViewById(R.id.pieChart);
        barChart = view.findViewById(R.id.barChart);

    }

    public static Fragment getInstance() {
        return new CardSolutionFragment();
    }

    private void initData() {

        barXValues = new ArrayList<>();
        barYValues1 = new ArrayList<>();
        barYValues2 = new ArrayList<>();
        barYValues3 = new ArrayList<>();
        pieValues = new HashMap<>();

        startRequest();


    }


    //    https://blog.csdn.net/coderinchina/article/details/50899391
    private void startRequest() {
        NetClient.getInstance().startRequest("http://148.70.111.56:8055/api/StudentCard?stuId=201611010218&tdsourcetag=s_pctim_aiomsg", callBack);
    }

    private void parseGson(String result) {

    /*    JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("studentCard");
        Gson gson = new Gson();
        List<GsonConsume> jsonlist = new ArrayList<>();
        for (JsonElement data:jsonArray){
            GsonConsume consume = gson.fromJson(data,new TypeToken<GsonConsume>(){}.getType());
            jsonlist.add(consume);
        }
        for (int i = 0; i < jsonlist.size(); i++) {
            barXValues.add((i+1)+"天前");
            barYValues1.add(Float.valueOf(jsonlist.get(i).getBb()));
            barYValues2.add(Float.valueOf(jsonlist.get(i).getHb()));
            barYValues3.add(Float.valueOf(jsonlist.get(i).getFb()));

            if (i==0){
                pieValues.put("早餐", Float.valueOf(jsonlist.get(0).getBb()));
                pieValues.put("午餐", Float.valueOf(jsonlist.get(0).getHb()));
                pieValues.put("晚餐", Float.valueOf(jsonlist.get(0).getFb()));
            }
        }*/


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 7; i++) {
                    barXValues.add((i+1)+ "天前");
                }

                barYValues1.add(3f);barYValues1.add(3.5f);barYValues1.add(0f);barYValues1.add(4f);
                barYValues1.add(3.2f);barYValues1.add(2.8f);barYValues1.add(3f);

                barYValues2.add(7.5f);barYValues2.add(8f);barYValues2.add(5f);barYValues2.add(8f);
                barYValues2.add(7.8f);barYValues2.add(7.5f);barYValues2.add(7f);

                barYValues3.add(10f);barYValues3.add(7f);barYValues3.add(7.8f);barYValues3.add(7.2f);
                barYValues3.add(8.2f);barYValues3.add(7f);barYValues3.add(6.5f);

                setThreeBarChart(barChart, barXValues, barYValues1, barYValues2, barYValues3, "早餐", "午餐", "晚餐");

                pieValues.put("早餐", 3f);
                pieValues.put("午餐", 7.5f);
                pieValues.put("晚餐", 10f);
                setPieChart(pieChart, pieValues, "", false);

            }
        });


//        setThreeBarChart(barChart,barXValues,barYValues1,barYValues2,barYValues3,
//                "早餐","午餐","晚餐");
    }

    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {
            parseGson(result);
        }

        @Override
        public void onFailure(int code) {
            Log.e("TAGTAG",code+"");
        }
    };


    //设置饼图的各种参数及数据
    public void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, boolean showLegend) {
        //设置使用百分比
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);

        pieChart.setExtraOffsets(10, 5, 10, 5);
        //环中文字
        pieChart.setCenterText(title);
        //环中文字大小
        pieChart.setCenterTextSize(10f);
        //设置绘制环中文字
        pieChart.setDrawCenterText(true);

        //图例设置
        Legend legend = pieChart.getLegend();
        if (showLegend) {
            legend.setEnabled(true);
            //设置图例的位置
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            //设置子图例的方向
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
            legend.setDrawInside(false);
            //图例的顺序
            legend.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
        } else {
            legend.setEnabled(false);
        }

        //设置饼图数据
        setPieChartData(pieChart, pieValues);


        pieChart.animateX(2000, Easing.EasingOption.EaseInCubic);

    }

    private void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {
        List<PieEntry> entries = new ArrayList<>();

        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        //饼块间的距离
        dataSet.setSliceSpace(3f);
        //饼块选中时偏离饼图中心的距离
        dataSet.setSelectionShift(5f);
        //颜色
        dataSet.setColors(Color.RED, Color.BLUE, Color.GREEN, Color.CYAN);

        //数据连接线距图片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(90f);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        //连接线的颜色
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "元";
            }
        });


        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);

//        pieChart.setNoDataText(getResources().getString(R.string.no_data));
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.highlightValue(null);
        //刷新数据
        pieChart.invalidate();

    }



    //设置三组柱状图的各种参数及数据
    public void setThreeBarChart(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2,
                                 List<Float> yAxisValue3, String title1, String title2, String title3) {
        //设置描述
        barChart.getDescription().setEnabled(false);
        //不按比例缩放柱状图
        barChart.setPinchZoom(false);
        barChart.setExtraBottomOffset(10);
        barChart.setExtraTopOffset(30);

        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));


        //y坐标轴设置
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        barChart.getAxisRight().setEnabled(false);

        Float yMin1 = Collections.min(yAxisValue1);
        Float yMin2 = Collections.min(yAxisValue2);
        Float yMin3 = Collections.min(yAxisValue3);
        Float yMax1 = Collections.max(yAxisValue1);
        Float yMax2 = Collections.max(yAxisValue2);
        Float yMax3 = Collections.max(yAxisValue3);
        Float yMinTemp = yMin1 < yMin2 ? yMin1 : yMin2;
        Float yMin = yMinTemp < yMin3 ? yMinTemp : yMin3;
        Float yMaxTemp = yMax1 > yMax2 ? yMax1 : yMax2;
        Float yMax = yMaxTemp > yMax3 ? yMaxTemp : yMax3;
        leftAxis.setAxisMinimum(Double.valueOf(yMin * 0.9).floatValue());
        leftAxis.setAxisMaximum(Double.valueOf(yMax * 1.1).floatValue());

        //设置柱状图数据
        setThreeBarChartData(barChart, xAxisValue, yAxisValue1, yAxisValue2, yAxisValue3, title1, title2, title3);

        Matrix m = new Matrix();
        m.postScale(1.5f, 1f); //xy轴的缩放比例
        barChart.getViewPortHandler().refresh(m, barChart, false);
        barChart.animateY(3000, Easing.EasingOption.EaseInOutBack);

    }

    private void setThreeBarChartData(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2,
                                      List<Float> yAxisValue3, String title1, String title2, String title3) {
        float groupSpace = 0.04f;
        float barSpace = 0.02f;
        float barWidth = 0.3f;
        // (0.3 + 0.02) * 3 + 0.04 = 1，即一个间隔为一组，包含三个柱图

        ArrayList<BarEntry> first_entries = new ArrayList<>();
        ArrayList<BarEntry> second_entries = new ArrayList<>();
        ArrayList<BarEntry> third_entries = new ArrayList<>();

        for (int i = 0, n = xAxisValue.size(); i < n; ++i) {

            first_entries.add(new BarEntry(i, yAxisValue1.get(i)));
            second_entries.add(new BarEntry(i, yAxisValue2.get(i)));
            third_entries.add(new BarEntry(i, yAxisValue3.get(i)));
        }

        BarDataSet first_set, second_set, third_set;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            first_set = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            second_set = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            third_set = (BarDataSet) barChart.getData().getDataSetByIndex(2);
            first_set.setValues(first_entries);
            second_set.setValues(second_entries);
            third_set.setValues(third_entries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            first_set = new BarDataSet(first_entries, title1);
            second_set = new BarDataSet(second_entries, title2);
            third_set = new BarDataSet(third_entries, title3);

            first_set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.blue));
            second_set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.red));
            third_set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.yellow));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(first_set);
            dataSets.add(second_set);
            dataSets.add(third_set);

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
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size() + 0);
        barChart.groupBars(0, groupSpace, barSpace);


        //禁用拖拽放大事件
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);

        barChart.invalidate();

    }

}
