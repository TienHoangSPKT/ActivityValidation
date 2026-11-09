package vn.udn.ute.week08.s2415141122106

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTenHP = findViewById<EditText>(R.id.etTenHP)
        val etQT = findViewById<EditText>(R.id.etQT)
        val etCK = findViewById<EditText>(R.id.etCK)
        val btnKiemTra = findViewById<Button>(R.id.btnKiemTra)
        val tvKetQua = findViewById<TextView>(R.id.tvKetQua)
        val btnXoa = findViewById<Button>(R.id.btnXoa)

            btnKiemTra.setOnClickListener {
                tvKetQua.text = ""
                etTenHP.error = null
                etQT.error = null
                etCK.error = null

                val ten = etTenHP.text.toString()
                val qtStr = etQT.text.toString()
                val ckStr = etCK.text.toString()

                var isValid = true

                if (!validateName(ten)) {
                    etTenHP.error = "Tên phải từ 1 đến 40 ký tự"
                    isValid = false
                }

                val qt = validateScore(ckStr)
                if (qt == null) {
                    etQT.error = "Điểm phải là số từ 0 đến 10"
                    isValid = false
                }

                val ck = validateScor(ckStr)
                if (ck == null) {
                    etCK.error = "Điểm phải là số từ 0 đến 10"
                    isValid = false
                }

                if (isValid && qt != null && ck != null)
                    val tenTrimmed = ten.trim()
                    val t = calculateT(qt,ck)
                    val ketLuan = classify(t, ck)

                val tFormatted = String.format("%.2f, t")

                tvKetQua.text = "Tên HP: $tenTrimmed\nĐiểm QT: $qt | Điểm CK: $ck\nTổng kết (T): $tFormatted\nKết luận: $KetQua
            }
        }

        btnXoa.setOnclickListener {
            etTenHP.setText{""}
            etQT.setText("")
            etCK.setText("")
            tvKetQua.text = ""
            etTenHP.error = null
            etQT.error = null
            etCK.error = null
        }

    private fun validateName(name: String): Boolean {
        val trimmed = name.trim()
        return trimmed.isNotEmpty() && trimmed.length <= 40
    }

    private fun validateScore(scoreStr: String): Double? {
        val score = scoreStr.toDoubleOrNull()
        if (score != null && score >= 0.0 && score <= 10.0) {
            return score
        }
        return null
    }

    private fun calculateT(qt: Double, ck: Double): Double {
        return 0.4 * qt + 0.6 * ck
    }

    private fun classify(t: Double, ck: Double): String {
        return if ( t >= 5.0 && ck >= 3.0) {
            "Đạt"
        } else {
            "Chưa đạt"
        }
    }
}