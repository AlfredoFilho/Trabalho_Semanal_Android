package a193532_c195741.ft.unicamp.br.aula03.kotlin

import a193532_c195741.ft.unicamp.br.aula03.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }

    override fun onStart(){
        super.onStart()
        Log.i("Kotlin","onStart")
    }
    override fun onResume(){
        super.onResume()
        Log.i("Kotlin","onResume")
    }
    override fun onRestart(){
        super.onRestart()
        Log.i("Kotlin","onRestart")
    }
    override fun onPause(){
        super.onPause()
        Log.i("Kotlin","onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.i("Kotlin","onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i("Kotlin","onDestroy")
    }

}
