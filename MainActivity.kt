package com.example.latihan1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var inputText: TextInputEditText
    private lateinit var resultText: EditText
    private lateinit var messageText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi elemen UI
        inputText = findViewById(R.id.inputText)
        resultText = findViewById(R.id.editTextText3)
        messageText = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)

        // Mengatur padding untuk sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set listener untuk tombol
        button.setOnClickListener { handleClick() }
    }

    @SuppressLint("SetTextI18n")
    private fun handleClick() {
        val inputTextValue = inputText.text.toString().trim()
        if (inputTextValue.isEmpty()) {
            messageText.text = "Masukkan jumlah IDR terlebih dahulu!"
            resultText.setText("USD")
            return
        }

        try {
            val rate = 1.0 / 16000 // 16000 IDR = 1 USD
            val idrAmount = inputTextValue.toDouble()
            val usdAmount = idrAmount * rate

            val df = DecimalFormat("#.##")
            resultText.setText("${df.format(usdAmount)} USD") // Menampilkan hasil + USD
            messageText.text = "Konversi Berhasil!" // Kosongkan pesan jika konversi berhasil
        } catch (e: NumberFormatException) {
            messageText.text = "Input tidak valid!"
            resultText.setText("USD")
        }
    }



}
