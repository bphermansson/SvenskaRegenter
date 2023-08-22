package nu.paheco.SvenskaRegenter.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.SvenskaRegenter.Regent

class HomeViewModel : ViewModel() {

    private val _reponse = MutableLiveData<Regent>()
    val response: LiveData<Regent>
        get() = _reponse

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
        value = "Olof Skötkonung"
    }
    val text: LiveData<String> = _text
}