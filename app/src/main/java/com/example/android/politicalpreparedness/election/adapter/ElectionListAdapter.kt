package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.databinding.ListViewItemBinding
//import com.example.android.politicalpreparedness.databinding.ViewholderElectionBinding
import com.example.android.politicalpreparedness.network.models.Election


class ElectionListAdapter(private val clickListener: ElectionListener): ListAdapter<Election, ElectionListAdapter.ElectionViewHolder>(DiffCallback) {

    //TODO: Create ElectionViewHolder
    class ElectionViewHolder(private var binding: ListViewItemBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(election: Election){
            binding.election = election
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    // TODO: Create ElectionDiffCallback
    companion object DiffCallback : DiffUtil.ItemCallback<Election>(){
        override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
            return oldItem.id == newItem.id
        }
    }

    //TODO: Add companion object to inflate ViewHolder (from)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder(ListViewItemBinding.inflate((from(parent.context))))
    }

    //TODO: Bind ViewHolder
    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val election = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(election)
        }
        holder.bind(election)
    }

    //TODO: Create ElectionListener
    class ElectionListener(val clickListener: (election:Election)-> Unit){
        fun onClick(election: Election)= clickListener(election)
    }

}

