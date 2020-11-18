package et.ad.poskanri

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import et.ad.poskanri.dbclass.Purchase

class UpdateActivity : AppCompatActivity() {
    lateinit var purchseItem: EditText
    lateinit var purchasePrice: EditText
    lateinit var purchaseQty: EditText
    lateinit var purchaseTax: EditText
    lateinit var purchaseSize: EditText
    lateinit var purchaseType: EditText
    lateinit var purchaseWeight: EditText
    lateinit var purchaseComment: EditText
    lateinit var updateBtn: Button
    lateinit var btnDelete: Button
    lateinit var id: String
    lateinit var item: String
    lateinit var price: String
    lateinit var qty: String
    lateinit var tax: String
    lateinit var size: String
    lateinit var type: String
    lateinit var weight: String
    lateinit var comment: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        purchseItem = findViewById(R.id.et_item_name2)
        purchasePrice = findViewById(R.id.et_item_price2)
        purchaseQty = findViewById(R.id.et_item_qty2)
        purchaseTax = findViewById(R.id.et_purchase_tax2)
        purchaseSize = findViewById(R.id.et_purchase_size2)
        purchaseType = findViewById(R.id.et_purchase_type2)
        purchaseWeight = findViewById(R.id.et_purchase_weight2)
        purchaseComment = findViewById(R.id.et_purchase_comment2)
        updateBtn = findViewById(R.id.btn_update)
        btnDelete = findViewById(R.id.btn_delete)

        getIntentData()
        updateBtn.setOnClickListener(View.OnClickListener {

            val purchase = Purchase()
            purchase.itemName = purchseItem.text.toString()
            purchase.purchasePrice = Integer.parseInt(purchasePrice.text.toString())
            purchase.itemQty = Integer.parseInt(purchaseQty.text.toString())
            purchase.tax = Integer.parseInt(purchaseTax.text.toString())
            purchase.size = purchaseSize.text.toString()
            purchase.itemType = purchaseType.text.toString()
            purchase.itemWeight = purchaseWeight.text.toString()
            purchase.comment = purchaseComment.text.toString()
            val dbHelper = MyDatabaseHelper(this@UpdateActivity)
            val result = dbHelper.updateData(id, purchase)
            if (result == -1) {
                Toast.makeText(applicationContext, "Update fail", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Update Successfully", Toast.LENGTH_SHORT).show()
            }
        })

        btnDelete.setOnClickListener(View.OnClickListener {
            confirmDialog()
        })


        val ab = supportActionBar
        ab?.title = item.toString()
    }

    private fun getIntentData() {
        if (intent.hasExtra("id") && intent.hasExtra("item") && intent.hasExtra("price") && intent.hasExtra(
                "qty"
            ) && intent.hasExtra("tax") && intent.hasExtra("size") && intent.hasExtra("type") && intent.hasExtra(
                "weight"
            ) && intent.hasExtra(
                "comment"
            )
        ) {
            id = intent.getStringExtra("id")
            item = intent.getStringExtra("item")
            price = intent.getStringExtra("price")
            qty = intent.getStringExtra("qty")
            tax = intent.getStringExtra("tax")
            size = intent.getStringExtra("size")
            type = intent.getStringExtra("type")
            weight = intent.getStringExtra("weight")
            comment = intent.getStringExtra("comment")

            purchseItem.setText(item)
            purchasePrice.setText(price)
            purchaseQty.setText(qty)
            purchaseTax.setText(tax)
            purchaseSize.setText(size)
            purchaseType.setText(type)
            purchaseWeight.setText(weight)
            purchaseComment.setText(comment)

        } else {
            Toast.makeText(applicationContext, "No Intent data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete ${item}?")
        builder.setMessage("Are you sure you want to Delete ${item}?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            val dbHelper = MyDatabaseHelper(this@UpdateActivity)
            dbHelper.deleteOneRow(id)
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
        }
        builder.create().show()

    }
}
