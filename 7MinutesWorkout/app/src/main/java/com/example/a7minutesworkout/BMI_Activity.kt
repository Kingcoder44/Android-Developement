package com.example.a7minutesworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMI_Activity : AppCompatActivity() {
    companion object{
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW ="US_UNIT_VIEW"
    }
    private var binding : ActivityBmiBinding? =null
    private var currentVisibleView : String = METRIC_UNIT_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmiActivity)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        makeVisibleMetricUnitView()
        binding?.rgUnits?.setOnCheckedChangeListener{_,checkedId : Int->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitView()
            }
            else{
                makeUsUnitsVisible()
            }
        }
        binding?.btnCalc?.setOnClickListener{
          CalulateUnit()
        }

    }
    private fun makeVisibleMetricUnitView(){
        currentVisibleView = METRIC_UNIT_VIEW
        binding?.wtTi?.visibility = View.VISIBLE
        binding?.tiHt?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.GONE


        binding?.wtEdt?.text!!.clear()
        binding?.edtHt?.text!!.clear()

        binding?.displayBMIResult?.visibility=View.INVISIBLE
    }
    private fun makeUsUnitsVisible(){
        currentVisibleView = US_UNIT_VIEW
        binding?.wtTi?.visibility = View.GONE
        binding?.tiHt?.visibility = View.GONE
        binding?.tilUsMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.VISIBLE


        binding?.etUsMetricUnitWeight?.text!!.clear()
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()

        binding?.displayBMIResult?.visibility=View.INVISIBLE
    }
    private fun display(bmi : Float){
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
           val bmiLabel :String
           val bmiDesc : String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Severely Underweight"
            bmiDesc = "Oops! Eat a lot and take care of yourself."
        } else if (bmi.compareTo(16f) <= 0) {
            bmiLabel = "Underweight"
            bmiDesc = "You should gain some healthy weight."
        } else if (bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Slightly Underweight"
            bmiDesc = "You're close to normal weight. Eat well!"
        } else if (bmi.compareTo(24.9f) <= 0) {
            bmiLabel = "Normal Weight"
            bmiDesc = "Great! Keep maintaining a healthy lifestyle."
        } else if (bmi.compareTo(29.9f) <= 0) {
            bmiLabel = "Overweight"
            bmiDesc = "You may want to exercise and watch your diet."
        } else if (bmi.compareTo(34.9f) <= 0) {
            bmiLabel = "Obese Class I (Moderate)"
            bmiDesc = "Consider a healthier diet and more exercise."
        } else if (bmi.compareTo(39.9f) <= 0) {
            bmiLabel = "Obese Class II (Severe)"
            bmiDesc = "Health risks increase at this stage. Consult a doctor."
        } else {
            bmiLabel = "Obese Class III (Very Severe)"
            bmiDesc = "High health risk! Seek medical advice immediately."
        }

        binding?.displayBMIResult?.visibility = View.VISIBLE
        binding?.your?.text = bmiValue
        binding?.bmiType?.text = bmiLabel
        binding?.bmiDes?.text = bmiDesc

    }
    private fun validMetricUnits() : Boolean{
        var isValid = true
        if(binding?.wtEdt?.text.toString().isEmpty())
            isValid = false
        else if(
            binding?.edtHt?.text.toString().isEmpty()){
            isValid=false

        }
        return isValid
    }
    private fun validUSMetricUnits() : Boolean{
       var isValid = true
        when{
            binding?.etUsMetricUnitWeight?.text.toString().isEmpty()->{
                isValid=false
            }
            binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()->{
                isValid=false
            }
            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()->{
                isValid=false
            }
        }
        return isValid
    }
    private fun CalulateUnit(){
        if(currentVisibleView== METRIC_UNIT_VIEW){
            if(validMetricUnits())
            {
                val weightValue : Float = binding?.wtEdt?.text.toString().toFloat()

                val heightValue : Float = binding?.edtHt?.text.toString().toFloat() / 100
                val bmi = weightValue/(heightValue*heightValue)
                display(bmi)
            }
            else{
                Toast.makeText(this,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            if (validUSMetricUnits())
            {
                val usUnitHeightFeet : String =
                    binding?.etUsMetricUnitHeightFeet?.text.toString()
                val usUnitHeightInch : String =
                    binding?.etUsMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue : Float =
                    binding?.etUsMetricUnitWeight?.text.toString().toFloat()

                val heightValue = usUnitHeightFeet.toFloat()*12 +usUnitHeightInch.toFloat()
                val bmi = 703 * (usUnitWeightValue/(heightValue*heightValue))

                display(bmi)
            }
            else{

                Toast.makeText(this,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }
    }
}