package com.example.SvenskaRegenter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow
import nu.paheco.SvenskaRegenter.MainActivity
import java.security.AccessController.getContext

/*

To prepopulate the db.
-Select the app directory in the Project view.
-Click File/New/Foilder/Assets Folder
-Start app in debug mode
-Open App inspection
-Run a statement to insert data, like 'INSERT INTO person VALUES (1, "Terence", "Landon")'
-Highlight the db name and right-click, export as a .db file to the assets dir created above
-Load the db as prepopulated data in the code:
MyDatabase::class.java,"my-database").createFromAsset("my-database.db").build()

Ref: https://www.daniweb.com/programming/mobile-development/tutorials/537384/android-native-how-to-prepopulate-a-room-database

If database cant be exported because its offline,
open the database file through Android Studios Device File Explorer.
<appname>/databases

 */
@Database(entities = [Regent::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun regentDao(): RegentDao
/*
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java,
                    "SvenskaRegenter")
                    .fallbackToDestructiveMigration()
                    //.createFromAsset("SvenskaRegenter_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
        /*
        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }
*/
        fun destroyInstance() {
            INSTANCE = null
        }

        fun openDB(): List<Regent> {
            var t = AppDatabase.getInstance(this)
            var personList = t.regentDao().getAllRegents()
            return personList
        }


        //https://medium.com/@myofficework000/migration-in-room-db-babee083c562
/*
        val MIGRATION_1_3 = object : Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL("Alter TABLE Blog ADD COLUMN body TEXT ")
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "SvenskaRegenter.db"
            )
                //.addMigrations(MIGRATION_1_3)
                .fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationFrom(1)
                .allowMainThreadQueries()
                .build()
        }
*/
    }

 */

}
