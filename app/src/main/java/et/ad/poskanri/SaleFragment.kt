package et.ad.poskanri

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SaleFragment : Fragment() {
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
        val view= inflater.inflate(R.layout.fragment_sale, container, false)
        val db = activity?.applicationContext?.let { MyDatabaseHelper(it) }
        val spinnerItem = view.findViewById<Spinner>(R.id.spinnerItem)
        val list = db?.readAllData()
        val itemList = mutableListOf<String>()
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val btnCalculate = view.findViewById<TextView>(R.id.btnCalculate)
        val tvCost = view.findViewById<TextView>(R.id.tvCost)
        val tvSalePrice = view.findViewById<TextView>(R.id.tvSalePrice)

        if(list!=null){
            for(li in list){
                itemList.add(li.itemName)
            }
        }
        val itemListAdapter = ArrayAdapter(activity!!.applicationContext,android.R.layout.simple_spinner_dropdown_item ,itemList)
        itemListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerItem.adapter = itemListAdapter

        spinnerItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                val db = activity?.applicationContext?.let { MyDatabaseHelper(it) }
                val price = db?.readPrice(item)
                tvPrice.text= price.toString()
            }

        }
        btnCalculate.setOnClickListener(View.OnClickListener {
            val itemPrice =tvPrice.text.toString()
            val transportationCost = tvCost.text.toString()
            val calculateSalePrice = itemPrice.toDouble()*(transportationCost.toDouble()/100) + itemPrice.toDouble()
            tvSalePrice.text = calculateSalePrice.toString()
        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SaleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SaleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

