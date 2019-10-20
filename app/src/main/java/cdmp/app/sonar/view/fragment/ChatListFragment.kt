package cdmp.app.sonar.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdmp.app.presentation.viewmodel.ChatViewModel
import com.stfalcon.chatkit.messages.MessageInput
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChatListFragment : Fragment() {

    companion object {
        fun newInstance() = ChatListFragment()
    }

    private val chatViewModel by viewModel<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(cdmp.app.sonar.R.layout.chat_list_fragment, container, false)
        chatViewModel.subscribeToChat()
        view.findViewById<MessageInput>(cdmp.app.sonar.R.id.mi_chat_input).apply {
            setInputListener {
                chatViewModel.sendMessage(it.toString())
                return@setInputListener true
            }
            setTypingListener(object : MessageInput.TypingListener {
                override fun onStartTyping() {

                }

                override fun onStopTyping() {

                }
            })
        }
        return view
    }
}
