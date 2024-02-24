package com.example.unikit

class NoteModel {
    companion object Factory{
        fun createList():NoteModel= NoteModel()
    }
    var UID:String?=null
    var itemDataText:String?=null
    var done:Boolean?=false
}