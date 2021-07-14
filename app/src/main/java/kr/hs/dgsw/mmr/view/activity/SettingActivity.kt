package kr.hs.dgsw.mmr.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        binding.activity = this
    }

    fun onClickLogOut(view: View) {
        var mPreferences: SharedPreferences = getSharedPreferences("LoginActivity", MODE_PRIVATE)
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
        preferencesEditor.remove("name")
        preferencesEditor.remove("id")
        preferencesEditor.remove("checked")
        preferencesEditor.apply()
        val intent = Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}