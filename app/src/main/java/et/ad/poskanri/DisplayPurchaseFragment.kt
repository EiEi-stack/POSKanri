import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import et.ad.poskanri.CustomAdapter
import et.ad.poskanri.MyDatabaseHelper
import et.ad.poskanri.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DisplayPurchaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayPurchaseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var purchaseId: ArrayList<String>
    private lateinit var itemName: ArrayList<String>
    private lateinit var purchasePrice: ArrayList<String>
    private lateinit var itemQty: ArrayList<String>
    private lateinit var imgViewNoData: ImageView
    private lateinit var tvNoData: TextView

    lateinit var customAdapter: CustomAdapter

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
        val view = inflater.inflate(R.layout.fragment_display_purchase, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        imgViewNoData = view.findViewById<ImageView>(R.id.img_view_no_data)
        tvNoData = view.findViewById<TextView>(R.id.tv_no_data)
        purchaseId = ArrayList<String>()
        itemName = ArrayList<String>()
        purchasePrice = ArrayList<String>()
        itemQty = ArrayList<String>()
        displayData()
        customAdapter =
            activity?.applicationContext?.let {
                CustomAdapter(
                    it,
                    purchaseId,
                    itemName,
                    purchasePrice,
                    itemQty
                )
            }!!
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        return view
    }

     override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        if(requestCode ==1){
            val fragment = DisplayPurchaseFragment()
            activity?.supportFragmentManager?.beginTransaction()?.remove(fragment)?.commit()

            val newFragment = DisplayPurchaseFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.frame_content, newFragment)
            fragmentTransaction?.commit()
            customAdapter.notifyDataSetChanged()
        }
    }
    private fun displayData() {
        val db = activity?.applicationContext?.let { MyDatabaseHelper(it) }

        val cursor = db?.readAllData()
        if (cursor?.count == 0) {
            imgViewNoData.visibility=View.VISIBLE
            tvNoData.visibility=View.VISIBLE

        } else {
            while (cursor?.moveToNext()!!) {
                cursor?.getString(0)?.let { purchaseId.add(it) }
                cursor?.getString(1)?.let { itemName.add(it) }
                cursor?.getString(2)?.let { purchasePrice.add(it) }
                cursor?.getString(3)?.let { itemQty.add(it) }
            }

            imgViewNoData.visibility=View.GONE
            tvNoData.visibility=View.GONE
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DisplayPurchaseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DisplayPurchaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}