package et.ad.poskanri

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
    lateinit var dataAccess: DatabaseAccess
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
        btnRegister.setOnClickListener(View.OnClickListener {
            val purchase = Purchase()
            purchase.itemName = etPurchaseItemName.text.toString().trim()
            purchase.purchasePrice = Integer.parseInt(etPurchasePrice.text.toString().trim())
            purchase.itemQty = Integer.parseInt(etPurchaseQty.text.toString().trim())
            purchase.comment = etPurchaseComment.text.toString().trim()
            openDB()
            val isSuccess = dataAccess.addPurchase(purchase)
            if (isSuccess) {
                Toast.makeText(context, "追加する成功しました", Toast.LENGTH_SHORT).show()
                etPurchaseItemName.text.clear()
                etPurchasePrice.text.clear()
                etPurchaseQty.text.clear()
                etPurchaseComment.text.clear()
            }
        })
        return view
    }

    fun openDB() {
        dataAccess = context?.applicationContext?.let {
            DatabaseAccess.getInstance(
                it
            )
        }!!
        dataAccess.open()
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
