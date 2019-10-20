package cdmp.app.sonar

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import cdmp.app.presentation.viewmodel.SessionViewModel
import cdmp.app.sonar.view.fragment.LoginFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val sessionViewModel by viewModel<SessionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
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
                    startActivity(Intent(this, MainActivity::class.java))
                }
            )
        })
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1
        )
    }
}
