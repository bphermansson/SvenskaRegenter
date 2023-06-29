package nu.paheco.SvenskaRegenter;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import nu.paheco.SvenskaRegenter.databinding.ActivityMainBinding
import nu.pahecu.SvenskaRegenter.DataBaseHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        val regent = Regent()
        //var regentList: MutableList<Regent> = mutableListOf<Regent>()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var clayout: ConstraintLayout

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val b = DataBaseHandler(this)
        b.readData(2)
        Log.i("SvenskaRegenterApp","Reg Res: "
                + regent.name.toString()
                + " " + regent.StartYear
                + " " + regent.StopYear
                + " " + regent.InfoText
        )


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



        /*
        This is for swipe function, shall be in a fragment

        clayout = findViewById(R.id.mainlayout)

        clayout.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                Toast.makeText(
                    this@MainActivity,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onSwipeUp() {
                super.onSwipeUp()
                Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onSwipeDown() {
                super.onSwipeDown()
                Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()
            }
        })

         */
    }
}
data class Regent(
    var id: Int,
    var name: String,
    var StartYear: Int,
    var StopYear: Int,
    var InfoText: String
    )
{
    // This makes it possible to initialize an empty instance
    constructor() : this(0, "dummy author", 0, 0, "")
}