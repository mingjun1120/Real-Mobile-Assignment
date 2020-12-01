package com.example.warehouselimmingjun.model

import java.util.*

class History {
    var HistoryId:Int? = null
    var HistoryDate:String? = null
    var ProductId:String? = null
    var ProductName:String? = null
    var StockIn:String? = null
    var StockOut:String? = null
    var Image:ByteArray? = null
    var UserName: String? = null
    var email: String? = null

    constructor(){}

    constructor(HistoryId:Int,HistoryDate:String,ProductId:String,ProductName:String,StockIn:String,StockOut:String,image:ByteArray, userName:String, email:String)
    {
        this.HistoryId = HistoryId
        this.HistoryDate = HistoryDate
        this.ProductId = ProductId
        this.ProductName = ProductName
        this.StockIn = StockIn
        this.StockOut = StockOut
        this.Image = image
        this.UserName = userName
        this.email = email
    }
}