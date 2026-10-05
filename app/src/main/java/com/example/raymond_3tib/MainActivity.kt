package com.example.raymond_3tib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.raymond_3tib.databinding.ActivityMainBinding
import com.example.raymond_3tib.pertemuan5.LimaActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val user=intent.getStringExtra("username")
        val pass=intent.getStringExtra("password")
        val umur=intent.getIntExtra("umur",0)


        Log.e("Hasil","Username: $user Password: $pass Umur: $umur")

        binding.txtUsername.text=user
        binding.txtPassword.setText(pass)

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Kembali ke Login Page?",
                Snackbar.LENGTH_LONG)
                .setAction("pindah") {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Yakin Mau logout?")
                        .setMessage("Anda perlu login kembali untuk mengakses tampilan ini lagi.")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Logout") { dialog, _ ->
                            finish()
                            // proses hapus
                            dialog.dismiss()
                        }
                        .setCancelable(false)
                        .show()
                }
                .show()
        }
        binding.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak " +
                        "bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.btnKembali.setOnClickListener {
//            val intent= Intent(this, LoginActivity::class.java)
 //           startActivity(intent)
            finish()
        }

        binding.btnToLima.setOnClickListener {
            val intent= Intent(this, LimaActivity::class.java)
            startActivity(intent)
        }
    }
}