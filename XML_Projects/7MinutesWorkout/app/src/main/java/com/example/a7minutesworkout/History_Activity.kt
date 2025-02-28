package com.example.a7minutesworkout

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch
import kotlin.math.log

class History_Activity : AppCompatActivity() {

    private var binding : ActivityHistoryBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistActivity)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }
        binding?.toolbarHistActivity?.setNavigationOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        val dao = (application as WorkoutApp).db.histDao()
        getAllCompleteDates(dao)

    }

    private fun getAllCompleteDates(historyDAO: HistoryDAO){
        lifecycleScope.launch {
            historyDAO.fetchAlldates().collect{
                getAllCompleteDateList->
                if(getAllCompleteDateList.isNotEmpty())
                {
                    binding?.tvHist?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoData?.visibility = View.INVISIBLE

                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@History_Activity)

                    val dates = ArrayList<String>()
                    for(date in getAllCompleteDateList){
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(dates)
                    binding?.rvHistory?.adapter = historyAdapter
                }
                else{
                    binding?.tvHist?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoData?.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
// reset the binding to null to avoid memory leak
        binding = null
    }
}