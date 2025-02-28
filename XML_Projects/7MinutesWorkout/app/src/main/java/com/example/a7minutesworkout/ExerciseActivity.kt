package com.example.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.renderscript.ScriptGroup.Binding
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.DialogBackCustomBinding
import org.w3c.dom.Text
import java.util.Locale


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExerciseBinding? =null
    private var restTimer: CountDownTimer?=null
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer?=null
    private var exerciseProgress = 0
    private var exerciseList : ArrayList<ExerciseModel>? =null
    private var currExercisePostion = -1
    private var tts : TextToSpeech? = null
    private var player : MediaPlayer? =null //to play an audio or sound
    private var exerciseAdapter : ExerciseStatusAdapter? = null
    private var restimerDuraion : Long  = 1
    private var exercisetimerDuraion : Long  = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //we will need toolbar to get a action bar ontop
        setSupportActionBar(binding?.toolbrExercise)
        //to send back to previous screen
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbrExercise?.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        //setting up text to speech
        tts = TextToSpeech(this,this)
        exerciseList = Constants.defaultExercise()

        restView()
        setupExerciseRecyclerView()
    }
    private fun setupExerciseRecyclerView(){
        binding?.rvStatus?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
            binding?.rvStatus?.adapter = exerciseAdapter
    }

    private fun restView(){
        player?.release()
        player = null

        try{
            val sounduri = Uri.parse("android.resource://com.example.a7minutesworkout/${R.raw.bell}")
            player = MediaPlayer.create(applicationContext, sounduri)
            player?.isLooping = false
            player?.start()
        }
        catch (e : Exception){
            e.printStackTrace()
        }

        binding?.progressBar?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.exerciseImage?.visibility = View.INVISIBLE
        binding?.upcomingExer?.visibility = View.VISIBLE

        if(restTimer!=null) {
            restTimer?.cancel()
            restProgress =0
        }
        binding?.upcomingExer?.text = "Upcoming exercise : ${exerciseList!![currExercisePostion+1].getName()}"
        setrestProgressBar()
    }

    private fun exerciseView(){
        binding?.progressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.exerciseImage?.visibility = View.VISIBLE
        binding?.upcomingExer?.visibility = View.INVISIBLE

        if(exerciseTimer!=null) {
            exerciseTimer?.cancel()
            exerciseProgress =0
        }

        speakOut(exerciseList!![currExercisePostion].getName())

        binding?.exerciseImage?.setImageResource(exerciseList!![currExercisePostion].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currExercisePostion].getName()
        setExerciseProgressBar()
    }

    private fun setrestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(restimerDuraion*1000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                currExercisePostion++
                exerciseList!![currExercisePostion].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged() //we inform adapter that data has changed
                exerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

       exerciseTimer = object : CountDownTimer(exercisetimerDuraion*1000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                 //we inform adapter that data has changed
             if(currExercisePostion<exerciseList?.size!!-1)
             {
                 exerciseList!![currExercisePostion].setIsCompleted(true)
                 exerciseList!![currExercisePostion].setIsSelected(false)
                 exerciseAdapter!!.notifyDataSetChanged()
                 restView()
             }
                else{
                    finish()
                 val intent = Intent(this@ExerciseActivity,Finish_Activity::class.java)
                 startActivity(intent)
             }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        //for restt timer
        if(restTimer!=null) {
            restTimer?.cancel()
            restProgress =0
        }

            // for exercise timer
        if(exerciseTimer!=null)
        {
            exerciseTimer?.cancel()
            exerciseProgress =0
        }

        //for sstopping the speak tts
        if(tts !=null)
        {
            tts?.stop()
            tts?.shutdown()
        }
        if(player!=null)
            player!!.stop()
        binding = null
    }
    //method to speak
    private fun speakOut(text : String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.ENGLISH)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.e("TTS","Language not supported")
            else
                Log.e("TTS","Initialization failed")
        }
    }
    @Deprecated("")
    override fun onBackPressed() {
        customDialogForBackButton()
        super. onBackPressedDispatcher.onBackPressed()
    }
    //custom back bpress button
    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)
        val dialogBinding = DialogBackCustomBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.tvYes.setOnClickListener{
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.tvNo.setOnClickListener{
            customDialog.dismiss()
        }
        customDialog.show()
    }


}