package nu.paheco.SvenskaRegenter.ui.all_regents

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.SvenskaRegenter.PeopleAdapter
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.R
import nu.paheco.SvenskaRegenter.databinding.FragmentAllRegentsBinding
import nu.paheco.SvenskaRegenter.ui.home.HomeViewModel


class AllRegentsFragment : Fragment(R.layout.fragment_all_regents) {
    private var _binding:FragmentAllRegentsBinding? = null;
    private val binding get() = _binding!!;
    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    private lateinit var adapter: PeopleAdapter
    private var clk: MutableLiveData<String> = MutableLiveData("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(AllRegentsViewModel::class.java)

        _binding = FragmentAllRegentsBinding.inflate(inflater, container, false)
        val View = binding.root

        Log.i(MainActivity.logTag, "Create peopleRV")

        adapter = PeopleAdapter(PeopleAdapter.OnClickListener {
                name ->
                val action = AllRegentsFragmentDirections.actionNavigationAllRegentsToNavigationHome(name)
                findNavController().navigate(action)
            }
        )
        val personRecView = binding.peopleRV
        personRecView.adapter = adapter
        personRecView.layoutManager = LinearLayoutManager(requireActivity())

        for(pers in MainActivity.users) {
            Log.i(MainActivity.logTag, pers.toString())
            Log.i(MainActivity.logTag, pers.uid.toString())
            Log.i(MainActivity.logTag, pers.foreName.toString())

//            adapter.people.add(pers.foreName!!)
            adapter.allRegents.add(pers)
        }

  /*
        var regTxt = binding.txtAllRegents
        clk.observe(viewLifecycleOwner, Observer {
            regTxt.text  = clk.value.toString()
        })

        var regTxtContent  = "HEJ"
        clk.value?.let {
            clk.value =  regTxtContent
        }

   */
        Log.i(MainActivity.logTag, "onCreateView")
        return View
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}