package com.example.SvenskaRegenter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.*
import kotlinx.coroutines.flow.Flow

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

// A Data Class — Defining the table and all the fields for the table
@Entity(tableName = "Regenter")
data class Regent(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(name = "StartYear") var StartYear: Int,
    @ColumnInfo(name = "StopYear") var StopYear: Int,
    @ColumnInfo(name = "InfoText") var InfoText: String
)



// A DAO Interface — Includes all the possible Queries used in the Project, using Annotations.
@Dao
interface RegentDao {
    @Query("SELECT * FROM Regenter")
    suspend fun getAllRegents(): List<Regent>

    @Query("SELECT * FROM Regenter where last_name = ''")
    fun getNoLastname(): List<Regent>

    @Query("SELECT * FROM Regenter WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Regent>

    @Query("SELECT * FROM Regenter WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Regent

    @Insert
    fun insertAll(vararg users: Regent)

    @Delete
    fun delete(user: Regent)

    @Query("SELECT * FROM Regenter")
    fun getNotes(): List<Regent>
}

@Database(entities = [Regent::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    abstract fun regentDao(): RegentDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "SvenskaRegenter.db").build()
            }
            return INSTANCE!!
        }

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

        fun destroyInstance() {
            INSTANCE = null
        }

        //https://medium.com/@myofficework000/migration-in-room-db-babee083c562
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "SvenskaRegenter.db"
            )
                //.addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}
