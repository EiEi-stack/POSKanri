package et.ad.poskanri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
lateinit var btnReload: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnReload =findViewById(R.id.btn_reload)
        btnReload.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,SplashScreenActivity::class.java)
            startActivity(intent)
        })
    }
}
