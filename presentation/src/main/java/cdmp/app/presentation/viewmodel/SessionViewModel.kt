package cdmp.app.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cdmp.app.model.User

class SessionViewModel : ViewModel() {
    private val loggedUser = MutableLiveData<User>()

    val loggedUserDisplay = Transformations.map(loggedUser) {
        it
    }

    fun setUserFromAuth(user: User) {
        loggedUser.postValue(user)
    }
}
