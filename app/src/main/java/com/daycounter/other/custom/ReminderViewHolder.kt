package com.daycounter.other.custom

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daycounter.R
import java.util.*

class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var titleView: TextView = itemView.findViewById(R.id.reminderTitle) as TextView
    var descriptionView: TextView = itemView.findViewById(R.id.reminderDescription) as TextView
    var thumbnailView: ImageView = itemView.findViewById(R.id.reminderThumbnail) as ImageView
    var dueDateView: TextView = itemView.findViewById(R.id.reminderDate) as TextView
}