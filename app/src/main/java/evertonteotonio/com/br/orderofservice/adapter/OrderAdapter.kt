package evertonteotonio.com.br.orderofservice.adapter

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.activity.OrderServiceActivity
import evertonteotonio.com.br.orderofservice.listener.OnItemClickListener
import evertonteotonio.com.br.orderofservice.model.OrderService
import kotlinx.android.synthetic.main.order_list_item.view.*
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.toast

class  OrderAdapter(private val  context: Context, data: ArrayList<OrderService>) :
        RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    //private val context: Context? = null
    private val inflater: LayoutInflater? = null
    private val data: ArrayList<OrderService> = data

    fun setClickListener(itemClickListener: OnItemClickListener) {
        this.clickListener = itemClickListener
    }

    private var clickListener: OnItemClickListener? = null

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener{

        val tvName = itemView.tvName
        val tvDate = itemView.tvDate
        val tvDescription = itemView.tvDescription
        //val ibInfo = itemView.ibInfo.setOnClickListener(this)
        val ibUpdate = itemView.ivCall.setOnClickListener(this)
        val layContent = itemView.layContent.setOnClickListener(this)
        val cellPhone = itemView.tvCellPhone

        var uuidOrder: String? = null

        override fun onClick(v: View?) {

            if(v != null) {

                if (v.ivCall?.isClickable == true){

                    val permission = ContextCompat.checkSelfPermission(v.context,
                            android.Manifest.permission.CALL_PHONE)

                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        v.context.toast("Você precisa conceder permissão para efetuar essa chamada")

                    }
                    else {
                        val re = Regex("[^0-9 ]")
                        val cell = cellPhone.text.toString().replace(re, "")

                        v.context.makeCall(cell)
                    }

                } else if (v.layContent?.isClickable == true){

                    val intent = Intent(v.context, OrderServiceActivity::class.java)
                    intent.putExtra("UUID_ORDER", uuidOrder);
                    context.startActivity(intent)

                }


            }

            if (clickListener != null) clickListener!!.onClick(itemView, getAdapterPosition());

        }

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
        orderHolder.tvDescription.text = "Descrição: " + currentOrder.task?.description
        orderHolder.tvDate.text = "Agendado para: " +
                currentOrder.task?.date + " " + currentOrder.task?.time

        orderHolder.cellPhone.text = "Celular: " + currentOrder.client?.cellPhone


        // Obtem o id da pesquisa para quando for editar a ordem de serviço
        orderHolder.uuidOrder = currentOrder.order?.uuid

    }

}