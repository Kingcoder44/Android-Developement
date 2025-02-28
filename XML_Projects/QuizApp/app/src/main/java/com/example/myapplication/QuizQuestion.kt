package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Result

class QuizQuestion : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    private var user_name :String?=null
    private var mcorrect : Int = 0
    private var myCurrPos :Int =1
    private var myQuesList : ArrayList<question>? =null
    private var selectedpos : Int = 0
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion :TextView?=null
    private var tvImage : ImageView?=null
    private var a1 : TextView?=null
    private var a2 : TextView?=null
    private var a3 : TextView?=null
    private var a4  : TextView?=null
    private var btn_submit : Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_question)
        user_name = intent.getStringExtra(Constants.USER_NAME)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        tvImage = findViewById(R.id.iv_image)
        a1 = findViewById(R.id.opt_1)
        a2 = findViewById(R.id.opt_2)
        a3 = findViewById(R.id.opt_3)
        a4 = findViewById(R.id.opt_4)
        btn_submit = findViewById(R.id.btn_submit)
        a1?.setOnClickListener(this)
        a2?.setOnClickListener(this)
        a3?.setOnClickListener(this)
        a4?.setOnClickListener(this)
        btn_submit?.setOnClickListener(this)
        myQuesList = Constants.getQuestions()
        setQuestion()
    }

    private fun setQuestion() {
        default_view()
        //for(i in questionList)
        val ques: question = myQuesList!![myCurrPos-1]
        tvImage?.setImageResource(ques.image)
        progressBar?.progress = myCurrPos
        tvProgress?.text = "${myCurrPos }/ ${progressBar?.max}"
        tvQuestion?.text = ques.questions
        a1?.text = ques.option1
        a2?.text = ques.option2
        a3?.text = ques.option3
        a4?.text = ques.option4

        if(myCurrPos==myQuesList!!.size){
            btn_submit?.text="FINISH"
        }
        else
            btn_submit?.text="SUBMIT"
    }
private fun default_view(){
    val option = ArrayList<TextView>()
    a1?.let{
        option.add(0,it)
    }
    a2?.let{
        option.add(1,it)
    }
    a3?.let{
        option.add(2,it)
    }
    a4?.let{
        option.add(3,it)
    }

    for(i in option){
        i.setTextColor(Color.parseColor("#7A8089"))
        i.typeface = Typeface.DEFAULT
        i.background = ContextCompat.getDrawable(this,R.drawable.default_option_border)
    }
}
    private fun selectedOptionView(tv:TextView , selectedOptionNum : Int){
        default_view()
        selectedpos = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background =  ContextCompat.getDrawable(this,R.drawable.selected_option_bg)
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.opt_1 ->{
                a1?.let{
                    selectedOptionView(it,1)
                }
            }
            R.id.opt_2 ->{
                a2?.let{
                    selectedOptionView(it,2)
                }
            }
            R.id.opt_3 ->{
                a3?.let{
                    selectedOptionView(it,3)
                }
            }
            R.id.opt_4 ->{
                a4?.let{
                    selectedOptionView(it,4)
                }
            }
            R.id.btn_submit->{
                //submit button
                if(selectedpos==0){
                   myCurrPos++

                    when{
                        myCurrPos <= myQuesList!!.size ->{
                            setQuestion()
                        }
                        else->{
                          //  Toast.makeText(this,"You made it to the End",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,Result::class.java)
                            intent.putExtra(Constants.USER_NAME,user_name)
                            intent.putExtra(Constants.CORCT_ANS,mcorrect)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,myQuesList?.size)
                            startActivity(intent)
                                finish()

                        }
                    }
                }
                else{
                    val question =  myQuesList?.get(myCurrPos-1)
                    if(question!!.crct_ans != selectedpos) {
                        answerView(selectedpos, R.drawable.wrong_option_border)
                    }else
                    {
                        mcorrect++
                    }
                    answerView(question!!.crct_ans,R.drawable.correct_option_border)
                }
                if(myCurrPos == myQuesList!!.size)
                    btn_submit?.text= "FINISH"
                else
                    btn_submit?.text="GO TO NEXT QUESTION"
                selectedpos=0
            }
        }
    }

    private fun answerView(answer :Int, drawableView : Int){
        when(answer){
            1->{
                a1?.background =  ContextCompat.getDrawable(
                    this,
                    drawableView)
            }
            2->{
                a2?.background =  ContextCompat.getDrawable(
                    this@QuizQuestion,
                    drawableView)
            }
            3->{
                a3?.background =  ContextCompat.getDrawable(
                    this@QuizQuestion,
                    drawableView)
            }
            4->{
                a4?.background =  ContextCompat.getDrawable(
                    this@QuizQuestion,
                    drawableView)
            }
        }
    }
}
