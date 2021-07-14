package kr.hs.dgsw.mmr.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.mmr.R

class AddMaterialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_metrial, container, false)

        val etName = view.findViewById<EditText>(R.id.et_material_name)
        val etUrl = view.findViewById<EditText>(R.id.et_material_url)

        view.findViewById<AppCompatButton>(R.id.btn_complete_material).setOnClickListener {
            if (!etName.text.isNullOrBlank() && !etName.text.isNullOrBlank()) {
                setFragmentResult(
                    "bundle",
                    bundleOf("name" to etName.text.toString(), "url" to etUrl.text.toString())
                )
                findNavController().navigateUp()
            }
        }

        return view.rootView
    }


}