package com.daycounter.other.custom

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.daycounter.R
import com.daycounter.dataclass.Reminder
import com.daycounter.fragment.popup.PopupItemFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.daycounter.dataclass.Counter


class RecyclerViewAdapter(private val context: Context?, remindersIn: List<Reminder>) :
    RecyclerView.Adapter<ReminderViewHolder>() {
    
    private val reminders: List<Reminder> = remindersIn
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {

        val recyclerViewItem: View = inflater.inflate(R.layout.item_recyclerview_layout, parent, false)

        recyclerViewItem.setOnClickListener {
            v -> onClick(parent as RecyclerView, v, context)
        }

        return ReminderViewHolder(recyclerViewItem)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder: Reminder = reminders[position]

        holder.titleView.text = reminder.title
        holder.descriptionView.text = reminder.description
        holder.thumbnailView.setImageResource(R.drawable.wallpuper)
        holder.dueDateView.text = reminder.dueDate.toString()
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    private fun onClick(recyclerView: RecyclerView, itemView: View, context: Context?) {
        val itemPos = recyclerView.getChildLayoutPosition(itemView)
        val reminder: Reminder = reminders[itemPos]

        val intent = Intent(context, PopupItemFragment::class.java)
        intent.putExtra("popupTitle", reminder.title)
        intent.putExtra("popupDescription", reminder.description)
        intent.putExtra("popupThumbnail", reminder.thumbnail)
        intent.putExtra("popupDueDate", reminder.thumbnail)
        context!!.startActivity(intent)

        findNavController(itemView).navigate(R.id.action_reminders_to_popup)
    }

}