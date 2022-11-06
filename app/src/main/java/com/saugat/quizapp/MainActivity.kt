package com.saugat.quizapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.saugat.quizapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private var q1_opt1 = false
    private var q1_opt2 = false
    private var q1_opt3 = false

    private var q2_opt1 = false
    private var q2_opt2 = false
    private var q2_opt3 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_option1_q1 -> {
                    q1_opt1 = true
                    q1_opt2 = false
                    q1_opt3 = false
                }

                R.id.radio_option2_q1 -> {
                    q1_opt2 = true
                    q1_opt1 = false
                    q1_opt3 = false
                }

                R.id.radio_option3_q1 -> {
                    q1_opt3 = true
                    q1_opt2 = false
                    q1_opt1 = false
                }
            }
        }

        binding.checkboxOption1Q2.setOnCheckedChangeListener { _, checked ->
            q2_opt1 = checked
        }

        binding.checkboxOption2Q2.setOnCheckedChangeListener { _, checked ->
            q2_opt2 = checked
        }

        binding.checkboxOption3Q2.setOnCheckedChangeListener { _, checked ->
            q2_opt3 = checked
        }

        binding.submitButton.setOnClickListener {
            onSubmitClicked()
        }

        binding.resetButton.setOnClickListener {
            onResetClicked()
        }
    }

    private fun onSubmitClicked() {

        var score = 0

        val current = LocalDateTime.now()

        //get date
        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = current.format(formatterDate)

        //get time
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val time = current.format(formatterTime)

        if (q1_opt2) {
            score += 50
        }
        if (q2_opt1 && q2_opt2) {
            score += 50
        }

        val messageToDisplay =
            "Congratulations! You submitted on current Date: $date and Time: $time, You achieved $score %"

        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.setMessage(messageToDisplay)!!.setTitle(title)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun onResetClicked() {

        //reset question 1 options
        binding.radioOption1Q1.isChecked = false
        binding.radioOption2Q1.isChecked = false
        binding.radioOption3Q1.isChecked = false

        //reset question 2 options
        binding.checkboxOption1Q2.isChecked = false
        binding.checkboxOption2Q2.isChecked = false
        binding.checkboxOption3Q2.isChecked = false
    }

}