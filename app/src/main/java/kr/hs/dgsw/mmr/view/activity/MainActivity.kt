package kr.hs.dgsw.mmr.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityMainBinding
import kr.hs.dgsw.mmr.factory.MainViewModelFactory
import kr.hs.dgsw.mmr.view.fragment.HomeFragment
import kr.hs.dgsw.mmr.view.fragment.ProfileFragment
import kr.hs.dgsw.mmr.view.fragment.WriteFragment
import kr.hs.dgsw.mmr.viewmodel.activity.MainViewModel

enum class FragmentEnum {
    Home, Write, Profile
}

class MainActivity() : BaseActivity<ActivityMainBinding, MainViewModel>() {


    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var binding: ActivityMainBinding

    private val writeFragment: WriteFragment = WriteFragment()
    private val homeFragment: HomeFragment = HomeFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()

    private fun changePage(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment)
            .commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel =
            ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel) {
            mBinding.navBottomBar.setOnNavigationItemSelectedListener {
                currentPage.value = when(it.itemId) {
                    R.id.home -> FragmentEnum.Home
                    R.id.write_post -> FragmentEnum.Write
                    R.id.profile -> FragmentEnum.Profile
                    else -> FragmentEnum.Home
                }

                return@setOnNavigationItemSelectedListener true
            }

            currentPage.observe(this@MainActivity, {
                when(it) {
                    FragmentEnum.Home -> {
                        changePage(homeFragment)
                    }
                    FragmentEnum.Write -> {
                        changePage(writeFragment)
                    }
                    FragmentEnum.Profile -> {
                        changePage(profileFragment)
                    }
                    else -> {}
                }
            })

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