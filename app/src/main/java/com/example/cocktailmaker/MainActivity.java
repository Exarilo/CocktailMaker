package com.example.cocktailmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText tvSearch;
    private ImageView btSearchValidate;
    private TextView tvCocktailName;
    private WebView webView;
    private ScrollView ScrollView;
    private TextView tvIngredients;
    static int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        tvSearch= findViewById(R.id.tvSearch);
        btSearchValidate= findViewById(R.id.btSearchValidate);
        tvCocktailName= findViewById(R.id.tvCocktailName);
        webView= findViewById(R.id.webView);
        ScrollView= findViewById(R.id.ScrollView);
        tvIngredients= findViewById(R.id.tvIngredients);
        setVisibility(false);

        btSearchValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cocktail cocktail=new Cocktail(tvSearch.getText().toString());
                tvCocktailName.setText(cocktail.listNames.get(index));
                webView.loadUrl(cocktail.listImgs.get(index));
                String ingredients="";
                //List<String> listIngredients=cocktail.dicIngredients.get(0);
                for (int i=0;i<cocktail.dicIngredients.size();i++){

                    ingredients+=cocktail.dicIngredients.get(i);
                }
                tvIngredients.setText(ingredients);
                setVisibility(true);
            }
        });


    }
    public void setVisibility(boolean visible){
        if(visible) {
            tvCocktailName.setVisibility(View.VISIBLE);
            webView.setVisibility(View.VISIBLE);
            ScrollView.setVisibility(View.VISIBLE);
        }
        else{
            tvCocktailName.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.INVISIBLE);
            ScrollView.setVisibility(View.INVISIBLE);
        }
    }
}