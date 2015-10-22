package mymapapplication.miguel.labrador.com.deadlifthelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;


public class TimerCount extends AppCompatActivity {

    private String timer;
    private TextView mTextField = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_count);

        //get the intent from the parent intent
        Intent intent_mainActivity = getIntent();

        timer = intent_mainActivity.getStringExtra(MainScreen.TIMER);
        int amount = 30;
        //startTimer(amount);

    }


    //method that starts a timer
    //needs to add a parameter that sets the time
    public void startTimer(int i) {



        int amount = i * 1000; //multiply by 1000 to convert milliseconds to seconds

        new CountDownTimer(amount, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();
    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_count, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
