import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import et.ad.poskanri.CustomAdapter
import et.ad.poskanri.MyDatabaseHelper
import et.ad.poskanri.R


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import et.ad.poskanri.MainActivity

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
    private lateinit var purchase_id: ArrayList<String>
    private lateinit var item_name: ArrayList<String>
    private lateinit var purchase_price: ArrayList<String>
    private lateinit var item_qty: ArrayList<String>
    private lateinit var customAdapter: CustomAdapter

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
        purchase_id = ArrayList<String>()
        item_name = ArrayList<String>()
        purchase_price = ArrayList<String>()
        item_qty = ArrayList<String>()
        displayData()
        customAdapter =
            activity?.applicationContext?.let {
                CustomAdapter(
                    it,
                    purchase_id,
                    item_name,
                    item_qty,
                    purchase_price
                )
            }!!
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        return view
    }

    fun displayData() {
        val db = activity?.applicationContext?.let { MyDatabaseHelper(it) }

        val cursor = db?.readAllData()
        if (cursor?.count == 0) {
            Toast.makeText(activity?.applicationContext, "No data", Toast.LENGTH_SHORT).show()

        } else {
            while (cursor?.moveToNext()!!) {
                cursor?.getString(0)?.let { purchase_id.add(it) }
                cursor?.getString(1)?.let { item_name.add(it) }
                cursor?.getString(2)?.let { purchase_price.add(it) }
                cursor?.getString(3)?.let { item_qty.add(it) }
            }
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
