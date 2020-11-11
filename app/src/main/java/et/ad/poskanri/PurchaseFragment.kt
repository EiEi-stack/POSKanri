package et.ad.poskanri

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import et.ad.poskanri.dbclass.Purchase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PurchaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PurchaseFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_purchase, container, false)
        // Inflate the layout for this fragment
        val btnRegister = view.findViewById<Button>(R.id.btn_purchase_register)
        val etPurchaseItemName = view.findViewById<EditText>(R.id.et_purchase_item_name)
        val etPurchasePrice = view.findViewById<EditText>(R.id.et_purchase_price)
        val etPurchaseQty = view.findViewById<EditText>(R.id.et_purchase_qty)
        val etPurchaseComment = view.findViewById<EditText>(R.id.et_purchase_comment)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        btnRegister.setOnClickListener(View.OnClickListener {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
//            val purchase = Purchase()
//            purchase.itemName = etPurchaseItemName.text.toString().trim()
//            purchase.purchasePrice = Integer.parseInt(etPurchasePrice.text.toString().trim())
//            purchase.itemQty = Integer.parseInt(etPurchaseQty.text.toString().trim())
//            purchase.comment = etPurchaseComment.text.toString().trim()

            val dbHelper= MyDatabaseHelper(activity!!.applicationContext)
            val isSuccess =dbHelper.addPurchaseItem(etPurchaseItemName.text.toString().trim(),
                Integer.valueOf(etPurchasePrice.text.toString().trim()),
                Integer.valueOf(etPurchaseQty.text.toString().trim()),etPurchaseComment.text.toString().trim())

            if (isSuccess.toInt() != -1) {
                Toast.makeText(context, "追加する成功しました", Toast.LENGTH_SHORT).show()
                etPurchaseItemName.text.clear()
                etPurchasePrice.text.clear()
                etPurchaseQty.text.clear()
                etPurchaseComment.text.clear()

                val fragment = SaleFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.frame_content, fragment)
                fragmentTransaction?.commit()
            }
        })

        purchase_id = ArrayList<String>()
        item_name = ArrayList<String>()
        purchase_price = ArrayList<String>()
        item_qty = ArrayList<String>()
        displayData()
        customAdapter =
            activity?.applicationContext?.let { CustomAdapter(it, purchase_id, item_name, item_qty, purchase_price) }!!
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
         * @return A new instance of fragment PurchaseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PurchaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
