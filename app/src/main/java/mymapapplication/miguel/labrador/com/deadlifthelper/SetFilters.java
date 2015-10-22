package mymapapplication.miguel.labrador.com.deadlifthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SetFilters extends AppCompatActivity {

    /*****************************/
    /*****Private Variables******/
    /***************************/

    private String username;    //Holds username from intent from Main Activity.
    private String weight;      //Holds weight from intent from Main Activity.

    //Used to hold the characters in the spinners of the UI.
    private String[] arraySpinner = {"<", "=", ">"};


    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Default and required.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_filters);

        /***********Receive Intent***********/
        //Get the intent from Main Screen that brought us here.
        Intent intent_mainActivity = getIntent();

        //Get strings passed from intent_mainAcivity.
        //Parameters are key values associated with these strings from the MainAcivity.
        username = intent_mainActivity.getStringExtra(MainScreen.USERNAME);
        weight = intent_mainActivity.getStringExtra(MainScreen.WEIGHT);


        /***********Initialize UI Elements***********/
        //UI elements should reflect the filters which produce the base graph.

        //Base graph shows data for the given username.
        EditText editText_username = (EditText) this.findViewById(R.id.UserNameEditText);
        editText_username.setText(username);

        //Base graph does not filter based on weight.
        EditText editText_weight = (EditText) this.findViewById(R.id.WeightEditText);
        editText_weight.setText("0");

        //Date on this screen should be the earliest date available for the given username.
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //FIXME: NEED TO GET THE EARLIEST DATE ENTERED FOR GIVEN USERNAME IN EXTERNAL RECORDS.
        //FIXME: THIS WILL COME FROM AN INTENT FROM VIEW LIFTS ACTIVITY. NEED TO FIGURE OUT THAT ACTIVITY TO FINISH THIS PART.
        //EditText editText_date = (EditText) this.findViewById(R.id.DateSingleValEditText);
        //editText_date.setText(weight);

        //Initialize spinners to contain necessary elements.
        //Get weight and date spinners.
        Spinner spinner_weight = (Spinner) findViewById(R.id.WeightSpinner);
        Spinner spinner_date = (Spinner) findViewById(R.id.DateSpinner);

        //Use an adapter to add elements to the spinners.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);

        //Actually add the elements to the spinners.
        spinner_weight.setAdapter(adapter);
        spinner_date.setAdapter(adapter);

        //Finally, set the starting value for each spinner.
    }





















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_filters, menu);
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
