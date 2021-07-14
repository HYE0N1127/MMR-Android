package kr.hs.dgsw.mmr.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivitySignUpBinding
import kr.hs.dgsw.mmr.viewmodel.activity.SignUpViewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel){
            registerResult.observe(this@SignUpActivity, {
                Toast.makeText(this@SignUpActivity, "회원 가입 성공! ${it}", Toast.LENGTH_SHORT).show()
                finish()
            })
            error.observe(this@SignUpActivity,{
                Toast.makeText(this@SignUpActivity, "회원 가입 실패 ${it.message}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}