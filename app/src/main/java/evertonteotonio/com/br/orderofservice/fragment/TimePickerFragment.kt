package evertonteotonio.com.br.orderofservice.fragment

import android.app.Dialog
import android.widget.TimePicker
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*
import android.text.format.DateFormat;
import android.widget.TextView
import evertonteotonio.com.br.orderofservice.R
import kotlinx.android.synthetic.main.fragment_task.*
import java.text.SimpleDateFormat


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user

        var minuto: String = "00"
        if (minute < 10) {
            minuto = "0$minute"
        }
        else{
            minuto = "$minute"
        }

        (activity!!.findViewById(R.id.tvTime) as TextView).text = "$hourOfDay:$minuto"
    }
}