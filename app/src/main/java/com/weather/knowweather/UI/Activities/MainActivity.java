package com.weather.knowweather.UI.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.weather.knowweather.R;
import com.weather.knowweather.ServiceAPIs.WeatherDataApi;
import com.weather.knowweather.Utilities.CONSTANTS;
import com.weather.knowweather.Utilities.CommonUtilities;
import com.weather.knowweather.Utilities.KWApiClient;

import org.json.JSONObject;

import java.net.SocketTimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.txt_lbl)
    TextView txt_lbl;
    @BindView(R.id.lnr_name)
    LinearLayout lnr_name;
    @BindView(R.id.edt_id)
    EditText edt_id;
    @BindView(R.id.txt_lbl1)
    TextView txt_lbl1;
    @BindView(R.id.lnr_id)
    LinearLayout lnr_id;

    @BindView(R.id.rgrp)
    RadioGroup rgrp;
    @BindView(R.id.rbtn_name)
    RadioButton rbtn_name;
    @BindView(R.id.rbtn_id)
    RadioButton rbtn_id;


    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    String loginResponseMsg, loginResponseCod;

//    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
/*
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Fabulous_PERSONAL_USE.ttf");
        edt_name.setTypeface(typeface);
        edt_id.setTypeface(typeface);
        btn_search.setTypeface(typeface);
        txt_lbl.setTypeface(typeface);
        txt_lbl1.setTypeface(typeface);
        rbtn_id.setTypeface(typeface);
        rbtn_name.setTypeface(typeface);
*/

        prefs = getSharedPreferences(CONSTANTS.appName, Activity.MODE_PRIVATE);
        editor = prefs.edit();


        rgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.rbtn_name) {
                    lnr_name.setVisibility(View.VISIBLE);
                    lnr_id.setVisibility(View.GONE);
                    btn_search.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rbtn_id) {
                    lnr_name.setVisibility(View.GONE);
                    lnr_id.setVisibility(View.VISIBLE);
                    btn_search.setVisibility(View.VISIBLE);
                } else {
                    lnr_name.setVisibility(View.GONE);
                    lnr_id.setVisibility(View.GONE);
                    btn_search.setVisibility(View.GONE);
                }
            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString().trim();
                String id = edt_id.getText().toString().trim();
                int selectedId = rgrp.getCheckedRadioButtonId();

                if (selectedId == rbtn_name.getId()) {
                    if (!name.isEmpty()) {
                        fetchDataByName(name);
                    } else {
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.empty_name));
                    }
                } else if (selectedId == rbtn_id.getId()) {
                    if (!id.isEmpty()) {
                        fetchDataByID(id);
                    } else {
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.empty_id));
                    }
                }

            }
        });


    }

    private void fetchDataByName(String name) {
        try {
            progressDialog = ProgressDialog.show(MainActivity.this, "", getResources().getString(R.string.please_wait), true);
            WeatherDataApi apiService = KWApiClient.getClient().create(WeatherDataApi.class);
            Call<ResponseBody> apiResponse = apiService.getDataByName(name, CONSTANTS.APPID, "imperial");
            apiResponse.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String responsevalue = "";
                        if (response.body() != null)
                            responsevalue = response.body().string();
                        else
                            responsevalue = response.errorBody().string();

                        if (!responsevalue.isEmpty() && responsevalue != null) {
                            progressDialog.dismiss();

                            JSONObject jsonObject = new JSONObject(responsevalue);
                            loginResponseCod = jsonObject.getString("cod");


                            if (loginResponseCod.equals("429")) {
                                CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.update_app));
                                loginResponseMsg = jsonObject.getString("message");
                            } else if (loginResponseCod.equals("404")) {
                                loginResponseMsg = jsonObject.getString("message");
                                CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.city_not_found));
                            } else if (loginResponseCod.equals("200")) {
                                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                                intent.putExtra(CONSTANTS.data, responsevalue);
                                startActivity(intent);
                            }

                        } else {
                            CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.data_not_found));
                            Log.i(CONSTANTS.TAG, "onResponse:\n" + responsevalue);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.errortxt));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.Timeout));
                    } else if (t instanceof java.net.ConnectException) {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.networkerror));
                    } else {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.errortxt));
                    }
                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            ex.printStackTrace();
            CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.errortxt));
        }


    }

    private void fetchDataByID(String id) {
        try {
            progressDialog = ProgressDialog.show(MainActivity.this, "", getResources().getString(R.string.please_wait), true);
            WeatherDataApi apiService = KWApiClient.getClient().create(WeatherDataApi.class);
            Call<ResponseBody> apiResponse = apiService.getDataById(id, CONSTANTS.APPID, "imperial");
            apiResponse.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String responsevalue = "";
                        if (response.body() != null)
                            responsevalue = response.body().string();
                        else
                            responsevalue = response.errorBody().string();

                        if (!responsevalue.isEmpty() && responsevalue != null) {
                            progressDialog.dismiss();

                            JSONObject jsonObject = new JSONObject(responsevalue);
                            loginResponseCod = jsonObject.getString("cod");


                            if (loginResponseCod.equals("429")) {
                                CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.update_app));
                                loginResponseMsg = jsonObject.getString("message");
                            } else if (loginResponseCod.equals("404")) {
                                loginResponseMsg = jsonObject.getString("message");
                                CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.city_not_found));
                            } else if (loginResponseCod.equals("200")) {
                                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                                intent.putExtra(CONSTANTS.data, responsevalue);
                                startActivity(intent);
                            }

                        } else {
                            CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.data_not_found));
                            Log.i(CONSTANTS.TAG, "onResponse:\n" + responsevalue);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.errortxt));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.Timeout));
                    } else if (t instanceof java.net.ConnectException) {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.networkerror));
                    } else {
                        progressDialog.dismiss();
                        CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.errortxt));
                    }
                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            ex.printStackTrace();
            CommonUtilities.ShowToastMessage(MainActivity.this, getResources().getString(R.string.errortxt));
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
