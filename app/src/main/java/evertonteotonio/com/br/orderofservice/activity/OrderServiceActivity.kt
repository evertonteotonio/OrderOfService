package evertonteotonio.com.br.orderofservice.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.FileProvider
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.LinearLayout
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.api.CEP
import evertonteotonio.com.br.orderofservice.api.RetrofitInitializer
import evertonteotonio.com.br.orderofservice.fragment.*
import evertonteotonio.com.br.orderofservice.model.OrderService
import evertonteotonio.com.br.orderofservice.repository.AddressRepository
import evertonteotonio.com.br.orderofservice.repository.ClientRepository
import evertonteotonio.com.br.orderofservice.repository.OrderServiceRepository
import evertonteotonio.com.br.orderofservice.repository.TaskRepository
import kotlinx.android.synthetic.main.activity_order_service.*
import kotlinx.android.synthetic.main.fragment_address_cli.*
import kotlinx.android.synthetic.main.fragment_cad_cli.*
import kotlinx.android.synthetic.main.fragment_save.*
import kotlinx.android.synthetic.main.fragment_task.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class OrderServiceActivity : MenuActivity() {


    val fragmentMan = supportFragmentManager

    val fragmentCadCli = CadCliFragment()
    val fragmentCliAddress = AddressCliFragment()
    val fragmentTask = TaskFragment()
    val fragmentSave = SaveFragment()
    var uuidOrder: String? = null
    var order: OrderService? = null

    val CAMERA_REQUEST_CODE = 0
    lateinit var imageFilePath: String

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        //clearFragments()


        when (item.itemId) {
            R.id.navigation_menu_cad_cli -> {

                fragmentholder_task.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_cli.setVisibility(LinearLayout.VISIBLE);

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu_address -> {


                fragmentholder_cli.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_task.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.VISIBLE);
                //openFragmentAddress(fragmentCliAddress)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_task -> {


                fragmentholder_cli.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_task.setVisibility(LinearLayout.VISIBLE);

                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_save -> {

                loadInfoService()
                fragmentholder_cli.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_task.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.VISIBLE);

                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    fun loadInfoService()
    {
        tvNameCli.text = "Nome: " + nameCli.text.toString()
        tvEmailCli.text = "Email: " + emailCli.text.toString()
        tvCellPhone.text = "Celular: " + cellPhone.text.toString()
        tvPhone.text = "Telefone: " + phoneCli.text.toString()


        tvCep.text = "CEP: " + cep.text.toString()
        tvAddress.text = "Endereço: " + address.text.toString()
        tvNumber.text = "Número: " + number.text.toString()
        tvDistrict.text = "Bairro: " + district.text.toString()
        tvCity.text = "Cidade: " + city.text.toString()
        tvUf.text = "Estado: " + uf.text.toString()

        tvDescription.text = "Descrição: " + description.text.toString()

        tvDate.text = tvDate.text.toString()
        tvTime.text = tvTime.text.toString()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        uuidOrder = intent.getStringExtra("UUID_ORDER")

        if(savedInstanceState == null){
            openFragmentCli(fragmentCadCli)
            openFragmentAddress(fragmentCliAddress)
            openFragmentTask(fragmentTask)
            openFragment(fragmentSave)
        }

        val dialogPermissionListener = DialogOnDeniedPermissionListener.Builder
                .withContext(this.baseContext)
                .withTitle("Permissão de camera")
                .withMessage("É necessária a permissão da camera para tirar uma foto")
                .withButtonText(android.R.string.ok)
                .build()

        Dexter.withActivity(this@OrderServiceActivity)
                .withPermission(android.Manifest.permission.CAMERA )
                .withListener(dialogPermissionListener)
                .onSameThread()
                .check();


        navigation_menu_cad_cli.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_order_service
    }

    override
    fun getNavigationMenuItemId(): Int {
        return R.id.navigation_new_service
    }

    fun clearFragments()
    {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_order, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openFragmentCli(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_cli, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openFragmentAddress(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_address, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openFragmentTask(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_task, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun saveOrderService(view: View)
    {

        val clientId = UUID.randomUUID().toString()
        val cli = ClientRepository(this).create(
                clientId,
                this.nameCli.text.toString(),
                this.emailCli.text.toString(),
                this.cellPhone.text.toString(),
                this.phoneCli.text.toString()
        )

        val addressId = UUID.randomUUID().toString()
        val addr = AddressRepository(this).create(
                addressId,
                cep.text.toString(),
                address.text.toString(),
                number.text.toString(),
                district.text.toString(),
                city.text.toString(),
                uf.text.toString()
        )

        val taskId = UUID.randomUUID().toString()
        val task = TaskRepository(this).create(
                taskId,
                description.text.toString(),
                tvDate.text.toString(),
                tvTime.text.toString()
        )

        if (cli){

            val order = OrderServiceRepository(this).create(
                    "1223",
                    clientId,
                    taskId,
                    addressId,
                    "teste"
            )
            toast("Dados salvo com sucesso")

        }


        startActivity(Intent(this, OrderListActivity::class.java))

    }


    fun updateOrderService(view: View)
    {
        ClientRepository(this).update(
                order?.client?.uuid.toString(),
                this.nameCli.text.toString(),
                this.emailCli.text.toString(),
                this.cellPhone.text.toString(),
                this.phoneCli.text.toString()
        )

        AddressRepository(this).update(
                order?.address?.uuid.toString(),
                cep.text.toString(),
                address.text.toString(),
                number.text.toString(),
                district.text.toString(),
                city.text.toString(),
                uf.text.toString()
        )

       TaskRepository(this).update(
                order?.task?.uuid.toString(),
                description.text.toString(),
                tvDate.text.toString(),
                tvTime.text.toString()
        )

        toast("Dados atualizados com sucesso")
        startActivity(Intent(this, OrderListActivity::class.java))

    }


    fun viewMap(v: View)
    {
        startActivity(Intent(this, MapsActivity::class.java))
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun showTimePickerDialog(v: View) {
        val newFragment = TimePickerFragment()
        newFragment.show(supportFragmentManager, "timePicker")
    }

    fun getCapturePhoto(v: View)

    {

        try {
            val imageFile = createImageFile()
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager) != null) {
                val authorities = packageName + ".fileprovider"
                val imageUri = FileProvider.getUriForFile(this, authorities, imageFile)
                callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        } catch (e: IOException) {
            Toast.makeText(this, "Não foi possível criar o arquivo!",
                    Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName: String = "JPEG_" + timeStamp + "_"
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if(!storageDir.exists()) storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        return imageFile
    }

    fun setScaledBitmap(): Bitmap {
        val imageViewWidth = photoImageView.width
        val imageViewHeight = photoImageView.height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

        val scaleFactor = Math.min(bitmapWidth/imageViewWidth, bitmapHeight/imageViewHeight)

        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor

        return BitmapFactory.decodeFile(imageFilePath, bmOptions)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
/*                if(resultCode == Activity.RESULT_OK && data != null) {
                    photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)
                }*/
                if (resultCode == Activity.RESULT_OK) {
                    photoImageView.setImageBitmap(setScaledBitmap())
                }
            }
            else -> {
                Toast.makeText(this, "Código de solicitação não reconhecido",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getDataOrder(uuidOrder: String)
    {
        order = OrderServiceRepository(this).getOrderServicesById(uuidOrder)

        if (order != null) {


            if (order?.client != null){
                this.nameCli.setText(order?.client?.name)
                this.emailCli.setText(order?.client?.email)
                this.cellPhone.setText(order?.client?.cellPhone)
                this.phoneCli.setText(order?.client?.phone)

            }

            if (order?.address != null){
                this.cep.setText(order?.address?.cep)
                this.address.setText(order?.address?.address)
                this.number.setText(order?.address?.number)
                this.district.setText(order?.address?.district)
                this.city.setText(order?.address?.city)
                this.uf.setText(order?.address?.uf)
            }

            if (order?.task != null){
                this.tvDate.setText(order?.task?.date)
                this.tvTime.setText(order?.task?.time)
                this.description.setText(order?.task?.description)
            }

        }

    }


    override fun onResume() {
        super.onResume()

        cep.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->

            val cep = cep.text.toString()



            if (cep.length == 8 && hasFocus == false){

                val mProgressDialog = indeterminateProgressDialog("Pesquisando endereço…",
                        "Pesquisando")

                mProgressDialog.show()

                val call = RetrofitInitializer()
                        .apiRetrofitService()
                        .getEnderecoByCEP(cep)

                call.enqueue(object : Callback<CEP> {

                    override fun onResponse(call: Call<CEP>, response: Response<CEP>) {

                        response?.let {

                            val CEPs: CEP? = it.body()

                            if (CEPs != null) {
                                address.setText(CEPs.logradouro)
                                district.setText(CEPs.bairro)
                                city.setText(CEPs.localidade)
                                uf.setText(CEPs.uf)
                            }
                            mProgressDialog.dismiss()
                        }

                    }

                    override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                        toast("Ouve um erro ao consultar o CEP")
                    }


                })

            }



        }

        if (uuidOrder != null){

            // Escondendo o botão de salvar
            btnSaveCli.isEnabled = false
            btnSaveCli.layoutParams.height = 0
            btnSaveCli.setVisibility(View.INVISIBLE)


            getDataOrder(uuidOrder.toString())
        }
        else {
            btnUpdateCli.isEnabled = false
            btnUpdateCli.layoutParams.height = 0
            btnUpdateCli.setVisibility(View.INVISIBLE)
        }
    }


}

