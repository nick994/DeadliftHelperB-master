package mymapapplication.miguel.labrador.com.deadlifthelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class MainScreen extends AppCompatActivity {

    /*****************************/
    /*****Global Variables******/
    /***************************/

    //Used as a key for extras in intents to identify username.
    public final static String USERNAME = "DEADLIFT_HELPER_USERNAME";

    //Used as a key for extras in intents to identify weight.
    public final static String WEIGHT = "DEADLIFT_HELPER_WEIGHT";

    //used as a key for extras in intents to identify timer
    public final static String TIMER = "DEADLIFT_HELPER_TIMER";

    //Used to identify the names and weight database.
    public final static String internalDB_name = "names_and_weight_database";

    //Used to identify the database with name, weight, and x y z accelerometer values.
    public final static String externalDB_name = "full_database";

    //DatabaseManager objects to utilize our databases.
    //These will be initializes here, and used throughout the application.
    public DatabaseManager full_table_database;
    public DatabaseManager names_weight_database;


    /*****************************/
    /*****Private Variables******/
    /***************************/

    /***** UI Elements***/
    private ListView myListView;            //Represents the listViewObject that we have on the MainScreen.
    private EditText username_EditText;
    private EditText weight_EditText;
    private EditText timer_EditText;
    private Button recordDeadliftButton;
    private Button viewLiftsButton;
    private Button timerButton;


    private ArrayAdapter<String> listAdaptor;   //Contains the elements of the above ListView.
    public SQLiteDatabase db;


    /*****************************/
    /*Helper & Private Functions*/
    /***************************/

    //Helper function to start the Record Deadlift activity.
    //This activity will require the username and weight that were entered here.
    //Build an intent with this data, and start next activity.
    private void navigateTo_recordDeadlift(View view)
    {
        //Intent used to navigate to Record Deadlift activity.
        Intent intent_recordDeadlift = new Intent(this, RecordDeadlift.class);

        //Get actual text value from each EditText.
        String username_text = username_EditText.getText().toString();
        String weight_text = weight_EditText.getText().toString();

        //Give the intent the necessary data.
        //Public strings USERNAME and WEIGHT are used as keys for
        //username_text and weight_text respectively.
        intent_recordDeadlift.putExtra(USERNAME, username_text);
        intent_recordDeadlift.putExtra(WEIGHT, weight_text);

        //Finally, start the Record Deadlift activity.
        startActivity(intent_recordDeadlift);
    }

    //Helper function to start the View Lifts activity.
    //This activity will require the username and weight that were entered here.
    //Build an intent with this data, and start next activity.
    private void navigateTo_viewLifts(View view)
    {
        //Intent used to navigate to View Lifts activity.
        Intent intent_viewLifts = new Intent(this, ViewLifts.class);

        //Get actual text value from each EditText.
        String username_text = username_EditText.getText().toString();
        String weight_text = weight_EditText.getText().toString();

        //Give the intent the necessary data.
        //Public strings USERNAME and WEIGHT are used as keys for
        //username_text and weight_text respectively.
        intent_viewLifts.putExtra(USERNAME, username_text);
        intent_viewLifts.putExtra(WEIGHT, weight_text);

        //Finally, start the View Lifts activity.
        startActivity(intent_viewLifts);
    }

    //Helper function to start the Timer activity.
    //This activity will require the time that was entered here
    //Build an intent with this data, and start next activity.
    private void navigateTo_TimerCount(View view)
    {
        //Intent used to navigate to timer activity.
        Intent intent_timerCount = new Intent(this, TimerCount.class);

        //Get actual text value from each EditText.
        String timerVal_text = timer_EditText.getText().toString();

        //Give the intent the necessary data.
        //Public string TIMER used as key for
        //timerVal_text.
        intent_timerCount.putExtra(TIMER, timerVal_text);

        //Finally, start the timer activity.
        startActivity(intent_timerCount);
    }


    //Function to add items to the ExistingUsernameListView
    //Items come from the internal database which stores this data.
    private void initializeExistingUsernameListView()
    {
        //Step 1: Add items to ArrayList
        //Get all names from internal database.
        String[] names_string = names_weight_database.getAllNames();

        //ArrayList to use for adaptor.
        ArrayList<String> usernameList = new ArrayList<String>();

        //Add items from names_string to usernameList.
        usernameList.addAll(Arrays.asList(names_string));

        //Step 2: Populate our listAdaptor. This allows us to add elements to the ListView.
        listAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usernameList);

        //Step 3: Set the adapator.
        myListView.setAdapter(listAdaptor);
        listAdaptor.notifyDataSetChanged();
    }


    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Default and required.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        /***********Initialize UI Elements***********/
        myListView = (ListView) findViewById(R.id.ExistingUsernameExpandableListView);
        username_EditText = (EditText) this.findViewById(R.id.UsernameEditText);
        weight_EditText = (EditText) this.findViewById(R.id.EnterWeightEditText);
        timer_EditText = (EditText) this.findViewById(R.id.TimerEditText);
        recordDeadliftButton = (Button) this.findViewById(R.id.RecordDeadliftButton);
        viewLiftsButton = (Button) this.findViewById(R.id.ViewPreviousLiftsButton);
        timerButton = (Button) this.findViewById(R.id.StartTimerButton);


        /***********Add Navigation Buttons***********/
        //Button to navigate to Record Deadlift screen.
        recordDeadliftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo_recordDeadlift(view);
            }
        });

        //Button to navigate to View Lifts screen.
        viewLiftsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo_viewLifts(view);
            }
        });

        //Button to navigate to Timer screen.
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                navigateTo_TimerCount(view);
            }
        });

        /***********Enable Interactivity with ListView***********/
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                String clicked_text = parent.getItemAtPosition(position).toString();
                username_EditText.setText(clicked_text);
                        }
             });


        /***********Initialize Database Components***********/
        names_weight_database = new DatabaseManager(this, internalDB_name, true);
        full_table_database = new DatabaseManager(this, externalDB_name, true);
    }


    //Define actions that must occur when the activity is resumed.
    //Populate the ListView.
    //Ensure that no text is left in textViews.
    @Override
    public void onResume()
    {
        super.onResume();       //Always call super method.

        initializeExistingUsernameListView();   //Initialize the ListView.

        //Clear text from EditTexts.
        username_EditText.setText("");
        weight_EditText.setText("");
        timer_EditText.setText("");
    }

    //Companion for onResume.
    @Override
    public void onPause()
    {
        super.onPause();
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
