package nu.paheco.SvenskaRegenter.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
        value = "Olof Skötkonung"
    }
    val text: LiveData<String> = _text
}