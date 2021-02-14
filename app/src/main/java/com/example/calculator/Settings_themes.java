package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Settings_themes extends AppCompatActivity implements View.OnClickListener  {

    RadioButton radioButtonLightTheme;
    RadioButton radioButtonNightTheme;
    Button buttonApply;
    MainActivity mActivity= new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_themes);
        initButtons();
        buttonApply.setOnClickListener(this);
    }

    public void initButtons(){
        radioButtonLightTheme = findViewById(R.id.radioButton_light_settings);
        radioButtonNightTheme = findViewById(R.id.radioButton_night_settings);
        buttonApply = findViewById(R.id.button_apply);

    }


    @Override
    public void onClick(View v) {
        if(radioButtonNightTheme.isChecked()){
            Intent intent = new Intent();
            intent.putExtra("Theme", "Night");
            setResult(1, intent);
            finish();

        } else if (radioButtonLightTheme.isChecked()){
            Intent intent = new Intent();
            intent.putExtra("Theme", "Light");
            setResult(1, intent);
            finish();

        }
    }
}