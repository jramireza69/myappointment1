package www.serpuntual.myappoinment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_appointments.*
import www.serpuntual.myappoinment1.model.Appointment

class AppointmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)
        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(1, "Doctor Test", "12/12/2020", "12:00 PM" )
        )
        appointments.add(
            Appointment(2, "Doctor Test1", "13/12/2020", "12:30 PM" )
        )
        appointments.add(
            Appointment(3, "Doctor Test2", "14/12/2020", "13:30 PM" )
        )


        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter = AppointmentAdapter(appointments)

    }
}