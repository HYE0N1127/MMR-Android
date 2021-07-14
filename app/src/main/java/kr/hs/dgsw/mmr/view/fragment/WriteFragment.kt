package kr.hs.dgsw.mmr.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.databinding.FragmentWriteBinding
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.viewmodel.fragment.WriteViewModel

class WriteFragment() : BaseFragment<FragmentWriteBinding, WriteViewModel>() {

    private lateinit var mPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            viewModelStore,
            NoParameterViewModelFactory()
        ).get(WriteViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun observerViewModel() {
        with(mViewModel) {
            mPreferences = requireActivity().getSharedPreferences("LoginActivity", AppCompatActivity.MODE_PRIVATE)

            userId.value = mPreferences.getString("id", "").toString()

            mBinding.rvMaterialList.adapter = adapter
            mBinding.btnAddMaterial.setOnClickListener {

                setFragmentResultListener("bundle") { key, bundle ->
                    val material =
                        MaterialRequest(
                            bundle.getString("name", ""),
                            bundle.getString("url", "")
                        )
                    materials.value?.add(material)
                }
//
                findNavController().navigate(R.id.addMaterialFragment)

            }

            getWritePostRequest.observe(this@WriteFragment, {
                if (it) {
                    Toast.makeText(context, "성공적으로 작성하였습니다", Toast.LENGTH_SHORT).show()
                    title.value = ""
                    imgUrl.value = ""
                    summary.value = ""
                    content.value = ""
                    materials.value = arrayListOf()
                }
            })
            onErrorEvent.observe(this@WriteFragment, {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            })
            materials.observe(this@WriteFragment, {
                adapter.materialList = it
                adapter.notifyDataSetChanged()
            })

        }
    }
}


