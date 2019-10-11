package a193532_c195741.ft.unicamp.br.aula03.kotlin.ui.tools

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ToolsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tools Fragment"
    }
    val text: LiveData<String> = _text
}