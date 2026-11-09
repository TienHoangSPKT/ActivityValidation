package vn.udn.ute.week08.s2415141122106

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.udn.ute.week08.s2415141122106.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Khai báo biến binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKiemTra.setOnClickListener {
            binding.tvKetQua.text = ""
            binding.etTenHP.error = null
            binding.etQT.error = null
            binding.etCK.error = null

            val ten = binding.etTenHP.text.toString()
            val qtStr = binding.etQT.text.toString()
            val ckStr = binding.etCK.text.toString()

            var isValid = true

            if (!validateName(ten)) {
                binding.etTenHP.error = "Tên phải từ 1 đến 40 ký tự"
                isValid = false
            }

            // Sửa lỗi: Truyền đúng biến qtStr
            val qt = validateScore(qtStr)
            if (qt == null) {
                binding.etQT.error = "Điểm phải là số từ 0 đến 10"
                isValid = false
            }

            // Sửa lỗi: Viết đúng tên hàm validateScore
            val ck = validateScore(ckStr)
            if (ck == null) {
                binding.etCK.error = "Điểm phải là số từ 0 đến 10"
                isValid = false
            }

            // Sửa lỗi: Thêm cặp ngoặc nhọn { } cho khối if này
            if (isValid && qt != null && ck != null) {
                val tenTrimmed = ten.trim()
                val t = calculateT(qt, ck)
                val ketLuan = classify(t, ck)

                // Sửa lỗi: Đưa biến t ra ngoài dấu ngoặc kép
                val tFormatted = String.format("%.2f", t)

                // Sửa lỗi: Đóng dấu ngoặc kép và gọi đúng biến $ketLuan
                binding.tvKetQua.text = "Tên HP: $tenTrimmed\nĐiểm QT: $qt | Điểm CK: $ck\nTổng kết (T): $tFormatted\nKết luận: $ketLuan"
            }
        }

        // Sửa lỗi: Viết đúng setOnClickListener và setText("")
        binding.btnXoa.setOnClickListener {
            binding.etTenHP.setText("")
            binding.etQT.setText("")
            binding.etCK.setText("")
            binding.tvKetQua.text = ""
            binding.etTenHP.error = null
            binding.etQT.error = null
            binding.etCK.error = null
        }
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
        // Sửa lỗi: Thêm dấu * để nhân
        return 0.4 * qt + 0.6 * ck
    }

    private fun classify(t: Double, ck: Double): String {
        return if (t >= 5.0 && ck >= 3.0) {
            "Đạt"
        } else {
            "Chưa đạt"
        }
    }
}