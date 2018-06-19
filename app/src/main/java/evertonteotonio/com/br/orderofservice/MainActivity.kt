package evertonteotonio.com.br.orderofservice

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.Tasks.call
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import evertonteotonio.com.br.orderofservice.activity.LoginActivity
import evertonteotonio.com.br.orderofservice.activity.MenuActivity
import evertonteotonio.com.br.orderofservice.activity.OrderListActivity

class MainActivity : AppCompatActivity() {

    //Tempo que nosso splashscreen ficará visivel
    private var waiting_time: Long = 3500

    private var authOrderServiceFirebase = FirebaseAuth.getInstance()

    //modificar para chamar outra activity
    private var currentUser: FirebaseUser? = authOrderServiceFirebase?.getCurrentUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (currentUser != null) {
            val intent = Intent(this@MainActivity, OrderListActivity::class.java)
            startActivity(intent)
            this@MainActivity.finish()
        }
        loadLogin()
    }

    private fun loadLogin() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_main)
        anim.reset()

        //Pegando o nosso objeto criado no layout
        val iv = findViewById<View>(R.id.imgLogo) as ImageView
        iv.clearAnimation()
        iv.startAnimation(anim)

        Handler().postDelayed(Runnable {
            // Após o tempo definido irá executar a próxima tela
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            this@MainActivity.finish()
        }, waiting_time)
    }
}
