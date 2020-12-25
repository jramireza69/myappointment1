package www.serpuntual.myappoinment1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import www.serpuntual.myappoinment1.PreferenceHelper.get
import www.serpuntual.myappoinment1.PreferenceHelper.set

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["session"])
            goToMenuActivity()

        btnLogin.setOnClickListener{
            createSessionPreferences()
            goToMenuActivity()
        }

        tvGoToRegister1.setOnClickListener {
            Toast.makeText(this,getString(R.string.please_fill_your_register_data), Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createSessionPreferences(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }
    private fun goToMenuActivity(){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }


}
