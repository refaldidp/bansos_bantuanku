package com.sosial.bantuanku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class PendaftarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pendaftarAdapter: PendaftarAdapter
    private lateinit var pendaftarList: MutableList<Pendaftar>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pendaftar, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_pendaftar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        pendaftarList = mutableListOf()
        pendaftarAdapter = PendaftarAdapter(pendaftarList)
        recyclerView.adapter = pendaftarAdapter

        fetchPendaftarData()

        return view
    }

    private fun fetchPendaftarData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("pengajuan")
            .get()
            .addOnSuccessListener { result ->
                pendaftarList.clear()
                for (document in result) {
                    val pendaftar = document.toObject(Pendaftar::class.java)
                    pendaftarList.add(pendaftar)
                }
                pendaftarAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
