package kr.hs.dgsw.mmr.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityMainBinding
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.viewmodel.activity.MainViewModel

enum class FragmentEnum {
    Home, Write, Profile
}

class MainActivity() : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var host: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel =
            ViewModelProvider(this, NoParameterViewModelFactory()).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)


        // binding = ActivityMainBinding.inflate(layoutInflater)
        host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val controller = host.navController

        mBinding.navBottomBar.setupWithNavController(controller)

    }

    override fun observerViewModel() {
        with(mViewModel) {
            openSettingEvent.observe(this@MainActivity, {
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
            })
        }
    }

    override fun onErrorEvent(e: Throwable) {
        super.onErrorEvent(e)
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
    }
}