package evertonteotonio.com.br.orderofservice.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import evertonteotonio.com.br.orderofservice.R


/**
 * A simple [Fragment] subclass.
 *
 */
class CadCliFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cad_cli, container, false)
    }

}
