package evertonteotonio.com.br.orderofservice.fragment

import android.widget.DatePicker
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.system.Os.bind
import android.widget.EditText
import android.widget.TextView
import evertonteotonio.com.br.orderofservice.R
import kotlinx.android.synthetic.main.activity_order_service.*
import kotlinx.android.synthetic.main.fragment_task.*
import kotlinx.android.synthetic.main.fragment_task.view.*
import java.text.SimpleDateFormat
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var data: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(getActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user

        val date = Date("$year/$month/$day" )
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        if (data == null) {
            data = format.format(date).toString()
        }

        (activity!!.findViewById(R.id.tvDate) as TextView).text = "$data"
    }
}