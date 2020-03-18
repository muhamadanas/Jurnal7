package org.d3if4026.diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.d3if2036.diary.database.Diary

@Dao
interface DiaryDatabaseDao {

    @Insert
    fun insert(night: Diary)

    @Update
    fun update(night: Diary)

    @Query("SELECT * FROM diary_table WHERE diaryId = :key")
    fun get(key:Long):Diary

    @Query("DELETE FROM diary_table")
    fun clear()

    @Query("SELECT * FROM diary_table ORDER BY diaryId DESC")
    fun getAllData(): LiveData<List<Diary>>

    @Query("SELECT * FROM diary_table ORDER BY diaryId DESC LIMIT 1")
    fun getData():Diary?
}