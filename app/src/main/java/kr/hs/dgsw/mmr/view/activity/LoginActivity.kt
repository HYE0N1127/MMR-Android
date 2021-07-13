package kr.hs.dgsw.mmr.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.databinding.ActivityLoginBinding
import kr.hs.dgsw.mmr.viewmodel.activity.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel) {
            loginResult.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, "로그인 성공 : $it", Toast.LENGTH_SHORT).show()
            })
            error.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, "${it.message}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}