package nu.paheco.SvenskaRegenter;

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.SvenskaRegenter.AppDatabase

import com.google.android.material.bottomnavigation.BottomNavigationView
import nu.paheco.SvenskaRegenter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var clayout: ConstraintLayout

        super.onCreate(savedInstanceState)

        /*
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-database"
        )
            .createFromAsset("SvenskaRegenter.db")
            .build()
*/
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
    }

    companion object {
        var logTag: String = "SimpleRoomDBLog"

        var currentRegentId = 0 // Which regent to show?


    }

}

//{
    // This makes it possible to initialize an empty instance
    //constructor() : this(0, "dummy author", 0, 0, "")
//}