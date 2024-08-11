package com.sosial.bantuanku

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    // Deklarasi Firebase Firestore
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi Firebase Firestore
        db = FirebaseFirestore.getInstance()

        // Inisialisasi BottomNavigationView dan fragment container
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val homeFragment = HomeFragment()
                    openFragment(homeFragment)
                    true
                }
                R.id.nav_pengajuan -> {
                    val pengajuanFragment = PengajuanFragment()
                    openFragment(pengajuanFragment)
                    true
                }
                R.id.nav_profile -> {
                    val profileFragment = ProfileFragment()
                    openFragment(profileFragment)
                    true
                }
                R.id.nav_pendaftar -> {
                    val pendaftarFragment = PendaftarFragment()
                    openFragment(pendaftarFragment)
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.nav_home
        }
    }

    // Fungsi untuk membuka fragment
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

