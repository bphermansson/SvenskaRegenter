package nu.pahecu.SvenskaRegenter

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor.*
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.database.getIntOrNull
import com.google.firebase.firestore.auth.User
import nu.paheco.SvenskaRegenter.MainActivity

val TABLENAME = "Regenter"

/*
 View > Tool Windows > App Inspection.
Select the Database Inspector tab.

https://developer.android.com/studio/inspect/database

 */

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, "SvenskaRegenter", null, 6) {
    fun insertData(user: User) {
    }

    fun readData(regentId: Int) {
        val db = this.readableDatabase
        var query = "Select * from $TABLENAME WHERE id = $regentId"
        Log.i("SvenskaRegenterApp", query)
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            Log.i("SvenskaRegenterApp", "Move first")
            var id = 0;
            var name = ""
            var startyear = 0;
            var stopyear = 0;
            var infotext = "";

           // do {
                //Log.i("SvenskaRegenterApp","LOOP" )

                // Int returns 0 if null, but empty strings return null. Try to avoid null exceptions with following crash!
                id = result.getInt(0)   // id is mandatory, don't have to check if it exists
                if (result.getType(1) == FIELD_TYPE_STRING) {
                    name = result.getString(1)
                }
                startyear = result.getInt(2)
                stopyear = result.getInt(3)
                if (result.getType(4) == FIELD_TYPE_STRING) {
                    infotext = result.getString(4)
                }

                Log.i("SvenskaRegenterApp","Query result: " + regentId + " " + id + " " + name + " " + startyear + result.getIntOrNull(2) + result.getInt(3))
/*

                MainActivity.regent.id = id
                MainActivity.regent.name = name
                MainActivity.regent.StartYear = startyear
                MainActivity.regent.StopYear = stopyear
                MainActivity.regent.InfoText = infotext

 */
            }
            //while (result.moveToNext())
        //}
    }

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*
        db.execSQL("DROP TABLE IF EXISTS $employeeTable")
        db.execSQL("DROP TABLE IF EXISTS $deptTable")
        db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger")
        db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22")
        db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid")
        db.execSQL("DROP VIEW IF EXISTS $viewEmps")

         */
        // Use this to add a column
        //db.execSQL("ALTER TABLE Regenter ADD InfoText VARCHAR")

        for (i in 4..100)
        {
            db.execSQL("INSERT INTO Regenter (Id) VALUES ($i)")
        }


        onCreate(db)
    }
}