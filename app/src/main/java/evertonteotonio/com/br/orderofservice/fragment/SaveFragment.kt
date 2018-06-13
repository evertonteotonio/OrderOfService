package evertonteotonio.com.br.orderofservice.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.activity.OrderServiceActivity
import kotlinx.android.synthetic.main.fragment_address_cli.view.*
import kotlinx.android.synthetic.main.fragment_cad_cli.*
import kotlinx.android.synthetic.main.fragment_save.*
import kotlinx.android.synthetic.main.fragment_save.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SaveFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_save, container, false)

//        val nameCli = getArguments()?.getString("nameCli");
//        val tvEmailCli = getArguments()?.getString("emailCli");
//        val tvCellPhone = getArguments()?.getString("cellPhone");
//        val tvPhone = getArguments()?.getString("phoneCli");
//        val cep = getArguments()?.getString("cep");
//
//
//        view.tvNameCli.setText("Nome: " + nameCli)
//        view.tvEmailCli.setText("Email: " + tvEmailCli)
//        view.tvCellPhone.setText("Celular: " + tvCellPhone)
//        view.tvPhone.setText("Celular: " + tvPhone)
//
//        view.tvCep.setText("CEP: " + cep)


        return view

    }


}
