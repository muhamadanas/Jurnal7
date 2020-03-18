package org.d3if4026.diary.diary

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*
import org.d3if2036.diary.database.Diary
import org.d3if4026.diary.ShowAllData
import org.d3if4026.diary.database.DiaryDatabaseDao

class DiaryViewModel(val database: DiaryDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModel = Job()

    private var uiScope = CoroutineScope(Dispatchers.Main + viewModel)
    private var dataDiary = MutableLiveData<Diary?>()
    private val listDiari = database.getAllData()

    val diaryString = Transformations.map(listDiari){
        ShowAllData(it)
    }



    override fun onCleared() {
        super.onCleared()
        viewModel.cancel()
    }


    init {
        initial()
    }

    private suspend fun inset(diary : Diary){
        withContext(Dispatchers.IO){
            database.insert(diary)
        }
    }

    fun onDelete(){
        uiScope.launch {
            delete()
            dataDiary.value = null
        }
    }

    private suspend fun delete(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    fun initial(){
        uiScope.launch {
            dataDiary.value =  getDiaryFromDatabase()
        }
    }

    private suspend fun getDiaryFromDatabase(): Diary? {
        return withContext(Dispatchers.IO){
            var diary = database.getData()
            if(diary?.tanggalDiary != diary?.isidiary){
                diary = null
            }
            diary
        }
    }


}