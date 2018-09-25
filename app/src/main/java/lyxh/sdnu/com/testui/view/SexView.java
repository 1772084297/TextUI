package lyxh.sdnu.com.testui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.angmarch.views.NiceSpinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.R;

public class SexView extends LinearLayout {
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutHide;
    private TextView womanNum;
    private TextView manNum;
    private ProgressBar progressBar;
    private NiceSpinner niceSpinner;
    private PieChartView pieChartView;

    private List<String> academies;
    private List<SliceValue> values;
    private boolean isHide=false;
    public SexView(Context context) {
        super(context);
        init();
    }

    public SexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_sex, this
                , true);
        findViews();
        initData();
        setClick();
    }

    private void setPieChartView(float man_radio) {
        if (man_radio==0f)
            man_radio=0.2740048f;
        setPieSize();
        float woman_radio= 1-man_radio;
        Log.e("woman-num",woman_radio+"");
        int manNum=(int)(man_radio*100);
        int womanNum=100-manNum;
        setSexProportion(manNum,womanNum);//todo set Sex Proportion
        SliceValue sliceValue1=new SliceValue(man_radio, ChartUtils.pickColor());
        sliceValue1.setLabel(String.format("男生:%d %%",manNum));
        values.add(sliceValue1);
        SliceValue sliceValue2=new SliceValue(woman_radio,ChartUtils.pickColor());
        sliceValue2.setLabel(String.format("女生:%d %%",womanNum));
        values.add(sliceValue2);
        PieChartData mPieChartData = new PieChartData(values);
        mPieChartData.setHasLabels(true);
        mPieChartData.setHasLabelsOnlyForSelected(false);
        mPieChartData.setHasCenterCircle(true);
        mPieChartData.setHasLabelsOutside(false);
        mPieChartData.setCenterText1("山东师范大学");
        mPieChartData.setCenterText1FontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,4,MainActivity.instance.getResources().getDisplayMetrics()));
        mPieChartData.setCenterText1Color(R.color.colorAccent);
        mPieChartData.setCenterText2("男女比例分析");
        mPieChartData.setCenterText2FontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,3, MainActivity.instance.getResources().getDisplayMetrics()));
        mPieChartData.setCenterText2Color(R.color.blue);
        mPieChartData.setHasLabelsOutside(false);
        mPieChartData.setSlicesSpacing(10);

        pieChartView.setPieChartData(mPieChartData);
        pieChartView.startDataAnimation();
    }
    private void setPieSize(){
        int[] loc = new int[2];
        pieChartView.getLocationOnScreen(loc);
//        pieChartView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 920));
    }
    private class ValueTouchListener implements PieChartOnValueSelectListener {
        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
        }

        @Override
        public void onValueDeselected() {
        }
    }

    private void initData() {
        academies= new LinkedList<>( Arrays.asList(getResources().getStringArray(R.array.academy)));

        niceSpinner.attachDataSource(academies);
        values=new ArrayList<>();
    }

    private void setClick() {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo to detailed sex view
                values.clear();
                if (isHide){
                    linearLayoutHide.setVisibility(GONE);
                    isHide=false;
                }else {
                    linearLayoutHide.setVisibility(VISIBLE);
                    setPieChartView(0.2740048f);
                    isHide=true;
                }
            }
        });
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //todo 获取url
                if (getCollegeId(niceSpinner.getSelectedIndex()).equals("00")){
                    values.clear();
                    setPieChartView(0.2740048f);
                }else {
                    final String url = "http://148.70.111.56:8055/api/sex?college=" + getCollegeId(niceSpinner.getSelectedIndex());
                    Log.e("return_data_url",url);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            values.clear();
                            getWebInfo(url);
                        }
                    }).start();
                }

            }
        });
    }
    private String getCollegeId(int selectedIndex) {
        if (selectedIndex<10)
            return "0"+selectedIndex;
        else if(selectedIndex>15){
            int newIndex=selectedIndex+6;
            if (newIndex==23)
                return 15+"";
            return newIndex+"";
        }
        else
            return selectedIndex+"";
    }

    private void requestData(final String data) {
        Log.e("return_data",data);
        float newData=Float.parseFloat(data.substring(1,data.length()-1));
        setPieChartView(newData);
    }
    private void getWebInfo(String urlRoot) {
        try {
            URL url=new URL(urlRoot);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setReadTimeout(8000);
            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String temp=null;
            while ((temp=bufferedReader.readLine())!=null){
                stringBuffer.append(temp);
            }
            showResponse(stringBuffer.toString());
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showResponse(final String s) {
        MainActivity.instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                requestData(s);
            }
        });
    }

    private void findViews() {
        CardView outerCard = findViewById(R.id.view_sex_outer_cardView);
        CardView innerCard = findViewById(R.id.view_sex_inner_cardView);
        linearLayout=findViewById(R.id.view_sex_linearLayout);
        linearLayoutHide=findViewById(R.id.view_sex_linearLayout_hide);
        womanNum=findViewById(R.id.view_sex_woman_num);
        manNum=findViewById(R.id.view_sex_man_num);
        progressBar=findViewById(R.id.view_sex_progressbar);
        niceSpinner=findViewById(R.id.view_sex__niceSpinner);
        pieChartView=findViewById(R.id.view_sex_piaCharView);

        outerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));
        innerCard.setCardBackgroundColor(getResources().getColor(R.color.bac));

        setSexProportion(27,73);//todo set Sex Proportion
        pieChartView.setOnValueTouchListener(new ValueTouchListener());
    }

    @SuppressLint("SetTextI18n")
    private void setSexProportion(int man,int woman) {
        womanNum.setText(man+"%");
        manNum.setText(woman+"%");
        progressBar.setProgress(woman);
    }
}
