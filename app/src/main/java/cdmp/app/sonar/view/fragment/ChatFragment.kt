package cdmp.app.sonar.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdmp.app.presentation.viewmodel.SessionViewModel
import cdmp.app.sonar.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private val sessionViewModel by sharedViewModel<SessionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)
//        view.findViewById<Button>(R.id.b_signin).setOnClickListener {
//            signIn()
//        }
        return view
    }
}
