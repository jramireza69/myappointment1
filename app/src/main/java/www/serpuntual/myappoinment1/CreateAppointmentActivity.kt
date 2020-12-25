package www.serpuntual.myappoinment1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_create_appointment.*
import kotlinx.android.synthetic.main.activity_menu.*
import java.util.*

class   CreateAppointmentActivity : AppCompatActivity() {
    private var selectedCalendar = Calendar.getInstance()
    private  var selectedRadioButton: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)

        btnNext.setOnClickListener {
            if (etDescripcion.text.toString().length < 3){
                etDescripcion.error = getString(R.string.error_descripcion)

            }else {
                cvStep1.visibility = View.GONE
                cvStep2.visibility = View.VISIBLE
            }


        }
        btnConfirmAppointment.setOnClickListener {
            Toast.makeText(this, "Cita registrada correcatamente", Toast.LENGTH_SHORT).show()
            finish()
        }

        val specialtyOptions = arrayOf("Specialty A","Specialty B","Specialty c")
        spinerSpecialties.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, specialtyOptions)

        val doctorOptions = arrayOf("Doctor A","Doctor B","Doctor c")
        spinerDoctors.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, doctorOptions)
    }

    fun onClickScheduledDate(v: View?){

        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener { view, y, m, d ->
          //  Toast.makeText(this, "$y-$m-$d", Toast.LENGTH_SHORT).show()
            selectedCalendar.set(y,m,d)
            etScheduledDate.setText(
                resources.getString(R.string.date_format,
                    y,
                    m.twoDigits(),
                    d.twoDigits()
                )
            )
                displayRadioButton()
        }

 val datePikerDialog =  DatePickerDialog(this, listener, year,  month, dayOfMonth)
val datePiker = datePikerDialog.datePicker  //acceso instancia datepiker
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH,0)
        datePiker.minDate =calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_MONTH, 29)
        datePiker.maxDate =calendar.timeInMillis
        datePikerDialog.show()
    }
    private fun displayRadioButton(){

        selectedRadioButton = null
       radioGroupLeft.removeAllViews()
       radioGroupRigt.removeAllViews()

        val hours = arrayOf("3:00 PM ","3:30 PM ","4:00 PM ","4:30 PM")
        var goToLeft = true
        hours.forEach {
            val radioButton = RadioButton(this )
            radioButton.id = View.generateViewId()
            radioButton.text = it
            radioButton.setOnClickListener {viev ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton = viev as RadioButton?
                selectedRadioButton?.isChecked = true

            }
            if (goToLeft)
            radioGroupLeft.addView(radioButton)
            else
                radioGroupRigt.addView(radioButton)
                goToLeft = !goToLeft

        }

    }
    fun Int.twoDigits()
     = if (this >= 10) this.toString() else "0$this"

    override fun onBackPressed() {
        if (cvStep2.visibility == View.VISIBLE){
            cvStep1.visibility = View.VISIBLE
            cvStep2.visibility = View.GONE
        }else if (cvStep1.visibility == View.VISIBLE){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.dialogo_create_appointment_exit_title))
            builder.setMessage(getString(R.string.dialogo_create_appointment_exit1))
            builder.setPositiveButton(getString(R.string.dialogo_create_appointment_exit2)) { _, _ ->
                finish()
            }
            builder.setNegativeButton(getString(R.string.dialogo_create_appointment_exit3)){ dialog, _ ->
                dialog.dismiss()
            }
            val dialog =  builder.create()
            dialog.show()
         }
        }
    }




