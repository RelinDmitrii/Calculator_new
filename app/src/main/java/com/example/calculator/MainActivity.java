package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NameSharedPreference = "LOGIN";
    private static final String IsDarkTheme = "IS_DARK_THEME";

    TextView ScreenWithSigns;
    TextView ScreenWithNumbers;
    TextView title;


    Button buttonOne;
    Button buttonOTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;
    Button buttonZero;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonSplit;
    Button buttonMultiply;
    Button buttonPlusMinus;
    Button buttonC;
    Button buttonResetOne;
    Button buttonEqually;

    Calculator calculator;

    private ConstraintLayout constraintLayout;
    String url = "https://cdn.pixabay.com/photo/2020/04/16/10/16/mountain-5050026_960_720.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!isDarkTheme()) {
            setTheme(R.style.Theme_Calculator_Light_My_Option);
        } else {
            setTheme(R.style.Theme_Calculator_Night_My_Option);
        }

        setContentView(R.layout.activity_main);



        initView();
        calculator = new Calculator();
        refreshScreens();
        initButtons();
        initLayout();
        buttonsSetOnClickListener();
        loadImageToBackGround();



    }


    public void refreshScreens() {
        ScreenWithSigns.setText(calculator.inputValue.get(Calculator.KEY_ScreenWithSigns));
        ScreenWithNumbers.setText(calculator.inputValue.get(Calculator.KEY_ScreenWithNumbers));
        title.setText(calculator.inputValue.get(Calculator.KEY_title));
    }

    public void initView() {
        ScreenWithSigns = findViewById(R.id.screenWithSigns);
        ScreenWithNumbers = findViewById(R.id.screenWithNumbers);
        title = findViewById(R.id.titleTextView);
    }

    public void initButtons() {
        buttonOne = findViewById(R.id.buttonOne);
        buttonOTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonZero = findViewById(R.id.buttonZero);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonSplit = findViewById(R.id.buttonSplit);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonPlusMinus = findViewById(R.id.buttonPlusMinus);
        buttonC = findViewById(R.id.buttonC);
        buttonResetOne = findViewById(R.id.buttonResetOne);
        buttonEqually = findViewById(R.id.buttonEqually);
    }


    public void initLayout() {
        constraintLayout = findViewById(R.id.constraintLayout);
    }

    public void buttonsSetOnClickListener() {
        buttonOne.setOnClickListener(this);
        buttonOTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonSplit.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonPlusMinus.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonResetOne.setOnClickListener(this);
        buttonEqually.setOnClickListener(this);
    }


    public void loadImageToBackGround() {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.background)
                .error(R.drawable.background)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        constraintLayout.setBackground(new BitmapDrawable(bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        calculator.input(((Button) v).getText().toString());
        refreshScreens();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString("screenSigns", calculator.inputValue.get(Calculator.KEY_ScreenWithSigns));
        instanceState.putString("screenNumbers", calculator.inputValue.get(Calculator.KEY_ScreenWithNumbers));
        instanceState.putString("title", calculator.inputValue.get(Calculator.KEY_title));
        instanceState.putBoolean("firstFlag", calculator.startOperation);
        instanceState.putBoolean("secondFlag", calculator.startOperationSin);
    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calculator.inputValue.put(Calculator.KEY_ScreenWithSigns, instanceState.getString("screenSigns"));
        calculator.inputValue.put(Calculator.KEY_ScreenWithNumbers,instanceState.getString("screenNumbers"));
        calculator.inputValue.put(Calculator.KEY_title,instanceState.getString("title"));
        calculator.startOperation = instanceState.getBoolean("firstFlag");
        calculator.startOperationSin = instanceState.getBoolean("secondFlag");
        refreshScreens();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Settings")) {
            startActivityForResult(new Intent(this, Settings_themes.class), 1);
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (data.getStringExtra("Theme").equals("Light")) {
                setDarkTheme(false);
                recreate();

            } else {
                setDarkTheme(true);
                recreate();
            }
            recreate();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    protected boolean isDarkTheme() {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getBoolean(IsDarkTheme, true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }


}