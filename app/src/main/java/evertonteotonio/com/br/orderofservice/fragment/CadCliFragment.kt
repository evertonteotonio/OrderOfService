package evertonteotonio.com.br.orderofservice.fragment


import android.database.DatabaseUtils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import evertonteotonio.com.br.orderofservice.R
import kotlinx.android.synthetic.main.fragment_cad_cli.view.*
import java.util.*


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

            if (view.dataContact.visibility == View.INVISIBLE){
                view.dataContact.setVisibility(View.VISIBLE)
            } else {
                view.dataContact.setVisibility(View.INVISIBLE)
            }
        })

        val btnSaveCli = view.btnSaveCli

        btnSaveCli.setOnClickListener(View.OnClickListener() {

            val nameCli = view.nameCli
            val emailCli = view.emailCli
            val cellPhone = view.cellPhone
            val phoneCli = view.phoneCli

//            ClientRepository(this).create(
//                    UUID.randomUUID().toString(),
//                    nameCli.toString(),
//                    emailCli.toString(),
//                    cellPhone.toString(),
//                    phoneCli.toString()
//            )





        })



        return view
    }

}
