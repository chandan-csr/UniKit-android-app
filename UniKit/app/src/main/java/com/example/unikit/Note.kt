package com.example.unikit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class Note : AppCompatActivity(),UpdateAndDelete {
    lateinit var database: DatabaseReference
    var toDoList:MutableList<NoteModel>?=null
    lateinit var adapter: NoteAdapter
    private var listViewItem: ListView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        val fab=findViewById<View>(R.id.fab) as FloatingActionButton

        listViewItem=findViewById<ListView>(R.id.item_listview)
        database = FirebaseDatabase.getInstance().reference
        fab.setOnClickListener {view->
            val alertDialog= AlertDialog.Builder(this)
            val  textEditText= EditText(this)
            alertDialog.setMessage("Add Note")
            alertDialog.setTitle("Enter note")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("Save") { dialog, i ->
                val todoItemData = NoteModel.createList()
                todoItemData.itemDataText = textEditText.text.toString()
                todoItemData.done = false
                val newitemData=database.child("note").push()
                todoItemData.done=false
                todoItemData.UID=newitemData.key
                newitemData.setValue(todoItemData)

                dialog.dismiss()
                Toast.makeText(this,"item Saved",Toast.LENGTH_LONG).show()

            }
            alertDialog.show()

        }
        toDoList=mutableListOf<NoteModel>()
        adapter= NoteAdapter(this,toDoList!!)
        listViewItem!!.adapter=adapter
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "No item Added", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                toDoList!!.clear()
                addItemTodoList(snapshot)
            }
        })
    }

    private fun addItemTodoList(snapshot: DataSnapshot) {
        val items =snapshot.children.iterator()
        if(items.hasNext()){
            val toDoIndexvalue=items.next()
            val itemsIterator=toDoIndexvalue.children.iterator()
            while(itemsIterator.hasNext()){
                val currentItem=itemsIterator.next()
                val toDoItemData=NoteModel.createList()
                val map=currentItem.getValue() as HashMap<String,Any>
                toDoItemData.UID=currentItem.key
                toDoItemData.done=map.get("done") as Boolean?
                toDoItemData.itemDataText=map.get("itemDataText") as String?
                toDoList!!.add(toDoItemData)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun modifyItem(itemUID: String, isDone: Boolean) {
        val itemReference=database.child("note").child(itemUID)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUID: String) {
        val itemReference = database.child("note").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }

    fun onBack(v: View?){
        var i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}