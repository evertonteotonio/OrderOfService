package evertonteotonio.com.br.orderofservice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.listener.OnItemClickListener
import evertonteotonio.com.br.orderofservice.model.Order
import evertonteotonio.com.br.orderofservice.model.OrderService
import android.R.attr.data
import kotlinx.android.synthetic.main.order_list_item.view.*


class  OrderAdapter(private val  context: Context, data: ArrayList<OrderService>) :
        RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    //private val context: Context? = null
    private val inflater: LayoutInflater? = null
    private val data: ArrayList<OrderService> = data

    private val clickListener: OnItemClickListener? = null

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName = itemView.tvName
        val tvAPI = itemView.tvAPI
        val tvVersao = itemView.tvDescription





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.OrderViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_list_item, parent, false)
        return OrderViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {


        val orderHolder = holder
        val currentOrder = data[position]

        orderHolder.tvName.text = "Solicitante: " + currentOrder.client?.name
        orderHolder.tvName.text = "Descrição: " + currentOrder.task?.description


    }

}