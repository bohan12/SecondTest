package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonElement;

import java.util.Map;

import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.api.ui.AIButton;

public class Main2Activity extends AppCompatActivity{

    private Button button;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AIService aiService;


        final AIConfiguration config = new AIConfiguration("8a626b2b91dc466d8544893a7b2c69da",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        AIButton aiButton = (AIButton) findViewById(R.id.micButton);

        aiButton.initialize(config);
        aiButton.setResultsListener(new AIButton.AIButtonListener() {
            @Override
            public void onResult(final AIResponse response) {
                resultTextView=findViewById(R.id.textView3);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Result result = response.getResult();

                        // Get parameters
                        String parameterString = "";
                        if (result.getParameters() != null && !result.getParameters().isEmpty()) {
                            for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                                parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
                            }
                        }

                        // Show results in TextView.
                        resultTextView.setText("Query:" + result.getResolvedQuery() +
                                "\nAction: " + result.getAction() +
                                "\nParameters: " + parameterString);
                    }
                });
            }

            @Override
            public void onError(final AIError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultTextView.setText(error.toString());
                    }
                });
            }

            @Override
            public void onCancelled() {

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "test", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }




}
