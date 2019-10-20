package cdmp.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cdmp.app.domain.case.ObserveMessagesCase
import cdmp.app.domain.case.SendMessageCase
import com.cdmp.rickmorty.domain.datatype.Either
import kotlinx.coroutines.launch

class ChatViewModel(
    private val sendMessageCase: SendMessageCase,
    private val observeMessagesCase: ObserveMessagesCase,
    scope: ViewModelScope
) : ViewModel(), ViewModelScope by scope {

    private val loggedUser = MutableLiveData<Either<String, Unit>>()

    val loggedUserDisplay: LiveData<Either<String, Unit>> = Transformations.map(loggedUser) {
        it
    }

    fun sendMessage(messageText: String) {
        launch {
            val result = sendMessageCase.sendMessage(messageText).fold(
                {
                    loggedUser.postValue(Either.left("An error happened, please try again"))
                }, {
                    loggedUser.postValue(Either.right(Unit))
                }
            )
        }
    }

    fun subscribeToChat(){
        launch {
            val result = observeMessagesCase.getMessages().fold({
                loggedUser.postValue(Either.left("An error happened, please try again"))
            },{
                for(message in it){
                    print(it)
                }
            })
        }
    }
}