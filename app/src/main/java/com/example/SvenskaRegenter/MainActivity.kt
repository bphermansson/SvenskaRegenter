package nu.paheco.SvenskaRegenter;

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.SvenskaRegenter.AppDatabase
import com.example.SvenskaRegenter.PeopleAdapter
import com.example.SvenskaRegenter.Regent

import com.google.android.material.bottomnavigation.BottomNavigationView
import nu.paheco.SvenskaRegenter.databinding.ActivityMainBinding

/*
To add regent:
insert into Regenter values (3,"Anund ", "Jakob", 1022, 1050, "Info")
To edit:
update Regenter set  first_name = "Erik" where uid = 1
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var clayout: ConstraintLayout

        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        Log.i(MainActivity.logTag, "main")

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_all_regents, R.id.navigation_about
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "SvenskaRegenter"
        )
            .allowMainThreadQueries()
            .build()

        val regentDao = db.regentDao()
        //val users: List<Regent> = regentDao.getAllRegents()
        users = regentDao.getAllRegents()
        /*
        for(pers in users) {
            Log.i(MainActivity.logTag, pers.toString())
            Log.i(MainActivity.logTag, pers.firstName.toString())

        }
        Log.i(MainActivity.logTag, users.size.toString())
        */
    }

    companion object {

        var logTag: String = "SimpleRoomDBLog"
        var currentRegentId = 0 // Which regent to show?
        var users: List<Regent> = emptyList()
        //val users: List<Regent> = List<Regent>


    }
}


//{
    // This makes it possible to initialize an empty instance
    //constructor() : this(0, "dummy author", 0, 0, "")
//}