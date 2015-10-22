/*
Class to create an internal database to store the  names_weight_table. This table contains a list of all username, weight combinations
that the user has used so far.
 */

package mymapapplication.miguel.labrador.com.deadlifthelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeadliftHelperInternalDatabase extends SQLiteOpenHelper {


    /*****************************/
    /*****Global Variables******/
    /***************************/
    public static String names_weight_table_name = "names_weight_table";


    /*****************************/
    /*****Private Variables******/
    /***************************/

    //Query to create the names and weight table.
    private String CREATE_NAMES_WEIGHT_TABLE = "CREATE TABLE IF NOT EXISTS " + names_weight_table_name + "(" +
            "_id integer PRIMARY KEY autoincrement, " +
            "username text, " +
            "weight integer)";

    /*****************************/
    /*Helper & Private Functions*/
    /***************************/

    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    //Database is not created here. It is created in onCreate() below.
    DeadliftHelperInternalDatabase(Context context, String databaseName)
    {
        super(context,databaseName, null, 1);
    }


    //This function is called when getWritableDatabase or getReadableDatabase is called.
    //This is where the database is actually created.
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_NAMES_WEIGHT_TABLE);
    }

    //Called when the database is updated. Most likely not necessary to implement anything here.
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){}


}
