package com.example.warehouselimmingjun.model

class HistoryList {
    var icons:Int ? = 0
    var title:String ? = null
    var detail:String ? = null
    var stockIn:String ? = null
    var stockInQty: String ? = null
    var stockOut:String ? = null
    var stockOutQty: String ? = null
    var dateTime:String ? = null


    constructor(icons: Int?, title: String?, detail: String?,  stockIn: String?, stockInQty: String?, stockOut: String?, stockOutQty: String?, dateTime: String?) {
        this.icons = icons
        this.title = title
        this.detail = detail
        this.stockIn = stockIn
        this.stockInQty = stockInQty
        this.stockOut = stockOut
        this.stockOutQty = stockOutQty
        this.dateTime = dateTime
    }
}