package org.d3if4026.diary.diary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*
import org.d3if2036.diary.database.Diary
import org.d3if4026.diary.database.DiaryDatabaseDao

class DiaryCreateViewModel(val database: DiaryDatabaseDao,val diary: Diary, application: Application) : AndroidViewModel(application) {

    private var viewModel = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModel)

    override fun onCleared() {
        super.onCleared()
        viewModel.cancel()
    }
    fun onSave(){
        uiScope.launch {
            insert(diary)
        }
    }
    private suspend fun insert(diary : Diary){
        withContext(Dispatchers.IO){
            database.insert(diary)
        }
    }
}