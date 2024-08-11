package com.sosial.bantuanku

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class PengajuanFragment : Fragment() {

    // Deklarasi Firebase Firestore
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pengajuan, container, false)

        // Inisialisasi Firebase Firestore
        db = FirebaseFirestore.getInstance()

        val submitButton: Button = view.findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.edit_name).text.toString()
            val nik = view.findViewById<EditText>(R.id.edit_nik).text.toString()
            val address = view.findViewById<EditText>(R.id.edit_address).text.toString()
            val phone = view.findViewById<EditText>(R.id.edit_phone).text.toString()
            val email = view.findViewById<EditText>(R.id.edit_email).text.toString()
            val selectedProgram = view.findViewById<Spinner>(R.id.spinner_program).selectedItem.toString()

            if (name.isNotEmpty() && nik.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                // Buat HashMap untuk data yang akan disimpan
                val userData = hashMapOf(
                    "nama" to name,
                    "NIK" to nik,
                    "alamat" to address,
                    "notelp" to phone,
                    "email" to email,
                    "program" to selectedProgram
                )

                // Simpan data ke Firebase Firestore
                db.collection("pengajuan")
                    .add(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Pengajuan berhasil disimpan.", Toast.LENGTH_SHORT).show()

                        // Simpan data ke SharedPreferences
                        val sharedPref = activity?.getSharedPreferences("ProfileData", Context.MODE_PRIVATE)
                        sharedPref?.edit()?.apply {
                            putString("NAMA", name)
                            putString("NIK", nik)
                            putString("ALAMAT", address)
                            putString("TELEPON", phone)
                            putString("EMAIL", email)
                            apply()
                        }

                        // Kosongkan input EditText setelah data berhasil disimpan
                        view.findViewById<EditText>(R.id.edit_name).text.clear()
                        view.findViewById<EditText>(R.id.edit_nik).text.clear()
                        view.findViewById<EditText>(R.id.edit_address).text.clear()
                        view.findViewById<EditText>(R.id.edit_phone).text.clear()
                        view.findViewById<EditText>(R.id.edit_email).text.clear()

                        // Perbarui halaman profil (jika perlu, reload fragment)
                        // Implementasi ini bergantung pada bagaimana Anda mengatur navigasi antar fragment
                        val profileFragment = ProfileFragment()
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, profileFragment)
                            .commit()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Gagal menyimpan pengajuan: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Harap isi semua field.", Toast.LENGTH_SHORT).show()
            }
        }

        val infoButton: Button = view.findViewById(R.id.info_button)
        infoButton.setOnClickListener {
            val url = "https://www.detik.com/jogja/bisnis/d-7472586/daftar-bansos-cair-agustus-2024-ada-pkh-hingga-bantuan-beras-10-kg"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(intent)
        }

        return view
    }
}
