package com.daycounter.other

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daycounter.R
import java.util.*

class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titleView: TextView
    var descriptionView: TextView
    var thumbnailView: ImageView
    var dueDateView: TextView

    // @itemView: recyclerview_item_layout.xml
    init {
        titleView = itemView.findViewById(R.id.reminderTitle) as TextView
        descriptionView = itemView.findViewById(R.id.reminderDescription) as TextView
        thumbnailView = itemView.findViewById(R.id.reminderThumbnail) as ImageView
        dueDateView = itemView.findViewById(R.id.reminderDate) as TextView
    }
}