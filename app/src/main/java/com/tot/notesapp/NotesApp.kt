package com.tot.notesapp

import android.app.UiAutomation
import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.tot.authenticationmodule.AuthenticationApp
import com.tot.notesapp.db.NotesDatabase
import com.tot.notesapp.db.NotesRepository
import com.tot.notesapp.utils.Constant
import com.tot.notesapp.utils.SharedPreference
import com.tot.notesapp.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApp : AuthenticationApp() {


    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { NotesDatabase.getDatabase(this, applicationScope) }
    val notesRepository by lazy { NotesRepository(database.notesDao()) }
    private lateinit var sharedPreference: SharedPreference


    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreference = SharedPreference(this)
        Utils.setNightMode(sharedPreference, this)
    }

    companion object {
        var instance: NotesApp? = null
    }

}