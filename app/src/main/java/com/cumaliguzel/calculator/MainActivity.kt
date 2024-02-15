package com.cumaliguzel.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.cumaliguzel.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstnumber = ""
    private var currentNumber = ""
    private var currentOperator = ""
    private var result = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //nolimit
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        //initVies
        binding.apply {
            //get all buttons
            binding.layoutMain.children.filterIsInstance<Button>().forEach { button ->
                //butonClickListenir
                button.setOnClickListener {
                    val buttonText = button.text.toString()
                    when {
                        buttonText.matches(Regex("[0-9]")) -> {
                            if (currentOperator.isEmpty()) {
                                firstnumber += buttonText
                                tvResult.text = firstnumber
                            } else {
                                currentNumber += buttonText
                                tvResult.text = currentNumber
                            }

                        }
                        buttonText.matches(Regex("[+\\-*/]"))->{
                            currentNumber = ""
                            if (tvResult.text.toString().isNotEmpty())
                            {
                                currentOperator = buttonText
                                tvResult.text = "0"
                            }
                        }
                        buttonText == "="->{
                            if (currentNumber.isNotEmpty()&& currentOperator.isNotEmpty())
                            {
                                tvFormula.text = "$firstnumber$currentOperator$currentNumber"
                                result = evaluateExpression(firstnumber,currentNumber,currentOperator)
                                firstnumber = result
                                tvResult.text = result
                            }
                        }
                        buttonText == "."->{
                            if(currentOperator.isEmpty())
                            {
                                if (! firstnumber.contains("."))
                                {
                                    if(firstnumber.isEmpty())firstnumber+="0$buttonText"
                                    else firstnumber +=buttonText
                                    tvResult.text = firstnumber
                                }
                            }else
                            {
                                if (! currentNumber.contains("."))
                                {
                                    if(currentNumber.isEmpty()) currentNumber+="0$buttonText"
                                    else currentNumber +=buttonText
                                    tvResult.text = currentNumber
                                }
                            }
                        }
                        buttonText == "C"->{
                            currentNumber = ""
                            firstnumber = ""
                            currentOperator = ""
                            tvResult.text = "0"
                            tvFormula.text = ""
                        }
                    }
                }
            }


        }
    }

    //functions
    private fun evaluateExpression(firstNumber:String,secondNumber:String,operator:String):String
    {
        val num1  = firstNumber.toDouble()
        val num2  = secondNumber.toDouble()
        return when(operator)
        {
            "+"-> (num1+num2).toString()
            "-"-> (num1-num2).toString()
            "*"-> (num1*num2).toString()
            "/"-> (num1/num2).toString()
            else ->""
                    }


                }
            }





