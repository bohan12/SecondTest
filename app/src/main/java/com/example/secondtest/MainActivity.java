package com.example.secondtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView textView;
    private int count=0;

    TextView textView1;
    private int count1=0;

    TextView textView2;
    private  int count2=0;

    private AIService aiService;
    TextView Robot;

    private Button button;

    private Switch switch1;
    private Switch switch2;
    private Switch switch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        textView = (TextView)findViewById(R.id.textView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText(String.valueOf(count));
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1++;
                textView1.setText(String.valueOf(count1));
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        textView2 = (TextView)findViewById(R.id.textView2);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2++;
                textView2.setText(String.valueOf(count2));
            }
        });

        switch1=findViewById(R.id.switch1);
        final View switchV1 = findViewById(R.id.button1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                switchV1.setVisibility(View.INVISIBLE);
            }else{
                switchV1.setVisibility(View.VISIBLE);
            }

            }
        }); // Changed


        switch2=findViewById(R.id.switch2);
        final View switchV2 = findViewById(R.id.button2);
        //switch2.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchV2.setVisibility(View.INVISIBLE);
                }else{
                    switchV2.setVisibility(View.VISIBLE);
                }

            }
        }); // Changed

        switch3=findViewById(R.id.switch3);
        final View switchV3 = findViewById(R.id.button3);
        //switch3.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchV3.setVisibility(View.INVISIBLE);
                }else{
                    switchV3.setVisibility(View.VISIBLE);
                }

            }
        }); // Changed


        //next page
        button=(Button)findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        //connect dialogflow
        final AIConfiguration config = new AIConfiguration("436e53fa91214d66a36512e2c1fc0018",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);
        aiService.setListener(this);
        Robot=(TextView)findViewById(R.id.Robot);

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "Permission to record denied");
            makeRequest();
        }

//        Button button4= (Button) findViewById(R.id.button6);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                aiService.startListening();
//            }
//        });

        button=(Button)findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        Button uplaod=findViewById(R.id.submitbutton);

        uplaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View switchB1 = findViewById(R.id.switch1);
                View switchB2 = findViewById(R.id.switch2);
                View switchB3 = findViewById(R.id.switch3);

                switchB1.setVisibility(View.INVISIBLE);
                switchB2.setVisibility(View.INVISIBLE);
                switchB3.setVisibility(View.INVISIBLE);


                //count smallest
                if(count < count1 && count< count2){
                    switchB1.setVisibility(View.VISIBLE);
                }

                //count1 smallest
                else if(count1 < count && count1 < count2){
                    switchB2.setVisibility(View.VISIBLE);
                }

                //count2 smallest
                else{
                    switchB3.setVisibility(View.VISIBLE);
                }
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "test", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    public void listenButtonOnClick(final View view) {
        aiService.startListening();
    }



    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user");
                } else {
                    Log.i(TAG, "Permission has been granted by user");
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.FirstPage) {
            return true;
        }
        if(id== R.id.SecondPage){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Clear(View view){
        View switchB1 = findViewById(R.id.switch1);
        View switchB2 = findViewById(R.id.switch2);
        View switchB3 = findViewById(R.id.switch3);

        switchB1.setVisibility(View.INVISIBLE);
        switchB2.setVisibility(View.INVISIBLE);
        switchB3.setVisibility(View.INVISIBLE);
        this.count=0;
        this.count1=0;
        this.count2=0;
        textView.setText(String.valueOf(count));
        textView1.setText(String.valueOf(count1));
        textView2.setText(String.valueOf(count2));
    }
    public void doThis(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Main4Activity.class);
        startActivity(intent);
    }
    public void second(MenuItem item){
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        startActivity(intent);
    }
    @Override
    public void onResult(AIResponse response) {

        Result result1=response.getResult();
        Log.d(TAG, "onResult: " + result1.getResolvedQuery());
//        Robot.setText(result1.getResolvedQuery());
        Robot.setText("test");

    }

    @Override
    public void onError(AIError error) {
        Robot.setText(error.toString());
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    @Override
    public void onClick(View v) {

    }
}
