package et.ad.poskanri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed

class SplashScreenActivity : AppCompatActivity() {
private val SPLASH_TIME_OUT:Long =3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)

    }
}
