package cdmp.app.sonar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cdmp.app.sonar.view.fragment.ChatListFragment

class MainActivity : AppCompatActivity() {

    //private val sessionViewModel by viewModel<SessionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatListFragment.newInstance())
                .commitNow()

        }
    }
}
