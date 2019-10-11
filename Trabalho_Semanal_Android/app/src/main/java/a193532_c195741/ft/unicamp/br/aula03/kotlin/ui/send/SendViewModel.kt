package a193532_c195741.ft.unicamp.br.aula03.kotlin.ui.send

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is send Fragment"
    }
    val text: LiveData<String> = _text
}