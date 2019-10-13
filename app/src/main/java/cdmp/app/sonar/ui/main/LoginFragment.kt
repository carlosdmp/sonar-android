package cdmp.app.sonar.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import cdmp.app.model.User
import cdmp.app.presentation.viewmodel.SessionViewModel
import cdmp.app.sonar.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }
    private val mGoogleSignInClient by lazy { GoogleSignIn.getClient(checkNotNull(activity), gso) }

    private val RC_SIGN_IN: Int = 111

    companion object {
        fun newInstance() = LoginFragment()
    }

    val sessionViewModel by sharedViewModel<SessionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        view.findViewById<Button>(R.id.b_signin).setOnClickListener {
            signIn()
        }
        return view
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)!!
            sessionViewModel.setUserFromAuth(
                User(
                    id = "G-" + account.id,
                    email = "",
                    firstName = "",
                    lastName = "",
                    phone = "",
                    username = "",
                    userStatus = 0
                )
            )
            // Signed in successfully, show authenticated UI.
            // updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //  Log.w(FragmentActivity.TAG, "signInResult:failed code=" + e.statusCode)
            // updateUI(null)
        }

    }
}
