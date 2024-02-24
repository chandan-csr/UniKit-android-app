package com.example.unikit

interface UpdateAndDelete {

    fun modifyItem(itemUID :String,isDone :Boolean)
    fun onItemDelete(itemUID: String)
}