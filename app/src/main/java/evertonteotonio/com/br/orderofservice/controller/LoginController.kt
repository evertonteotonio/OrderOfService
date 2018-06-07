package evertonteotonio.com.br.orderofservice.controller

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import android.Manifest.permission.READ_CONTACTS
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.User
import evertonteotonio.com.br.orderofservice.repository.UserRepository

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.db.*
import java.util.*

/**
 * Uma tela de login que oferece login via email/password.
 */
class LoginController : AppCompatActivity(), LoaderCallbacks<Cursor> {
    /**
     * Mantenha o controle da tarefa de login para garantir que possamos cancelá-la se solicitado.
     */
    private var mAuthTask: UserLoginTask? = null

    //private var authOrderServiceFirebase: FirebaseAuth? = null

    private var authOrderServiceFirebase = FirebaseAuth.getInstance()

    private var currentUser: FirebaseUser? = authOrderServiceFirebase?.getCurrentUser()

    fun signIn(email: String, password: String) {

        Toast.makeText(this, "Autenticando...", Toast.LENGTH_SHORT).show()

        authOrderServiceFirebase.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->

            if(task.isSuccessful){
                var intent = Intent(this, LoginController::class.java)
                intent.putExtra("id", authOrderServiceFirebase.currentUser?.email)
                startActivity(intent)
                //Toast.makeText(this, "sucesso na autenticação", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Login e senhas incorretos!!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Configurar o formulário de login.
        populateAutoComplete()
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener { attemptLogin() }

    }

    //Exibe ou esconde o inpute de nome para cadastro
    fun isRegister(view: View)
    {
        if (action_cad.isChecked){
            lbName.setVisibility(View.VISIBLE)
            name.requestFocus()
            email_sign_in_button.text = "Cadastrar"

        }
        else {
            lbName.setVisibility(View.INVISIBLE)
            email_sign_in_button.text = "Entrar"
        }

    }




    override fun onStart() {
        super.onStart();
        // Verifique se o usuário está conectado (não nulo) e atualize a UI de acordo.
        //var currentUser: FirebaseUser? = authOrderServiceFirebase?.getCurrentUser()
        if (currentUser != null) {
            val intent = Intent(this@LoginController, MenuController::class.java)
            startActivity(intent)
            this@LoginController.finish()
        }
    }

    private fun populateAutoComplete() {
        if (!mayRequestContacts()) {
            return
        }

        loaderManager.initLoader(0, null, this)
    }

    private fun mayRequestContacts(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(email, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok,
                            { requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS) })
        } else {
            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
        }
        return false
    }

    /**
     * Retorno de chamada recebido quando uma solicitação de permissão foi concluída.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }


    /**
     * Tenta entrar ou registrar a conta especificada pelo formulário de login.
     * Se houver erros de formulário (e-mail inválido, campos ausentes, etc.), o
     * Erros são apresentados e nenhuma tentativa real de login é feita.
     */
    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        email.error = null
        password.error = null
        name.error = null

        // Armazenar valores no momento da tentativa de login.
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()
        val nameStr = name.text.toString()

        var cancel = false
        var focusView: View? = null

        val user = UserRepository(this).findByEmail(emailStr)

        // Verifique se há uma senha válida, se o usuário digitou uma.
        if (TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Verificar um endereço de email válido.
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }

        // Verificar um nome foi inserido
        if (TextUtils.isEmpty(nameStr) && action_cad.isChecked) {
            name.error = getString(R.string.error_field_required)
            focusView = name
            cancel = true
        }

        // se for cadastro exige uma senha.
        if (TextUtils.isEmpty(passwordStr) && action_cad.isChecked) {
            password.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        }

        if (cancel) {
            // Havia um erro; não tente entrar e focar o primeiro
            // campo de formulário com um erro.
            focusView?.requestFocus()
        } else {
            // Mostre um spinner de progresso e inicie uma tarefa em segundo plano para
            // realizar a tentativa de login do usuário.
            //showProgress(true) mostra progresse bar
//            mAuthTask = UserLoginTask(emailStr, passwordStr)
//            mAuthTask!!.execute(null as Void?)


            if (action_cad.isChecked)
            {
                if (user?.size == 0){
                    UserRepository(this).create(UUID.randomUUID().toString(),
                            nameStr, emailStr, passwordStr)

                    //Autenticando
                    showProgress(true)
                    signIn(emailStr, passwordStr)
                }
                else {
                    Toast.makeText(this, "Esse usuário já está cadastrado", Toast.LENGTH_SHORT).show()
                }

            }else{

                showProgress(true)
                signIn(emailStr, passwordStr)
                //showProgress(false)

                if (currentUser != null) {
                    val intent = Intent(this@LoginController, MenuController::class.java)
                    startActivity(intent)
                    this@LoginController.finish()
                }
            }


        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Mostra a interface do usuário do progresso e oculta o formulário de login.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // No Honeycomb MR2 temos as APIs ViewPropertyAnimator, que permitem
        // para animações muito fáceis. Se disponível, use essas APIs para aumentar o brilho
        // o spinner de progresso.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // As APIs do ViewPropertyAnimator não estão disponíveis, portanto, basta mostrar
            // e ocultar os componentes relevantes da interface do usuário.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor> {
        return CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Selecione apenas endereços de email.
                ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE),

                // Mostrar os endereços de e-mail principais primeiro. Note que não haverá
                // um endereço de e-mail principal, se o usuário não tiver especificado um.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }

        addEmailsToAutoComplete(emails)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {

    }

    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>) {
        //Crie o adaptador para informar ao AutoCompleteTextView o que mostrar em sua lista suspensa.
        val adapter = ArrayAdapter(this@LoginController,
                android.R.layout.simple_dropdown_item_1line, emailAddressCollection)

        email.setAdapter(adapter)
    }

    object ProfileQuery {
        val PROJECTION = arrayOf(
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY)
        val ADDRESS = 0
        val IS_PRIMARY = 1
    }

    /**
     * Representa uma tarefa de login / registro assíncrona usada para autenticar o usuário
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: tente autenticação contra um serviço de rede.

            try {
                // Simule o acesso à rede.
                signIn(mEmail, mPassword)
                //Thread.sleep(2000)

                var currentUser: FirebaseUser? = authOrderServiceFirebase?.getCurrentUser()
                if (currentUser != null) {
                    return true
                }

            } catch (e: InterruptedException) {
                return false
            }

            return false

//            return DUMMY_CREDENTIALS
//                    .map { it.split(":") }
//                    .firstOrNull { it[0] == mEmail }
//                    ?.let {
//                        // Conta existe, retornar verdadeiro se a senha corresponder.
//                        it[1] == mPassword
//                    }
//                    ?: true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                finish()
            } else {
                password.error = getString(R.string.error_incorrect_password)
                password.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {

        /**
         * Id para identificar a solicitação de permissão READ_CONTACTS.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * Um armazenamento de autenticação fictício contendo nomes de usuários e senhas conhecidos.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}
