package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,INotesRVAdapter{

    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        recycler.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
        getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->list?.let {


            adapter.updateList(it)
        }
        })
    }
    fun submitdata(v:View){
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()) {
            viewModel.insertNote(Notes(noteText))
        }

    }
override fun onItemClicked(notes: Notes){
  viewModel.delNote(notes)
    }
}