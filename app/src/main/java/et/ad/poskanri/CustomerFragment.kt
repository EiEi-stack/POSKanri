package et.ad.poskanri

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_customer.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_customer, container, false)
        val pieChart = view.findViewById<PieChart>(R.id.pieChartData)

        val pieEntries= arrayListOf<PieEntry>()

        pieEntries.add(PieEntry(30.0f))
        pieEntries.add(PieEntry(40.0f))
        pieEntries.add(PieEntry(35.0f))

        pieChart.animateXY(1000,1000)
        val pieDataSet = PieDataSet(pieEntries,"This is Pie Chart Label")
        pieDataSet.setColors(
            resources.getColor(R.color.colorAccent),
            resources.getColor(R.color.colorPrimaryDark),
            resources.getColor(R.color.colorPrimary)

        )
        val pieData = PieData(pieDataSet)
        pieChart.centerText ="This is center Text"
        pieChart.setCenterTextColor(resources.getColor(android.R.color.holo_red_dark))
        pieChart.setCenterTextSize(15f)
        pieChart.legend.isEnabled= false
        pieChart.description.isEnabled=false
        pieChart.description.text="This is test Description"
        pieChart.holeRadius=75f

        pieData.setDrawValues(true)
        pieChart.data = pieData
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
