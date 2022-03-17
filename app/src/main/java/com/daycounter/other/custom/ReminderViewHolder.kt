package com.daycounter.other.custom

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daycounter.R

class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var titleView: TextView = itemView.findViewById(R.id.popupTitle) as TextView
    var descriptionView: TextView = itemView.findViewById(R.id.popupDescription) as TextView
    var thumbnailView: ImageView = itemView.findViewById(R.id.popupThumbnail) as ImageView
    var dueDateView: TextView = itemView.findViewById(R.id.popupDate) as TextView
}