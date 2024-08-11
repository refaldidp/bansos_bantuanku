package com.sosial.bantuanku

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var profileImageView: ImageView
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Ambil data dari SharedPreferences
        val sharedPref = activity?.getSharedPreferences("ProfileData", Context.MODE_PRIVATE)
        val name = sharedPref?.getString("NAMA", "")
        val nik = sharedPref?.getString("NIK", "")
        val address = sharedPref?.getString("ALAMAT", "")
        val phone = sharedPref?.getString("TELEPON", "")
        val email = sharedPref?.getString("EMAIL", "")

        // Set data ke EditText
        val nameEditText = view.findViewById<EditText>(R.id.edit_name)
        val nikEditText = view.findViewById<EditText>(R.id.edit_nik)
        val addressEditText = view.findViewById<EditText>(R.id.edit_address)
        val phoneEditText = view.findViewById<EditText>(R.id.edit_phone)
        val emailEditText = view.findViewById<EditText>(R.id.edit_email)

        nameEditText.setText(name)
        nikEditText.setText(nik)
        addressEditText.setText(address)
        phoneEditText.setText(phone)
        emailEditText.setText(email)

        // Membuat teks tebal dan hitam
        val editTexts = listOf(nameEditText, nikEditText, addressEditText, phoneEditText, emailEditText)
        for (editText in editTexts) {
            editText.isEnabled = false
            editText.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black)) // Ubah warna teks menjadi hitam
            editText.setTypeface(null, Typeface.BOLD) // Ubah teks menjadi tebal
        }

        return view
    }
}


