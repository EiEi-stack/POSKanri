package et.ad.poskanri

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import et.ad.poskanri.dbclass.PurchaseEntity

class CustomAdapter(
    private val context: Context,
    private val purchase: MutableList<PurchaseEntity>
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    private lateinit var translateAnim: Animation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return purchase.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.MyViewHolder, position: Int) {
        holder.purchaseId.text = purchase[position].purchaseId.toString()
        holder.itemName.text = purchase[position].itemName
        holder.itemPrice.text = purchase[position].purchasePrice.toString() + "￥"
        holder.itemQty.text = purchase[position].itemQty.toString()

        holder.mainLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("id", purchase[position].purchaseId.toString())
            intent.putExtra("item", purchase[position].itemName)
            intent.putExtra("price", purchase[position].purchasePrice.toString())
            intent.putExtra("qty", purchase[position].itemQty.toString())
            intent.putExtra("tax", purchase[position].tax.toString())
            intent.putExtra("size", purchase[position].size.toString())
            intent.putExtra("type", purchase[position].itemType.toString())
            intent.putExtra("weight", purchase[position].itemWeight.toString())
            intent.putExtra("comment", purchase[position].comment.toString())
            intent.putExtra("image", purchase[position].image.toString())
            context.startActivity(intent)
        })
        translateAnim = AnimationUtils.loadAnimation(context, R.anim.translate_anim)
        holder.mainLayout.animation = translateAnim
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var purchaseId: TextView = itemView.findViewById(R.id.txt_purchase_id)
        var itemName: TextView = itemView.findViewById(R.id.txt_item)
        var itemPrice: TextView = itemView.findViewById(R.id.txt_price)
        var itemQty: TextView = itemView.findViewById(R.id.txt_qty)
        var mainLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.mainLayout)

    }
}