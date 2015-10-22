/**
Class to work with the DeadliftHelperInternalDatabase and DeadliftHelperExternalDatabase
 classes to run queries on them.
 */

package mymapapplication.miguel.labrador.com.deadlifthelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    /*****************************/
    /*****Global Variables******/
    /***************************/

    /*****************************/
    /*****Private Variables******/
    /***************************/

    private DeadliftHelperInternalDatabase internalDB;  //Stores internalDB.
    private DeadliftHelperExternalDatabase externalDB;  //Stores externalDB.

    //Our actual database object.
    //This is used to perform queries.
    private SQLiteDatabase myDatabase;

    private String tableName;   //Name changes based on internal or external DB.


    /*****************************/
    /*Helper & Private Functions*/
    /***************************/

    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    //Constructor to create an instance of either DeadliftHelperInternalDatabase
    //or DeadliftHelperExternalDatabase. Which is chosen depends on value of internal_flag.
    DatabaseManager(Context context, String databaseName, boolean internal_flag)
    {
        if(internal_flag) {
            internalDB = new DeadliftHelperInternalDatabase(context, databaseName);
            myDatabase = internalDB.getWritableDatabase();
            tableName = DeadliftHelperInternalDatabase.names_weight_table_name;
        }
        else {
            externalDB = new DeadliftHelperExternalDatabase(context, databaseName);
            myDatabase = externalDB.getWritableDatabase();
            tableName = DeadliftHelperExternalDatabase.full_table_name;
        }
    }


    /*****************************/
    /******Public Functions******/
    /***************************/

    //List is used to becasue dynamic allocation is necessary.
    public String[] getAllNames()
    {
        //Query the database for all names contained therein.
        String[] columnNames = {"username"};
        Cursor myCursor = myDatabase.query(tableName, columnNames, null, null, null, null, null);

        //Create an array with as many elements as necessary to hold all names
        //contained in our queried database.
        String[] names = new String[myCursor.getCount()];

         myCursor.moveToFirst(); //Ensure that the cursor is at the first row.

        //Iterate through each row, and add the item therein to our names.
        for(int c = 0; c < myCursor.getCount(); c++)
        {
            //Add the item to array.
            names[c] = myCursor.getString(0);


            //Move to next item.
            myCursor.moveToNext();
        }
        return names;
    }


    //Function to add a name, weight pair into the internal database.
    //If internal database not created in this instance, then print an error.
    //Name cannot be null.
    public void addEntry_NameAndWeight(String name, int weight)
    {
        //Ensure that we are using the correct table.
        if(!tableName.equals(DeadliftHelperInternalDatabase.names_weight_table_name)){
            System.out.println("Attempted to add a name, weight pair to table other than names_weight_table.");
            return;
        }

        //Name cannot be null.
        if(name == null || name.isEmpty()) {
            System.out.println("Attempted to add an empty name to  names_weight_table - error.");
            return;
        }

        //insert requires that we add items to a ContentValues object.
        ContentValues myVals = new ContentValues(2);

        //can be thought of as coluimn name, data.
        myVals.put("username", name);
        myVals.put("weight", weight);

        //Insert the data into the table.
        myDatabase.insert(tableName, null, myVals);


    }

}
