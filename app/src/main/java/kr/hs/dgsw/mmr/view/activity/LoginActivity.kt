package kr.hs.dgsw.mmr.view.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
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
    }

    override fun observerViewModel() {
        with(mViewModel) {
            loginResult.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, "로그인 성공 : $it", Toast.LENGTH_SHORT).show()

                val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
                preferencesEditor.putString("name", it)
                preferencesEditor.apply()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            })
            error.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, "${it.message}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}