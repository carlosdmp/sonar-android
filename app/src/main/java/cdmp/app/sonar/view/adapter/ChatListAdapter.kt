package cdmp.app.sonar.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cdmp.app.sonar.R

class ChatListAdapter : ListAdapter<ChatMessageDisplay, ChatThreadViewHolder>(
    object : DiffUtil.ItemCallback<ChatMessageDisplay>() {
        override fun areItemsTheSame(
            oldItem: ChatMessageDisplay,
            newItem: ChatMessageDisplay
        ): Boolean {
            return oldItem.message.userId == newItem.message.userId
        }

        override fun areContentsTheSame(
            oldItem: ChatMessageDisplay,
            newItem: ChatMessageDisplay
        ): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatThreadViewHolder {
        return ChatThreadViewHolder(
            View.inflate(
                parent.context,
                R.layout.chat_thread_viewholder,
                parent
            )
        )
    }

    override fun onBindViewHolder(holder: ChatThreadViewHolder, position: Int) {

    }

}