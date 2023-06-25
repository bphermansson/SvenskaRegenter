package nu.paheco.SvenskaRegenter.ui.all_regents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllRegentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is all regents Fragment"
    }
    val text: LiveData<String> = _text
}