package evertonteotonio.com.br.orderofservice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.MainActivity
import android.content.Intent



class AboutActivity : MenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_about
    }

    override
    fun getNavigationMenuItemId(): Int {
        return R.id.navigation_about
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_about)

//        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

//    }

//    override fun onBackPressed() { //Botão BACK padrão do android
//        startActivity(Intent(this, MenuActivity::class.java)) //O efeito ao ser pressionado do botão (no caso abre a activity)
//        this@AboutActivity.finish()//Método para matar a activity e não deixa-lá indexada na pilhagem
//        return
//    }
}
