package com.daycounter.other

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daycounter.blueprint.Reminder

class RecyclerViewAdapter(private val context: Context?, remindersIn: List<Reminder>) :
    RecyclerView.Adapter<ReminderViewHolder>() {
    
    private val reminders: List<Reminder> = remindersIn
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {

        val recyclerViewItem: View = mLayoutInflater.inflate(R.layout.recyclerview_item_layout, parent, false)

        recyclerViewItem.setOnClickListener {
            v -> handleRecyclerItemClick(parent as RecyclerView, v)
        }

        return ReminderViewHolder(recyclerViewItem)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val Reminder: Reminder = reminders[position]

        holder.titleView.text = Reminder.title
        holder.descriptionView.text = Reminder.description
        holder.thumbnailView.setImageResource(R.drawable.alert_dark_frame)
        holder.dueDateView.text = Reminder.dueDate.toString()
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    private fun handleRecyclerItemClick(recyclerView: RecyclerView, itemView: View) {
        val itemPosition = recyclerView.getChildLayoutPosition(itemView)
        val Reminder: Reminder = reminders[itemPosition]
        Toast.makeText(context, Reminder.title, Toast.LENGTH_LONG).show()
    }

}