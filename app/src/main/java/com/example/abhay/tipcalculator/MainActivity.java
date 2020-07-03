package com.example.abhay.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    float tipPercent = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float DEFAULT_TIP_PERCENTAGE = 15;
    float REGULAR_TIP_PERCENTAGE = 10;
    float EXCELLENT_TIP_PERCENTAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValue();
    }

    private void setTipValue() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent, tipPercent));
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodServive, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                tipPercent = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodServive:
                tipPercent = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                tipPercent = EXCELLENT_TIP_PERCENTAGE;
                break;

        }

        calculateBillAmount();
        setTipValue();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateBillAmount();
        setTipValue();
    }

    private void calculateBillAmount() {
        if(tipPercent == 0)
            tipPercent = DEFAULT_TIP_PERCENTAGE;
        if(!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount*tipPercent)/100;
        finalBillAmount = totalBillAmount + tipTotal;
    }
}
