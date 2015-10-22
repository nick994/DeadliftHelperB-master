package mymapapplication.miguel.labrador.com.deadlifthelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

import java.util.Locale;

public class ViewLifts extends AppCompatActivity {

    /*****************************/
    /*****Private Variables******/
    /***************************/

    private String username;    //Holds username from intent from Main Activity.
    private String weight;  //Holds weight from intent from Main Activity.

    public SQLiteDatabase db;


    /*****************************/
    /*Helper & Private Functions*/
    /***************************/

    //Helper function to start the Set Filters activity.
    //This activity will require the username and weight that were entered here.
    //Build an intent with this data, and start next activity.
    private void navigateTo_setFilters(View view)
    {
        //Intent used to navigate to Set Filters activity.
        Intent intent_setFilters = new Intent(this, SetFilters.class);

        //Give the intent the necessary data.
        //Public strings USERNAME and WEIGHT are used as keys for
        //username_text and weight_text respectively.
        intent_setFilters.putExtra(MainScreen.USERNAME, username);
        intent_setFilters.putExtra(MainScreen.WEIGHT, weight);

        //Finally, start the Set Filters activity.
        startActivity(intent_setFilters);
    }


    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lifts);

        /***********Receive Intent***********/
        //Get the intent from Main Screen that brought us here.
        Intent intent_mainActivity = getIntent();

        //Get strings passed from intent_mainAcivity.
        //Parameters are key values associated with these strings from the MainAcivity.
        username = intent_mainActivity.getStringExtra(MainScreen.USERNAME);
        weight = intent_mainActivity.getStringExtra(MainScreen.WEIGHT);


        /***********Add Navigation Buttons***********/
        //Button to navigate to Record Deadlift screen.
        Button setFilters = (Button) this.findViewById(R.id.SetFiltersButton);

        setFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo_setFilters(view);
            }
        });

        //displayPreviousLifts();

    }



    public void displayPreviousLifts() {

        String name1, weight1;

        Cursor c=db.rawQuery("SELECT * FROM deadlift", null);
        if(c.getCount()==0)
        {
            //print error message
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("name: "+c.getString(0)+"\n");
            buffer.append("weight: "+c.getString(1)+"\n");

        }
        showMessage("Student Details", buffer.toString());

    }



    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }




















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_lifts, menu);
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
