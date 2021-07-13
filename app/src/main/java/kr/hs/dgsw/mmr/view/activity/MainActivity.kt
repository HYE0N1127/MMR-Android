package kr.hs.dgsw.mmr.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityMainBinding
import kr.hs.dgsw.mmr.factory.MainViewModelFactory
import kr.hs.dgsw.mmr.viewmodel.MainViewModel

class MainActivity() : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    override fun observerViewModel() {
        with(mViewModel) {

        }
    }

    override val viewModel =
        ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
}