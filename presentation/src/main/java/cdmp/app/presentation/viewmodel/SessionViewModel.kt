package cdmp.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cdmp.app.domain.case.LogUserCase
import cdmp.app.domain.case.SubscribeToChannelCase
import cdmp.app.domain.model.UserLogin
import com.cdmp.rickmorty.domain.datatype.Either
import kotlinx.coroutines.launch

class SessionViewModel(
    private val loginCase: LogUserCase,
    private val subscribeToChatCase: SubscribeToChannelCase,
    scope: ViewModelScope
) : ViewModel(), ViewModelScope by scope {
    private val loggedUser = MutableLiveData<Either<String, Unit>>()

    val loggedUserDisplay: LiveData<Either<String, Unit>> = Transformations.map(loggedUser) {
        it
    }

    fun setUserFromAuth(user: UserLogin) {
        launch {
            subscribeToChatCase.subscribeToChannel().fold({
                print(it)
            },{
                print(it)
            })
            //            loginCase.logUser(user).fold(
//                {
//                    loggedUser.postValue(Either.left("An error happened, please try again"))
//                }, {
//                    loggedUser.postValue(Either.right(Unit))
//                }
//            )
        }
    }
}
