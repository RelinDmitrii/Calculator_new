package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ScreenWithSigns;
    TextView ScreenWithNumbers;
    TextView title;

    boolean startOperation = false;
    boolean startOperationSin = false;

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

        calculator = new Calculator();
        initView();
        initButtons();
        initLayout();
        ButtonsSetOnClickListener();
        loadImageToBackGround();


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

    public void ButtonsSetOnClickListener() {
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
        switch (v.getId()) {
            case (R.id.buttonPlus): {
                calculateAndSet(ScreenWithSigns, ScreenWithNumbers, title, "+");
                break;
            }
            case (R.id.buttonMinus): {
                calculateAndSet(ScreenWithSigns, ScreenWithNumbers, title, "-");
                break;
            }
            case (R.id.buttonSplit): {
                calculateAndSet(ScreenWithSigns, ScreenWithNumbers, title, "/");
                break;
            }
            case (R.id.buttonMultiply): {
                calculateAndSet(ScreenWithSigns, ScreenWithNumbers, title, "*");
                break;
            }

            case (R.id.buttonPlusMinus): {
                if (title.getText().charAt(0) != '-') {
                    title.setText("-" + title.getText());
                } else {
                    title.setText(title.getText().toString().replace("-", ""));
                }
                break;

            }
            case (R.id.buttonResetOne): {
                if (!title.getText().equals("0")) {
                    if (title.getText().length() == 1) {
                        title.setText("0");
                    } else {
                        title.setText(title.getText().toString().substring(0, title.getText().length() - 1));
                    }
                }
                break;
            }
            case (R.id.buttonC): {
                ScreenWithSigns.setText("");
                ScreenWithNumbers.setText("");
                title.setText("0");
                break;
            }
            case (R.id.buttonEqually): {
                ButtonEqually(ScreenWithSigns, ScreenWithNumbers, title);
                break;
            }
            default: {
                String clickNumber = ((Button) v).getText().toString();
                if (title.getText().equals("0")) {
                    title.setText(clickNumber);
                } else {
                    if (startOperation) {
                        title.setText("");
                        startOperation = false;
                    }
                    startOperationSin = true;
                    title.setText(title.getText() + clickNumber);
                }
            }
        }
    }

    public void calculateAndSet(TextView screenSigns, TextView screenNumbers, TextView screenTitle, String operator) {

        screenSigns.setText(operator);
        startOperation = true;
        if (screenNumbers.getText().length() > 0 && startOperationSin) {
            String result = String.valueOf(calculator.Calculate(screenNumbers.getText().toString(), screenTitle.getText().toString(), screenSigns.getText().toString()));
            screenNumbers.setText(result);
            screenTitle.setText(result);
        }
        startOperationSin = false;
        screenNumbers.setText(screenTitle.getText());
    }

    public void ButtonEqually(TextView screenSigns, TextView screenNumbers, TextView screenTitle) {
        String result = String.valueOf(calculator.Calculate(screenNumbers.getText().toString(), screenTitle.getText().toString(), screenSigns.getText().toString()));
        screenNumbers.setText(result);
        screenTitle.setText(result);
        startOperationSin = false;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString("screenSigns", ScreenWithSigns.getText().toString());
        instanceState.putString("screenNumbers", ScreenWithNumbers.getText().toString());
        instanceState.putString("title", title.getText().toString());
    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        ScreenWithSigns.setText(instanceState.getString("screenSigns"));
        ScreenWithNumbers.setText(instanceState.getString("screenNumbers"));
        title.setText(instanceState.getString("title"));
    }


}