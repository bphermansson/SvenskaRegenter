package nu.paheco.SvenskaRegenter.ui.all_regents

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.SvenskaRegenter.AppDatabase
import com.example.SvenskaRegenter.PeopleAdapter
import com.example.SvenskaRegenter.Regent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.R
import nu.paheco.SvenskaRegenter.databinding.FragmentAllRegentsBinding

class AllRegentsFragment : Fragment() {
    private var _binding: FragmentAllRegentsBinding? = null

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PeopleAdapter.ViewHolder>? = null

    //private val appDatabase by lazy { AppDatabase.getDatabase(requireContext()).regentDao()}

    var personadapter = PeopleAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

     suspend fun openDB(): List<Regent> {
         //lifecycleScope.launch {

             var t = AppDatabase.getInstance(requireContext())
             var personList = t.regentDao().getAllRegents()

             for (person in personList)
                 Log.i(MainActivity.logTag, "List names: " + person)
         //}
         return personList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(AllRegentsViewModel::class.java)

        _binding = FragmentAllRegentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtAllRegents
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        Log.i(MainActivity.logTag, "onCreateView")

        var regentToAdd = Regent(1, "l", "Linkin Park", 1999, 2121, "Info")
        personadapter.people.add(regentToAdd)
        Log.i(MainActivity.logTag, "Reg: " + regentToAdd)

        val personRecView = binding.peopleRV
        personRecView.adapter = personadapter
        personRecView.layoutManager = LinearLayoutManager(requireActivity())

//      personadapter.people.add(addname)
        var allRegents:  List<Regent> = listOf()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO)  {
            //viewModelScope.launch(Dispatchers.IO) {
            var regentToAdd = Regent(1, "Ride", "Sevendust", 1999, 2121, "Info")
            personadapter.people.add(regentToAdd)
            Log.i(MainActivity.logTag, "regentToAdd: " + regentToAdd)

            allRegents = async { openDB() }.await()

            //Log.i(MainActivity.logTag, "Found: " + allRegents.size.toString())

            //Log.i(MainActivity.logTag, "allRegents: " + allRegents)

            try {
                Log.i(MainActivity.logTag, "Regent0: " + allRegents[0])
                personadapter.people.add(allRegents[0])
            } catch (exception: Exception) {
                Log.i(MainActivity.logTag, exception.toString())
            }

        }


            printdata(allRegents)
            Log.i(MainActivity.logTag, allRegents.size.toString())


            /*
            for (item in allRegents) {
                Log.i(MainActivity.logTag, "Item in loop: " + item)
                //personadapter.people.add(regentToAdd)
                personadapter.people.add(item)
            }
            */
        //}
/*
        for (item in allRegents) {
            Log.i(MainActivity.logTag, "Item: " + item)
            //personadapter.people.add(regentToAdd)
            personadapter.people.add(item)
        }
        personadapter!!.notifyDataSetChanged()

 */
        return root
    }



    fun printdata(data: List<Regent>) {
        Log.i(MainActivity.logTag, "Data: " + data)

        var regentToAdd = Regent(1, "l", "Sevendust", 1999, 2121, "Info")
        personadapter.people.add(regentToAdd)
        Log.i(MainActivity.logTag, "Data: " + regentToAdd)
        try{
            personadapter.people.add(data[0])
            Log.i(MainActivity.logTag, "Data0: " + data[0])
        } catch (exception: Exception) {
            Log.i(MainActivity.logTag, exception.toString())
        }
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
/*
        var regentToAdd = Regent(1, "P", "H", 1973, 2121, "Info")
        var allRegents: List<Regent> = listOf() // Initialize empty list
        Log.i(MainActivity.logTag, "onViewCreated")

        personadapter.people.add(regentToAdd)

        //allRegents[1].uid = 0

        val regents: Regent
        lifecycleScope.launch(Dispatchers.Main) {
            allRegents = async {openDB()}.await()
        }

        Log.i(MainActivity.logTag, "Names from db: " + allRegents.toString())
       //Log.i(MainActivity.logTag, "Name: " + allRegents[0].firstName.toString())

        val personRecView = requireView().findViewById<RecyclerView>(R.id.peopleRV)
        personRecView.adapter = personadapter
        personRecView.layoutManager = LinearLayoutManager(context)
*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}