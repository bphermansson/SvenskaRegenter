package nu.paheco.SvenskaRegenter.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.MainActivity.Companion.users
import nu.paheco.SvenskaRegenter.OnSwipeTouchListener
import nu.paheco.SvenskaRegenter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    // Livedata variabels
    var counter: MutableLiveData<Int> = MutableLiveData(0)
    var regForeName: MutableLiveData<String?> = MutableLiveData("")
    var regLastName: MutableLiveData<String?> = MutableLiveData("")
    var regYears: MutableLiveData<String> = MutableLiveData("")
    var regInfo: MutableLiveData<String> = MutableLiveData("")
    var regPicture: MutableLiveData<String> = MutableLiveData("")
    var regPictureText: MutableLiveData<String> = MutableLiveData("")

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

// Livedata observe
    regForeName.observe(viewLifecycleOwner, Observer {
        binding.txtRegentForeName.text  = regForeName.value
    })
    regLastName.observe(viewLifecycleOwner, Observer {
        binding.txtRegentLastName.text  = regLastName.value
    })
    regYears.observe(viewLifecycleOwner, Observer {
        binding.txtYears.text  = regYears.value.toString()
    })
    regInfo.observe(viewLifecycleOwner, Observer {
        binding.txtSingleRegentInfo.text  = regInfo.value
    })
    regPicture.observe(viewLifecycleOwner, Observer {
        //binding.imgSingleRegent.srcCompat = regPicture.value
        //binding.imgSingleRegent.setImageDrawable(regPicture.value)
    })
    regPictureText.observe(viewLifecycleOwner, Observer {
        binding.txtImageText.text  = regPictureText.value
    })

    // ViewBinding
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    // Bind textview
   // val textView: TextView = binding.txtRegentForName

    return root
}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = ""

        val args: HomeFragmentArgs by navArgs()
        Log.i(MainActivity.logTag, "Id: " + args.id)

        if(users.isNotEmpty()) {
            Log.i(MainActivity.logTag, "Regent: " + users[args.id.toInt()].toString())
            Log.i(MainActivity.logTag, "Regent: " + users[args.id.toInt()].foreName)

            Toast.makeText(requireContext(), args.id, Toast.LENGTH_SHORT).show()
            var localId = args.id.toInt() -1
            if (localId < 0) {
                localId = 0
            }
            regForeName.value = users[localId].foreName
            regLastName.value = users[localId].lastName
            var localYears : String
            localYears = users[localId].StartYear.toString() + " - " + users[localId].StopYear
            regYears.value = localYears
            regInfo.value = users[localId].InfoText
        }

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
        Log.i(MainActivity.logTag,"Id: " + MainActivity.currentRegentId)
        getRegentData()
        regForeName.value="HEJ"

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
        /*
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

         */
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




