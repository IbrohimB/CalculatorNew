package eu.tutorials.newcalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import eu.tutorials.newcalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }


    private fun setListeners() {
        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnThree.setOnClickListener(this)
        binding.btnFour.setOnClickListener(this)
        binding.btnFive.setOnClickListener(this)
        binding.btnSix.setOnClickListener(this)
        binding.btnSeven.setOnClickListener(this)
        binding.btnEight.setOnClickListener(this)
        binding.btnNine.setOnClickListener(this)
        binding.btnZero.setOnClickListener(this)
        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtract.setOnClickListener(this)
        binding.btnMultiply.setOnClickListener(this)
        binding.btnDivide.setOnClickListener(this)
        binding.btnDot.setOnClickListener(this)
        binding.btnEqual.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnOne -> {
                lastEntered("1")
            }
            binding.btnTwo -> {
                lastEntered("2")
            }
            binding.btnThree -> {
                lastEntered("3")
            }
            binding.btnFour -> {
                lastEntered("4")
            }
            binding.btnFive -> {
                lastEntered("5")
            }
            binding.btnSix -> {
                lastEntered("6")
            }
            binding.btnSeven -> {
                lastEntered("7")
            }
            binding.btnEight -> {
                lastEntered("8")
            }
            binding.btnNine -> {
                lastEntered("9")
            }
            binding.btnZero -> {
                lastEntered("0")
            }
            binding.btnDot -> {
                onDecimalPoint()
            }
            binding.btnClear -> {
                clearInputField()
            }
            binding.btnAdd -> {
                onOperator("+")
            }
            binding.btnSubtract -> {
                onOperator("-")
            }
            binding.btnDivide -> {
                onOperator("/")
            }
            binding.btnMultiply -> {
                onOperator("*")
            }
            binding.btnEqual -> {
                onEqual("=")
            }

        }
    }

    private fun onOperator(input: String) {
        binding.tvInput.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {

                val existingText = binding.tvInput.text.toString()
                val updatedText = existingText + input

                binding.tvInput.text = updatedText
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun lastEntered(input: String) {

        val existingText = binding.tvInput.text.toString()
        val updatedText = existingText + input
        binding.tvInput.text = updatedText
        lastNumeric = true
        lastDot = false


        //
    }

    private fun clearInputField() {
        binding.tvInput.text = ""
    }

    private fun onDecimalPoint() {
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    private fun onEqual(input: String) {
        if (lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)

        return value
    }


    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")


        }
    }


}