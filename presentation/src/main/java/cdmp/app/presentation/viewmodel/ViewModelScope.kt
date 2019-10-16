package cdmp.app.presentation.viewmodel

import kotlinx.coroutines.*

interface ViewModelScope : CoroutineScope {

    val uiDispatcher: CoroutineDispatcher

    val job: Job
}

fun viewModelScope() = object : ViewModelScope {

    override val uiDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    override val job = Job()
    override val coroutineContext by lazy { uiDispatcher + job }
}

@ExperimentalCoroutinesApi
fun testScope() = object : ViewModelScope {
    override val uiDispatcher: CoroutineDispatcher
        get() = Dispatchers.Unconfined

    override val job = Job()
    override val coroutineContext by lazy { uiDispatcher + job }
}