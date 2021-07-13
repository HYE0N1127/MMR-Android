package kr.hs.dgsw.mmr

import android.os.Bundle
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityMainBinding
import kr.hs.dgsw.mmr.viewmodel.TestViewModel

class MainActivity() : BaseActivity<ActivityMainBinding, TestViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) 
    }

    override val viewModel: TestViewModel
        get() = TODO("Not yet implemented")

    override fun observerViewModel() {
        TODO("Not yet implemented")
    }
}