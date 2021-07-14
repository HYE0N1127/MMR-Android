package kr.hs.dgsw.mmr.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.view.adapter.SavedMaterialAdapter


class StoreMaterialActivity : AppCompatActivity() {
    private val STRING = "&||&"
    private val STRING2 = "*()*"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_material)

        val adapter = SavedMaterialAdapter()

        findViewById<RecyclerView>(R.id.rv_saved_material_list).adapter = adapter

        val string = getSharedPreferences("LOGIN_ACTIVITY", MODE_PRIVATE).getString("material", "")

        string?.split(STRING)?.forEach {
            val strings = it.split(STRING2)
            if (strings.size == 2) adapter.materialList.add(MaterialRequest(strings[0], strings[1]))
        }

        adapter.notifyDataSetChanged()

        findViewById<AppCompatButton>(R.id.btn_save_material).setOnClickListener {
            if (adapter.materialList.isNotEmpty()) {
                val result = adapter.materialList.map { "${it.name}$STRING2${it.url}" }
                    .reduce { s1, s2 -> "$s1$STRING$s2" }

                getSharedPreferences("LOGIN_ACTIVITY", MODE_PRIVATE).edit()
                    .putString("material", result).apply()
            } else {
                getSharedPreferences("LOGIN_ACTIVITY", MODE_PRIVATE).edit()
                    .putString("material", "").apply()
            }
            Toast.makeText(this, "저장하였습니다", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}