package kr.hs.dgsw.mmr.view.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.databinding.FragmentDeleteDialogBinding
import kr.hs.dgsw.mmr.utils.SingleLiveEvent

class DeleteDialogFragment : DialogFragment() {

//    val onDeleteEvent = SingleLiveEvent<Unit>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding = DataBindingUtil.inflate<FragmentDeleteDialogBinding>(
//            inflater,
//            R.layout.fragment_delete_dialog,
//            container,
//            false
//        )
//
//        binding.btnDialogCancle.setOnClickListener {
//            Log.d("MYTAG", "취소버튼 클릭")
//            dismiss()
//        }
//
//        binding.btnDialogDelete.setOnClickListener {
//            Log.d("MYTAG", "삭제버튼 클릭")
//            onDeleteEvent.call()
//            dismiss()
//
//        }
//
//        return binding.root
//    }

}