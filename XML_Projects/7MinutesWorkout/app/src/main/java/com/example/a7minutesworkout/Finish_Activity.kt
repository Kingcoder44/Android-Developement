package com.example.a7minutesworkout

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class Finish_Activity : AppCompatActivity() {
    private var bindingg : ActivityFinishBinding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingg = ActivityFinishBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(bindingg?.root)
        setSupportActionBar(bindingg?.toolbarFinishActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        bindingg?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        bindingg?.btnFinish?.setOnClickListener{
            finish()
        }

        val historDAO = (application as WorkoutApp).db.histDao()
        addDateToDB(historDAO)
    }

    private fun addDateToDB(historyDAO: HistoryDAO){
        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date",""+dateTime)

        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Date",""+dateTime)


        lifecycleScope.launch {
            historyDAO.insert(HistoryEntity(date))
            Log.e("Date","Added")
        }
    }
}