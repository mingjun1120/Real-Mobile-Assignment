package com.example.warehouselimmingjun.model

class Login {
    var email:String? = null
    var pass:String? = null

    constructor(){}

    constructor(email:String,pass:String)
    {
        this.email = email
        this.pass = pass
    }
}