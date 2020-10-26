package et.ad.poskanri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

class IntroActivity : AppCompatActivity() {
lateinit var viewPager:ViewPager
    lateinit var btnSkip: Button
    lateinit var btnNext: Button
    lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        viewPager = findViewById(R.id.view_pager)
        btnSkip = findViewById(R.id.btn_skip)
        btnNext = findViewById(R.id.btn_next)
        linearLayout = findViewById(R.id.linear_layout)

        val adapter = MainAdapter(this)
        viewPager.adapter=adapter
        adddots(0)
    }

    private fun adddots(i: Int) {

    }
}
