package nu.pahecu.SvenskaRegenter

/*
https://stackoverflow.com/questions/74190789/where-in-kotlin-android-project-folder-to-copy-a-sqlite-database-file
https://www.geeksforgeeks.org/android-sqlite-database-in-kotlin/
 */

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor.*
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.database.getIntOrNull
import com.google.firebase.firestore.auth.User

val DATABASENAME = "MY DATABASE"
val TABLENAME = "Regenter"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_ID = "id"

const val DATABASE_NAME = "SvenskaRegenter" /* the database name */
const val ASSET_NAME = "SvenskaRegenter" /* The name of the asset file which could be different if required */
const val DATABASE_VERSION = 1
const val ASSET_COPY_BUFFER_SIZE = 8 * 1024

/*
Use the Database Inspector to add new data into DB
 View > Tool Windows > App Inspection.
Select the Database Inspector tab.

https://developer.android.com/studio/inspect/database

 */

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, "SvenskaRegenter", null, 3) {

    fun insertData(user: User) {
    }

        @SuppressLint("Range")
        fun readData(): MutableList<Int> {

        //val list: MutableListOf<String>, <Int> = ArrayList()
        //val intMutableList: MutableList = mutableListOf()
        var list: MutableList<Int> = mutableListOf<Int>()

        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            var id = 0;
            var name = ""
            var startyear = 0;

            do {
                Log.i("SvenskaRegenterApp","LOOP" )

                id = result.getInt(0)   // id is mandatory, don't have to check if it exists
                if (result.getType(1) == FIELD_TYPE_STRING) {
                    name = result.getString(1)
                }

                //if (result.getType(2) != FIELD_TYPE_NULL) {
                    startyear = result.getInt(2)
                //}


                Log.i("SvenskaRegenterApp","Res: " + id + " " + name + " " + startyear + result.getIntOrNull(2) + result.getInt(3))
                list.add(id)
/*
                val user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.age = result.getString(result.getColumnIndex(COL_AGE)).toInt()
                list.add(user)
*/


            }
            while (result.moveToNext())
        }
        return list
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
        db.execSQL("ALTER TABLE Regenter ADD StopYear INTEGER")


        onCreate(db)
    }
}

data class Regent(val id: Int, val name: String, val StartYear: Int, val StopYear: Int)




/*
class DBHelper: SQLiteOpenHelper {

    private constructor(context: Context) : super(context, DATABASE_NAME,null, DATABASE_VERSION)

    companion object {
        private var instance: DBHelper?=null
        fun getInstance(context: Context): DBHelper {
            if (this.instance==null) {
                getAndCopyAssetDatabase(context)
                instance = DBHelper(context);
            }
            return instance as DBHelper
        }

       // fun getName(): Cursor? {
            fun getName(context: Context): DBHelper {

            // here we are creating a readable
            // variable of our database
            // as we want to read value from it
           //
           // below code returns a cursor to
            // read data from the database
            return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        }

        private fun ifDatabaseExists(context: Context): Boolean {
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            if (dbFile.exists()) return true
            else if (!dbFile.parentFile.exists()) {
                dbFile.parentFile.mkdirs()
            }
            return false
        }

        private fun getAndCopyAssetDatabase(context: Context) {
            if (ifDatabaseExists(context)) return
            context.assets.open(ASSET_NAME).copyTo(
                FileOutputStream(context.getDatabasePath(DATABASE_NAME)),
                ASSET_COPY_BUFFER_SIZE
            )
        }
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "SvenskaRegenter"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "gfg_table"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "name"

        // below is the variable for age column
        val AGE_COL = "age"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        // should not do anything if using a pre-packaged database
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // May or may not be used
    }
}

//class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    //SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    /*
    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(name : String, age : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }
*/


 */