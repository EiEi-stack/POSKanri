package et.ad.poskanri

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(  private val context: Context,
                      private val purchase_id: ArrayList<String>,
                      private val purchase_item: ArrayList<String>,
                      private val purchase_qty: ArrayList<String>,
                      private val purchase_price: ArrayList<String>
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)


    }

    override fun getItemCount(): Int {
        return purchase_id.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.MyViewHolder, position: Int) {
        holder.purchaseId.text = purchase_id[position]
        holder.itemName.text = purchase_item[position]
        holder.itemPrice.text = purchase_qty[position]
        holder.itemQty.text = purchase_price[position]

        holder.mainLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("id", purchase_id[position].toString())
            intent.putExtra("item", purchase_item[position].toString())
            intent.putExtra("price", purchase_qty[position].toString())
            intent.putExtra("qty", purchase_price[position].toString())
            context.startActivity(intent)
        })
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var purchaseId: TextView = itemView.findViewById(R.id.txt_purchase_id)
        var itemName: TextView = itemView.findViewById(R.id.txt_item)
        var itemPrice: TextView = itemView.findViewById(R.id.txt_price)
        var itemQty: TextView = itemView.findViewById(R.id.txt_qty)
        var mainLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.mainLayout)
    }
}