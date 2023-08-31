package nu.paheco.SvenskaRegenter.ui.all_regents

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class AllRegentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is all regents Fragment"
    }
    val text: LiveData<String> = _text
}

