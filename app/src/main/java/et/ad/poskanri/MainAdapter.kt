package et.ad.poskanri

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import java.util.*
import java.util.zip.Inflater

class MainAdapter(introActivity: IntroActivity) : PagerAdapter() {
    lateinit var context: Context
    lateinit var inflater: LayoutInflater
    private val listImage = mutableListOf(
        R.drawable.ic_purchase,
        R.drawable.ic_monetization,
        R.drawable.ic_customer,
        R.drawable.ic_profit_loss
    )
    private val listTitle = mutableListOf("Purchase", "Sale Price", "Customer Detail", "Profit and Losss")

    private val listColor =
        mutableListOf(
            Color.rgb(147, 112, 21),
            Color.rgb(255, 127, 80),
            Color.rgb(100, 149, 237),
            Color.rgb(118, 98, 200)
        )

    override fun getCount(): Int {
        return listTitle.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int) {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.slide_item,container,false)

        val linearLayout = view.findViewById(R.id.linear_layout)
        val imageView = view.findViewById(R.id.image_view)
        val textView = view.findViewById(R.id.text_view)

        imageView.setImageResource(listImage[position])
        textView.setImageResource(listTitle[position])
        linearLayout.setBackgroundColor(listColor[position])

        container.addView(view)
        return
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(object)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view ==object
    }

}