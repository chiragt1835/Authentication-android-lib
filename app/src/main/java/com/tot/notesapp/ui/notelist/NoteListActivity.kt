package com.tot.notesapp.ui.notelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.tot.authenticationmodule.data.local.UserData
import com.tot.authenticationmodule.databinding.ActivityLoginBinding
import com.tot.authenticationmodule.utils.Constant
import com.tot.notesapp.R
import com.tot.notesapp.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
//        val userData = Gson().fromJson(intent.getStringExtra(Constant.USER_DATA),UserData::class.java)

    }

}