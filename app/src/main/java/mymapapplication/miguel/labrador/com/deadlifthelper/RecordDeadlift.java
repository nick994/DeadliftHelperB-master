package mymapapplication.miguel.labrador.com.deadlifthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RecordDeadlift extends AppCompatActivity {

    /*****************************/
    /*****Private Variables******/
    /***************************/

    private String username;    //Holds username from intent from Main Activity.
    private String weight;      //Holds weight from intent from Main Activity.


    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Required default stuff.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_deadlift);


        /***********Receive Intent***********/
        //Get the intent from Main Screen that brought us here.
        Intent intent_mainActivity = getIntent();

        //Get strings passed from intent_mainAcivity.
        //Parameters are key values associated with these strings from the MainAcivity.
        username = intent_mainActivity.getStringExtra(MainScreen.USERNAME);
        weight = intent_mainActivity.getStringExtra(MainScreen.WEIGHT);
    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record_deadlift, menu);
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
