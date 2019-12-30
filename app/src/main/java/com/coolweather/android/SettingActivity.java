package com.coolweather.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.coolweather.android.service.AutoUpdateService;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RelativeLayout cityManage = findViewById(R.id.city_manage);    

        TextView settingCityName = findViewById(R.id.cityName);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String cityName = prefs.getString("cityName", "");
        settingCityName.setText(cityName);


        final Switch autoUpdate = findViewById(R.id.auto_update);
        SharedPreferences pres = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isCheck = pres.getBoolean("isCheck", false);
        autoUpdate.setChecked(isCheck);
        if (isCheck){
            autoUpdate.setSwitchTextAppearance(SettingActivity.this,R.style.Switch_true);

        }else{
            autoUpdate.setSwitchTextAppearance(SettingActivity.this,R.style.Switch_false);

        }
        autoUpdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                editor.putBoolean("isCheck",isChecked);
                editor.apply();

                if (isChecked){
                    Intent intent = new Intent(SettingActivity.this, AutoUpdateService.class);
                    startService(intent);

                    autoUpdate.setSwitchTextAppearance(SettingActivity.this,R.style.Switch_true);


                }else{
                    Intent intent = new Intent(SettingActivity.this, AutoUpdateService.class);
                    stopService(intent);

                    autoUpdate.setSwitchTextAppearance(SettingActivity.this,R.style.Switch_false);



                }
            }
        });


    }
}
