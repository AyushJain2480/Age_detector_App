package com.example.ageinminutes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

//TODO: ? allows us to put a null value


class MainActivity : AppCompatActivity() {

  private lateinit var binding : com.example.ageinminutes.databinding.ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.selectedDateBtn.setOnClickListener { view ->
            clickDatePicker(view)
        }
        binding.button2.setOnClickListener {  view ->
            calculateAge(view)
        }
    }

    private fun clickDatePicker(view: View){

        var c = Calendar.getInstance()

     DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ datePicker, yy,mm,dd ->

         var dt = "$dd/${mm + 1}/$yy"
         TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{timePicker, hr, mi ->
             dt += " $hr:$mi"
             binding.tvSelectedDate.text = dt

         },c.get(Calendar.HOUR),c.get(Calendar.MINUTE),false).show()

     },
        c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show()
    }


    fun calculateAge(view : View){
       var today = Date()
        var dobs = binding.tvSelectedDate.text.toString()
          var sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
          // converting string of date into object of date with simpleDateFormat
          var dob = sdf.parse(dobs)

        //aj ka time - user ne jo select kia vo time
        // it will return the value in miliseconds
        // how to convert miliseconds into days ?
        // per day 86400 sec , 86400 * 1000 milisec
        // time/milisec
         var days = (today.time - dob.time)/86400000
         var hours =  (today.time - dob.time)%86400000/3600000
         var minutes =  (today.time - dob.time)%86400000%3600000/60000
         var sec =  (today.time - dob.time)%86400000%3600000%60000/1000
         var age = (today.year - dob.year)
         if(today.day < dob.day){
             age--
         }



        binding.tvSelectedDateInMinutes.visibility = View.VISIBLE
        binding.tvSelectedDateInMinutes.text = "Current age : $age\nDays = $days\nHours = $hours\nMinutes = $minutes\nSeconds = $sec"

    }
}