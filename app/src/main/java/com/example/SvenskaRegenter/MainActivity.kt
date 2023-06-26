package nu.paheco.SvenskaRegenter;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.SvenskaRegenter.DBHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import nu.paheco.SvenskaRegenter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var clayout: ConstraintLayout

        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        db = DBHelper.getInstance(this)
        db.writableDatabase /* Force Database Access (open) */

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