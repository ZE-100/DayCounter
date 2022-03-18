package com.daycounter.other.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daycounter.R
import com.daycounter.dataclass.Reminder
import androidx.navigation.Navigation.findNavController
import com.daycounter.fragment.RemindersFragmentDirections
import com.daycounter.other.enum.Constants

class RecyclerViewAdapter(private val context: Context?, remindersIn: List<Reminder>) :
    RecyclerView.Adapter<ReminderViewHolder>() {
    
    private val reminders: List<Reminder> = remindersIn
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {

        val recyclerViewItem: View = inflater
            .inflate(R.layout.item_recyclerview_layout, parent, false)

        recyclerViewItem.setOnClickListener {
            v -> onClick(parent as RecyclerView, v, context)
        }

        return ReminderViewHolder(recyclerViewItem)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder: Reminder = reminders[position]

        holder.titleView.text = reminder.title
        holder.descriptionView.text = reminder.description
        holder.thumbnailView.setImageResource(R.drawable.heart_shape)
        holder.dueDateView.text = String.format("%s", Constants.SDF.format(reminder.dueDate))
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    private fun onClick(recyclerView: RecyclerView, itemView: View, context: Context?) {
        val itemPos = recyclerView.getChildLayoutPosition(itemView)
        val reminder: Reminder = reminders[itemPos]

        findNavController(itemView).navigate(RemindersFragmentDirections.actionRemindersToPopup(itemPos))
    }
}