package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyconverter.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.cos
import kotlin.text.Typography.times

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertButton.setOnClickListener{
            CalculateMoney()
        }
    }

    fun CalculateMoney(){

        val inputMoney = binding.moneyAmount.text.toString()
        val cost = inputMoney.toDoubleOrNull()

        if (cost == null){
            binding.result.text = "Amount: 0.00"
            return
        }

        val currencyType = when(binding.radioGroup.checkedRadioButtonId){
            R.id.usd -> 0.00047
            R.id.euro -> 0.00044
            R.id.pound -> 0.00039
            R.id.baht -> 0.016
            else -> 0.064
        }

        val currencySign = when(binding.radioGroup.checkedRadioButtonId){
            R.id.usd -> "$"
            R.id.euro -> "€"
            R.id.pound -> "£"
            R.id.baht -> "฿"
            else -> "¥"
        }

        var result = cost * currencyType

        if (binding.roundUp.isChecked){
            result = kotlin.math.ceil(result)
        }

        binding.result.text = getString(R.string.final_result, currencySign, result)
    }

    
}