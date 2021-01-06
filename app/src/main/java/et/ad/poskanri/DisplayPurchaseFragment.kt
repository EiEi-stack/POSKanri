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
import androidx.room.Room
import et.ad.poskanri.CustomAdapter
import et.ad.poskanri.MyDatabaseHelper
import et.ad.poskanri.R
import et.ad.poskanri.dbclass.PurchaseDatabase
import et.ad.poskanri.dbclass.PurchaseEntity

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
    private lateinit var purchaseItem:MutableList<PurchaseEntity>
    private lateinit var imgViewNoData: ImageView
    private lateinit var tvNoData: TextView

    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        purchaseDatabase = AppDatabase.getApp
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
        purchaseItem= this!!.displayData()!!
                customAdapter =
            activity?.applicationContext?.let {
                CustomAdapter(
                    it,
                    purchaseItem
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
    private fun displayData(): MutableList<PurchaseEntity>? {
        val db = activity?.applicationContext?.let { MyDatabaseHelper(it) }

        val list = db?.readAllData()
        if (list?.size == 0) {
            imgViewNoData.visibility=View.VISIBLE
            tvNoData.visibility=View.VISIBLE

        } else {


//                cursor?.getString(0)?.let { purchase.add() }
//                cursor?.getString(1)?.let { itemName.add(it) }
//                cursor?.getString(2)?.let { purchasePrice.add(it) }
//                cursor?.getString(3)?.let { itemQty.add(it) }
            }

            imgViewNoData.visibility=View.GONE
            tvNoData.visibility=View.GONE
        return list
        }

    companion object {
        lateinit var purchaseDatabase:PurchaseDatabase
    }
}
