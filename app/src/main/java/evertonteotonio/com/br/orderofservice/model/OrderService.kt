package evertonteotonio.com.br.orderofservice.model

data class OrderService(val order: Order?,
                        val client: Client?,
                        val address: Address?,
                        val task: Task?)
{

}