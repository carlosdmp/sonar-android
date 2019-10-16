package cdmp.app.sonar

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cdmp.app.presentation.viewmodel.SessionViewModel
import cdmp.app.sonar.view.fragment.ChatFragment
import cdmp.app.sonar.view.fragment.LoginFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val sessionViewModel by viewModel<SessionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()

        }
        sessionViewModel.loggedUserDisplay.observe(this, Observer {
            it.fold(
                {
                    findViewById<TextView>(R.id.tv_error).apply {
                        text = it
                        visibility = View.VISIBLE
                    }
                }, {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ChatFragment.newInstance())
                        .commitNow()
                }
            )
        })
    }
}
