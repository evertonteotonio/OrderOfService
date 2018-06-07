package evertonteotonio.com.br.orderofservice.fragment


import android.database.DatabaseUtils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import evertonteotonio.com.br.orderofservice.R
import kotlinx.android.synthetic.main.fragment_cad_cli.*
import android.R.attr.fragment
import android.R.attr.onClick
import kotlinx.android.synthetic.main.fragment_cad_cli.view.*


// TODO: Renomear argumentos do parâmetro, escolher nomes que correspondam
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CadCliFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_cad_cli, container, false)

        val viewDataCli = view.showHideFieldsContact



        viewDataCli.setOnClickListener(View.OnClickListener() {
            Log.i("Clik", "Foi clicado")

            if (view.data_contact.visibility == View.INVISIBLE){
                view.data_contact.setVisibility(View.VISIBLE)
            } else {
                view.data_contact.setVisibility(View.INVISIBLE)
            }



        })


        return view


    }

}
