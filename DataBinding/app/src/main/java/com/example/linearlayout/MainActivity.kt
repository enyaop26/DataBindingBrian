package com.example.linearlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import com.example.linearlayout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //->*******************************************
    private lateinit var binding:ActivityMainBinding
    data class MyName(var name: String="Angel Eduardo Curintzita", var nickname:String="Pandaman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.doneButton.setOnClickListener{
            addNickname(it)

        }
        findViewById<TextView>(R.id.nickname_text).setOnClickListener({
            updateNickname(it)
        })
    }
    private fun addNickname(view: View){
        val editText=findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)
        binding.apply {
            binding.nicknameText.text=binding.nicknameEdit.text
            binding.nicknameEdit.visibility=View.GONE
            binding.doneButton.visibility=View.GONE
            binding.nicknameText.visibility=View.VISIBLE
            binding.nicknameText.text=binding.nicknameEdit.text.toString()
            myName?.nickname=nicknameEdit.text.toString()
            invalidateAll()
        }
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View){
        val editText= findViewById<EditText>(R.id.nickname_edit)
        val doneButton= findViewById<Button>(R.id.done_button)
        editText.visibility=View.VISIBLE
        doneButton.visibility=View.VISIBLE
        view.visibility=View.GONE
        // Set the focus to the edit text
        editText.requestFocus()
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText,0)

    }

}