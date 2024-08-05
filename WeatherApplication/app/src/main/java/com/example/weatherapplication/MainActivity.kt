package com.example.weatherapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var repo: Repo
    private lateinit var vmf : WeatherVMF
    private lateinit var vm : WetherVM
    private lateinit var loader : ProgressBar
    private lateinit var cityName : EditText
    private lateinit var btn : Button
    private lateinit var img : ImageView
    private lateinit var txt : TextView
    private lateinit var text_city : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        init()
        btn.setOnClickListener {
            vm.getWeatherDetail(cityName.text.toString())
        }
        vm.weatherLiveData.observe(this){
            val currWe = it.current.condition.text
            val currTemp = it.current.temp_c

            txt.text = "$currWe,$currTemp"

            val img_link = "https://${it.current.condition.icon}"
            Glide.with(this).load(img_link)
                .into(img)

            val cityN = it.location.name
            val state= it.location.region

            val cityandstate = "$cityN,$state"
            text_city.text = cityandstate
        }
        vm.loading. observe(this)
        {
            if(it)
                loader.visibility = View.VISIBLE
            else
                loader.visibility = View.GONE
        }
    }
    private fun init()
    {
        repo = Repo(RetrofitBuilder.getInstance())
        vmf = WeatherVMF(repo)
        vm = ViewModelProvider(this,vmf).get(WetherVM::class.java)


     loader = findViewById(R.id.loader)
        cityName=findViewById(R.id.city)
        btn=findViewById(R.id.weather)
        img=findViewById(R.id.img)
        txt=findViewById(R.id.txt_Wthr)
        text_city=findViewById(R.id.city_state)
     }

}