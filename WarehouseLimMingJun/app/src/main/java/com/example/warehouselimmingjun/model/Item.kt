package com.example.warehouselimmingjun.model

class Item {
    var id:String? = null
    var name:String? = null
    var quantity:String? = null
    var category:String? = null
    var price:String? = null
    var size:String? = null
    var location:String? = null
    var image:ByteArray? = null

    var icons:Int ? = 0
    var title:String ? = null
    var detail:String ? = null

    constructor(){}

    constructor(id:String,name:String,quantity:String,category:String,price:String,size:String,location:String,image:ByteArray)
    {
        this.id = id
        this.name = name
        this.quantity = quantity
        this.category = category
        this.price = price
        this.size = size
        this.location = location
        this.image = image
    }



    constructor(icons: Int?, title: String?, detail: String?) {
        this.icons = icons
        this.title = title
        this.detail = detail
    }
}