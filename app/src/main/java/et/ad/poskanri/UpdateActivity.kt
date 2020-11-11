package et.ad.poskanri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateActivity : AppCompatActivity() {
    lateinit var purchseItem: EditText
    lateinit var purchasePrice: EditText
    lateinit var purchaseQty: EditText
    lateinit var updateBtn: Button
    lateinit var id: String
    lateinit var item: String
    lateinit var price: String
    lateinit var qty: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        purchseItem = findViewById(R.id.et_item_name2)
        purchasePrice = findViewById(R.id.et_item_price2)
        purchaseQty = findViewById(R.id.et_item_qty2)
        updateBtn = findViewById(R.id.btn_update)

        getIntentData()
        updateBtn.setOnClickListener(View.OnClickListener {

            val dbHelper=MyDatabaseHelper(this@UpdateActivity)
            val result=dbHelper.updateData(id,item,price.toInt(),qty.toInt())
            if(result== -1){
                Toast.makeText(applicationContext,"Update fail", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Update Successfully", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun getIntentData() {
        if (intent.hasExtra("id") && intent.hasExtra("item") && intent.hasExtra("price") && intent.hasExtra(
                "qty"
            )
        ) {
            id = intent.getStringExtra("id")
            item = intent.getStringExtra("item")
            price = intent.getStringExtra("price")
            qty = intent.getStringExtra("qty")

            purchseItem.setText(item.toString())
            purchasePrice.setText(price.toString())
            purchaseQty.setText(qty.toString())

        } else {
            Toast.makeText(applicationContext, "No Intent data", Toast.LENGTH_SHORT).show()
        }
    }
}
