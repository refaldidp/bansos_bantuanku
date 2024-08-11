package com.sosial.bantuanku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PendaftarAdapter(private val pendaftarList: List<Pendaftar>) : RecyclerView.Adapter<PendaftarAdapter.PendaftarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendaftarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pendaftar_item, parent, false)
        return PendaftarViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendaftarViewHolder, position: Int) {
        val pendaftar = pendaftarList[position]
        holder.bind(pendaftar)
    }

    override fun getItemCount() = pendaftarList.size

    class PendaftarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.pendaftar_name)
        private val emailTextView: TextView = itemView.findViewById(R.id.pendaftar_email)
        private val NIKTextView: TextView = itemView.findViewById(R.id.pendaftar_NIK)
        private val programTextView: TextView = itemView.findViewById(R.id.pendaftar_program)

        fun bind(pendaftar: Pendaftar) {
            nameTextView.text = pendaftar.nama
            emailTextView.text = pendaftar.email
            NIKTextView.text = pendaftar.NIK
            programTextView.text = pendaftar.program
        }
    }
}
