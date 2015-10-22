/*
Class to create an external database to store the full_table. This table contains the x, y, and z accelarometer values.
 */

package mymapapplication.miguel.labrador.com.deadlifthelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeadliftHelperExternalDatabase extends SQLiteOpenHelper {


    /*****************************/
    /*****Global Variables******/
    /***************************/
    public static String full_table_name = "full_table";


    /*****************************/
    /*****Private Variables******/
    /***************************/

    //Query to create the full table.
    private String CREATE_FULL_TABLE = "CREATE TABLE " + full_table_name + "(" +
            "_id integer primary key autoincrement" +
            "username text" +
            "weight integer" +
            "real x_accel" +
            "real_y_accel" +
            "real z_accel)" +
            "IF NOT EXISTS full_table";

    /*****************************/
    /*Helper & Private Functions*/
    /***************************/

    /*****************************/
    /*****Lifecycle Functions****/
    /***************************/

    //Database is not created here. It is created in onCreate() below.
    DeadliftHelperExternalDatabase(Context context, String databaseName)
    {
        super(context, context.getExternalFilesDir(null).getAbsolutePath() + "/" + databaseName, null, 1);
    }


    //This function is called when getWritableDatabase or getReadableDatabase is called.
    //This is where the database is actually created.
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_FULL_TABLE);
    }

    //Called when the database is updated. Most likely not necessary to implement anything here.
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){}
}
