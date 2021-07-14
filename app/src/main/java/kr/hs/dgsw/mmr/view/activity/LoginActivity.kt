package kr.hs.dgsw.mmr.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityLoginBinding
import kr.hs.dgsw.mmr.viewmodel.activity.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    private lateinit var mPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        super.onCreate(savedInstanceState)
        mPreferences = getSharedPreferences("LoginActivity", MODE_PRIVATE)
        keepLoginCheck()
    }

    override fun observerViewModel() {
        with(mViewModel) {
            loginResult.observe(this@LoginActivity, {


                val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
                preferencesEditor.putString("name", it)
                preferencesEditor.putString("id", id.value)
                if (mBinding.checkBox.isChecked) {
                    preferencesEditor.putString("checked", "true")
                }
                preferencesEditor.apply()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
            error.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, "${it.message}", Toast.LENGTH_SHORT).show()
            })
            openSingUp.observe(this@LoginActivity, {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            })

        }
    }

    private fun keepLoginCheck() {
        if (!mPreferences.getString("name", null).toString()
                .equals(null) && !mPreferences.getString("id", null).toString()
                .equals(null) && mPreferences.getString("checked", "false").toString() == "true"
        ) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}