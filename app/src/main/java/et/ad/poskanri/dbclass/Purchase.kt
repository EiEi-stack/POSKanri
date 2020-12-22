package et.ad.poskanri.dbclass

class Purchase {
    var purchaseId = -1
    var itemName: String = ""
    var purchasePrice = 0
    var itemQty = 0
    var tax = 0
    var size :String= ""
    var itemType :String= ""
    var itemWeight:String = ""
    var comment :String= ""
    var image = ByteArray(8192)
}