package nu.paheco.SvenskaRegenter.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.SvenskaRegenter.AppDatabase
import com.example.SvenskaRegenter.PeopleAdapter
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.OnSwipeTouchListener
import nu.paheco.SvenskaRegenter.databinding.FragmentHomeBinding
import nu.pahecu.SvenskaRegenter.DataBaseHandler

class HomeFragment : Fragment() {
    // Livedata variabels
    var counter: MutableLiveData<Int> = MutableLiveData(0)
    var regName: MutableLiveData<String> = MutableLiveData("")



    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


/*
        homeViewModel.text.observe(viewLifecycleOwner) {
            var name = MainActivity.regent.name
            textView.text = name
        }
*/
// Livedata observe

    regName.observe(viewLifecycleOwner, Observer {
        binding.txtName.text  = regName.value.toString()
    })

        regName.value = "=?"

    // ViewBinding
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    // Bind textview
    val textView: TextView = binding.txtName

    return root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)

    lateinit var clayout: ConstraintLayout
    clayout = binding.fraghome
    clayout.setOnTouchListener(object : OnSwipeTouchListener(context) {
    override fun onSwipeLeft() {
        super.onSwipeLeft()

      //  val c = binding.txtName


        Toast.makeText(context, "Swipe Left gesture detected",
            Toast.LENGTH_SHORT)
            .show()
    }
    override fun onSwipeRight() {
        super.onSwipeRight()
        Toast.makeText(
            context,
            "Swipe Right gesture detected",
            Toast.LENGTH_SHORT
        ).show()
        MainActivity.currentRegentId++
        Log.i("SvenskaRegenterApp","Id: " + MainActivity.currentRegentId)
        getRegentData()
        regName.value="HEJ"

    }
    override fun onSwipeUp() {
        super.onSwipeUp()
        Toast.makeText(context, "Swipe up gesture detected", Toast.LENGTH_SHORT)
            .show()
    }
    override fun onSwipeDown() {
        super.onSwipeDown()
        Toast.makeText(context, "Swipe down gesture detected", Toast.LENGTH_SHORT)
            .show()
    }
}
    )
//regName.value = "Patrik"
    MainActivity.currentRegentId = 1
    //getRegentData()
}
private fun getRegentData() {
    try {
        val b = DataBaseHandler(requireContext())
        b.readData(MainActivity.currentRegentId)
        Log.i("SvenskaRegenterApp","Reg Res: "
                /*
                + MainActivity.currentRegentId
                + " " + UserDB.regent.name
                + " " + MainActivity.regent.StartYear
                + " " + MainActivity.regent.StopYear
                + " " + MainActivity.regent.InfoText

                 */
        )
    }
    catch (ex: Exception){
        Log.i("SvenskaRegenterApp","Error: " + ex)
    }

}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




