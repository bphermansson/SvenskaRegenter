package com.example.SvenskaRegenter

/*
https://stackoverflow.com/questions/74190789/where-in-kotlin-android-project-folder-to-copy-a-sqlite-database-file
https://www.geeksforgeeks.org/android-sqlite-database-in-kotlin/
 */

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

const val DATABASE_NAME = "SvenskaRegenter" /* the database name */
const val ASSET_NAME = "SvenskaRegenter" /* The name of the asset file which could be different if required */
const val DATABASE_VERSION = 1
const val ASSET_COPY_BUFFER_SIZE = 8 * 1024

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
        private val DATABASE_NAME = "GEEKS_FOR_GEEKS"

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
