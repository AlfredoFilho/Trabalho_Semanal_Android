package a193532_c195741.ft.unicamp.br.aula03.kotlin.ui.share

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is share Fragment"
    }
    val text: LiveData<String> = _text
}