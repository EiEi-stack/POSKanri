package et.ad.poskanri.dbclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="customer")
class Customer {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    @ColumnInfo(name = "customerName")
    var customerName: String = ""
    @ColumnInfo(name = "phNo")
    var phNo = 0
    @ColumnInfo(name = "address")
    var address: String = ""
    @ColumnInfo(name = "itemName")
    var itemName: String = ""
    @ColumnInfo(name = "itemQty")
    var itemQty: String = ""
    @ColumnInfo(name = "comment")
    var comment: String = ""
    @ColumnInfo(name = "status")
    var status = 0
    @ColumnInfo(name = "data")
    var date = 0
}