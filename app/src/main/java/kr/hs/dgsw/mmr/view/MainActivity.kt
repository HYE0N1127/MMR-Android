package kr.hs.dgsw.mmr.view

import android.os.Bundle
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityMainBinding
import kr.hs.dgsw.mmr.viewmodel.MainViewModel

class MainActivity() : BaseActivity<ActivityMainBinding, MainViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun observerViewModel() {
        TODO("Not yet implemented")
    }

    override val viewModel: MainViewModel
        get() = TODO("Not yet implemented")
}