package et.ad.poskanri

import DisplayPurchaseFragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import et.ad.poskanri.dbclass.PurchaseEntity

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
    private lateinit var imgViewPurchase: ImageView
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
        val etPurchaseTax = view.findViewById<EditText>(R.id.et_purchase_tax)
        val etItemSize = view.findViewById<EditText>(R.id.et_purchase_size)
        val etItemType = view.findViewById<EditText>(R.id.et_purchase_type)
        val etItemWeight = view.findViewById<EditText>(R.id.et_purchase_weight)
        imgViewPurchase = view.findViewById<ImageView>(R.id.et_purchase_pic)
        imgViewPurchase.setOnClickListener(View.OnClickListener {
            getImageFromGallery()
        })
        val util = Util()
        val imageByte = util.getBytes(imgViewPurchase.drawable.toBitmap())
        btnRegister.setOnClickListener(View.OnClickListener {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            val purchase = PurchaseEntity()
            if (etPurchaseItemName.text.toString() != "" && etPurchasePrice.text.toString() != "" && etPurchaseComment.text.toString() != "" && etPurchaseTax.text.toString() != ""
                && etItemSize.text.toString() != "" && etItemType.text.toString() != "" && etItemWeight.text.toString() != "" && imageByte != null
            ) {
                purchase.itemName = etPurchaseItemName.text?.trim().toString()
                purchase.purchasePrice =
                    etPurchasePrice.text?.toString()?.trim()?.let { it1 -> Integer.parseInt(it1) }!!
                purchase.comment = etPurchaseComment.text?.toString()?.trim().toString()
                purchase.tax =
                    etPurchaseTax.text?.toString()?.trim()?.let { it1 -> Integer.parseInt(it1) }!!
                purchase.size = etItemSize.text?.toString()?.trim().toString()
                purchase.itemType = etItemType.text?.toString()?.trim().toString()
                purchase.itemWeight = etItemWeight.text?.toString()?.trim().toString()
                purchase.image = imageByte


                val dbHelper = MyDatabaseHelper(activity!!.applicationContext)
                val isSuccess = dbHelper.addPurchaseItem(purchase)

                if (isSuccess.toInt() != -1) {
                    Toast.makeText(context, "追加する成功しました", Toast.LENGTH_SHORT).show()
                    etPurchaseItemName.text.clear()
                    etPurchasePrice.text.clear()
                    etPurchaseQty.text.clear()
                    etPurchaseComment.text.clear()
                    etPurchaseTax.text.clear()
                    etItemSize.text.clear()
                    etItemType.text.clear()
                    etItemWeight.text.clear()

                    val fragment = DisplayPurchaseFragment()
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.frame_content, fragment)
                    fragmentTransaction?.commit()
                } else {
                    Toast.makeText(context,"Your data is blank",Toast.LENGTH_SHORT).show()
                }
            }
        })
        return view
    }

    private fun getImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (null != data) {
            val selectedImage = data.data
            imgViewPurchase.setImageURI(selectedImage)
        } else {
            Toast.makeText(context, "Sorry,Can't pick up image", Toast.LENGTH_SHORT).show()
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
        private const val PERMISSION_CODE = 1001
        private const val RESULT_CODE = 1

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
