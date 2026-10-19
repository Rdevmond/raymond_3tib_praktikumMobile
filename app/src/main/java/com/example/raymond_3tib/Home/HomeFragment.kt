package com.example.raymond_3tib.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.raymond_3tib.Home.pertemuan5.LimaActivity
import com.example.raymond_3tib.Home.pertemuan6.SixthActivity
import com.example.raymond_3tib.LoginActivity
import com.example.raymond_3tib.R
import com.example.raymond_3tib.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val user=requireActivity().intent.getStringExtra("username")
        val pass=requireActivity().intent.getStringExtra("password")
        val umur=requireActivity().intent.getIntExtra("umur",0)


        Log.e("Hasil","Username: $user Password: $pass Umur: $umur")

        binding.txtUsername.text=user
        binding.txtPassword.setText(pass)

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Kembali ke Login Page?",
                Snackbar.LENGTH_LONG)
                .setAction("pindah") {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Yakin Mau logout?")
                        .setMessage("Anda perlu login kembali untuk mengakses tampilan ini lagi.")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Logout") { dialog, _ ->
                            requireActivity().finish()
                            // proses hapus
                            dialog.dismiss()
                        }
                        .setCancelable(false)
                        .show()
                }
                .show()
        }
        binding.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
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
            requireActivity().finish()
        }

        binding.btnToLima.setOnClickListener {
            val intent= Intent(requireContext(), LimaActivity::class.java)
            startActivity(intent)
        }
        binding.btnToEnam.setOnClickListener {
            val intent= Intent(requireContext(), SixthActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    val intent= Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}