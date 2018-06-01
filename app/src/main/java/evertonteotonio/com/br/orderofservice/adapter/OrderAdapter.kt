package evertonteotonio.com.br.orderofservice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.Collections.emptyList
import android.view.LayoutInflater
import evertonteotonio.com.br.orderofservice.listener.OnItemClickListener
import evertonteotonio.com.br.orderofservice.model.Order
import java.util.*


class  OrderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context? = null
    private val inflater: LayoutInflater? = null
    private val data: List<Order> = Collections.emptyList()

    private val clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}