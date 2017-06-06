package br.com.devnull.cervejabarata

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import br.com.devnull.cervejabarata.models.User
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.vicpin.krealmextensions.save
import io.realm.Realm
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.startActivity


class MainActivity : Activity() {

    private var callbackManager: CallbackManager? = null
    private var info: TextView? = null
    private var loginButton: LoginButton? = null
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callbackManager = CallbackManager.Factory.create()

        setContentView(R.layout.main_activity)
        info = findViewById(R.id.info) as TextView
        loginButton = login_button as LoginButton

        login_button.setReadPermissions("email", "public_profile")
        login_button!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Toast.makeText(this@MainActivity, getString(R.string.login_error), Toast.LENGTH_LONG).show()
            }

            override fun onError(e: FacebookException) {
                Toast.makeText(this@MainActivity, getString(R.string.login_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        callbackManager!!.onActivityResult(requestCode, resultCode, intent)
    }

    private fun handleFacebookAccessToken(token : AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            val user = mAuth.currentUser
            if (user != null) {
                Realm.init(applicationContext)
                User(0, user.displayName!!, user.photoUrl.toString(), user.email!!).save()
                startActivity<DrawerActivity>()
                finish()
            }
        }
    }


}
