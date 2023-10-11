package com.tot.notesapp.ui.notelist

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.tot.authenticationmodule.data.local.UserData
import com.tot.notesapp.NotesApp
import com.tot.notesapp.R
import com.tot.notesapp.adapter.NoteListAdapter
import com.tot.notesapp.data.local.NotesData
import com.tot.notesapp.databinding.ActivityNoteListBinding
import com.tot.notesapp.databinding.DialogThemeBinding
import com.tot.notesapp.ui.addnote.AddNoteActivity
import com.tot.notesapp.ui.main.MainActivity
import com.tot.notesapp.utils.Constant
import com.tot.notesapp.utils.EqualGapItemDecoration
import com.tot.notesapp.utils.SharedPreference
import com.tot.notesapp.utils.Utils

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    private val viewModel: NoteListViewModel by viewModels { NoteListModelFactory((application as NotesApp).notesRepository) }
    private lateinit var sharedPreference: SharedPreference
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = SharedPreference(this)
        init()
    }

    private fun init() {
        val userData =
            Gson().fromJson(sharedPreference.getString(Constant.USER_DATA), UserData::class.java)
        binding.txtUserName.text = "Welcome ${userData.firstName},"
        setAdapter()
        initObservers()
        binding.imgLogout.setOnClickListener {
            sharedPreference.clearAllPref()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constant.LOGOUT_ACTION, Constant.LOGOUT)
            startActivity(intent)
            finish()
        }
        binding.imgAddNotes.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra(Constant.NOTE_TYPE, Constant.ADD_NOTE)
            startActivity(intent)
        }

        binding.imgTheme.setOnClickListener {
            showThemeDialog()

        }

        viewModel.getNoteList()
        registerReceiver(broadcastReceiver, IntentFilter(Constant.LIST_ACTION))
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            viewModel.getNoteList()
        }
    }

    private fun initObservers() {
        viewModel.notesList.observe(this) { it ->
            if (it.isNullOrEmpty().not()) {
                val list = it.filter {
                    it.emailId == sharedPreference.getString(Constant.EMAIL_ID)
                } as ArrayList<NotesData>
                if (list.isEmpty()) {
                    manageEmptyView(true)
                    noteListAdapter.clear()
                } else {
                    manageEmptyView(false)
                    noteListAdapter.addAddData(list)
                }

            } else {
                manageEmptyView(true)
                noteListAdapter.clear()
            }
        }
    }

    private fun manageEmptyView(isVisible: Boolean) {
        binding.txtEmptyView.isVisible = isVisible
        binding.animationView.isVisible = isVisible
    }

    private fun setAdapter() {
        noteListAdapter = NoteListAdapter(this)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvNotes.layoutManager = staggeredGridLayoutManager
        binding.rvNotes.adapter = noteListAdapter
        binding.rvNotes.addItemDecoration(
            EqualGapItemDecoration(
                2, resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp)
            )
        )
    }

    private fun showThemeDialog() {
        val dialog = Dialog(this)
        val binding = DialogThemeBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        val windowManagerLayoutParams = WindowManager.LayoutParams()
        val window = dialog.window
        window?.let {
            windowManagerLayoutParams.copyFrom(window.attributes)
            window.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        this, R.color.blurBackground
                    )
                )
            )
            windowManagerLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            windowManagerLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = windowManagerLayoutParams
        }

        binding.btnDark.setOnClickListener {
            sharedPreference.setString(Constant.THEME_TYPE, Constant.DARK_THEME)
            Utils.setNightMode(sharedPreference, this)
            dialog.dismiss()
        }

        binding.btnLight.setOnClickListener {
            sharedPreference.setString(Constant.THEME_TYPE, Constant.LIGHT_THEME)
            Utils.setNightMode(sharedPreference, this)
            dialog.dismiss()
        }

        binding.btnSystem.setOnClickListener {
            sharedPreference.setString(Constant.THEME_TYPE, Constant.SYSTEM_DEFAULT)
            Utils.setNightMode(sharedPreference, this)
            dialog.dismiss()

        }

        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

}