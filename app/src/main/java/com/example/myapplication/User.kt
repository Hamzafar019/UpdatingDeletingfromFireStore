package com.example.myapplication

class User {
    var name:String?=null
    var password:String?=null
    var id:String?=null

    constructor(){}
    constructor(name: String?, password: String?, id: String?) {
        this.name = name
        this.password = password
        this.id = id
    }
}