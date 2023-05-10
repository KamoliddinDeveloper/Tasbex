package com.example.tasbex

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import com.example.tasbex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var count =0
    var checkVibr=true
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tasbeeh_Count()
}


@SuppressLint("CommitPrefEdits")
private fun tasbeeh_Count(){

    sharedPreferences = getSharedPreferences("catch_file",Context.MODE_PRIVATE)
    val  edit = sharedPreferences.edit()
    val h: String = sharedPreferences.getString("key", binding.zikrNumber.text.toString()).toString()
    binding.zikrNumber.text = h
    binding.click.setOnClickListener {
        val a =binding.zikrNumber.text.toString().toLong()+1
        edit.putString("key",a.toString())
        edit.commit()
        val h: String = sharedPreferences.getString("key", a.toString()).toString()
        binding.zikrNumber.text = h
        val vibr=(getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
        if (checkVibr){
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                vibr.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
            }else{
                vibr.vibrate(30)
            }
        }
    }
    binding.restart.setOnClickListener {
        count = 0
        binding.zikrNumber.text = count.toString()
        edit.putString("key", count.toString())
        edit.commit()
    }


}}
