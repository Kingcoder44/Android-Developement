package com.example.room_entitytest

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room_entitytest.db.Note
import com.example.room_entitytest.db.NoteDataBase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), Notes_Adapter.ClickListener {
    private lateinit var repo: Repo
    private lateinit var notesVM: NotesViewModel
    private lateinit var notesVMFactory: VMFactory
    private lateinit var notesDB: NoteDataBase
    private lateinit var adap: Notes_Adapter
    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var dial: Dialog
    private lateinit var edt_text: EditText
    private lateinit var edt_cntnt: EditText
    private lateinit var save_btn: Button

    // Change 1: Added currentNote variable to store the note being updated
    private var currentNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
        notesVM.getAllNotes().observe(this) {
            adap = Notes_Adapter(it, this)
            rv.adapter = adap
            rv.layoutManager = LinearLayoutManager(this)
        }

        // Change 2: Reset currentNote when FAB is clicked for adding a new note
        fab.setOnClickListener {
            currentNote = null
            openDialog(comingFromFAB = true)
        }
    }

    private fun openDialog(comingFromFAB: Boolean) {
        dial = Dialog(this)
        dial.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dial.setCancelable(true)
        dial.setContentView(R.layout.layout_dialog)

        edt_text = dial.findViewById(R.id.edt_notename)
        edt_cntnt = dial.findViewById(R.id.edt_notecntnt)
        save_btn = dial.findViewById(R.id.btn_save)

        // Change 3: Set the fields if updating an existing note
        if (!comingFromFAB && currentNote != null) {
            edt_text.setText(currentNote!!.notename)
            edt_cntnt.setText(currentNote!!.notecont)
        } else {
            edt_text.text.clear()
            edt_cntnt.text.clear()
        }

        save_btn.setOnClickListener {
            val note = Note(
                edt_text.text.toString(),
                edt_cntnt.text.toString()
            )
            if (!comingFromFAB) {
                // Change 4: Ensure the ID is preserved for updating
                note.noteid = currentNote!!.noteid
            }
            if (comingFromFAB) {
                notesVM.insert(note)
            } else {
                notesVM.update(note)
            }
            dial.dismiss()
        }
        dial.show()
    }

    private fun init() {
        notesDB = NoteDataBase(this)
        repo = Repo(notesDB.getNoteDao())
        notesVMFactory = VMFactory(repo)
        notesVM = ViewModelProvider(this, notesVMFactory).get(NotesViewModel::class.java)
        rv = findViewById(R.id.rv)
        fab = findViewById(R.id.fab)
    }

    // Change 5: Set currentNote before opening the dialog for updating
    override fun updateNote(note: Note) {
        currentNote = note
        openDialog(comingFromFAB = false)
    }

    override fun deleteNote(note: Note) {
        notesVM.delete(note)
    }
}
