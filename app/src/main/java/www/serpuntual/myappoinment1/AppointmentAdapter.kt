package www.serpuntual.myappoinment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_appointment.view.*
import www.serpuntual.myappoinment1.model.Appointment

class AppointmentAdapter(private val appointments: ArrayList<Appointment>)
    : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(appointment: Appointment) = with(itemView){
                tvAppointmentId.text = context.getString(R.string.item_appointments_id, appointment.id)
                tvDoctorName.text = appointment.doctorName
                tvSheduledDate.text = context.getString(R.string.item_appointments_date, appointment.scheduledDate)
                tvSheduledTime.text = context.getString(R.string.item_appointments_time, appointment.scheduledTime)


        }

    }

    //created view  infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        )
    }
    //Number of elements
    override fun getItemCount() = appointments.size


        //enlazar data que viene
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]
            holder.bind(appointment)
    }
}