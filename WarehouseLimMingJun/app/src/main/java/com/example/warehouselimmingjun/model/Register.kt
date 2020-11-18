package com.example.warehouselimmingjun.model

class Register {
    var email:String? = null
    var pass:String? = null
    var name:String? = null

    constructor(){}

    constructor(email:String,pass:String,name:String)
    {
        this.email = email
        this.pass = pass
        this.name = name
    }
}