package org.d3if4026.diary.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if2036.diary.database.Diary


@Database(entities = [Diary::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {

    abstract val diaryDatabaseDao : DiaryDatabaseDao
    companion object{

        @Volatile
        private var INSTANCE : DiaryDatabase? = null

        fun getInstance(context : Context) : DiaryDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,DiaryDatabase::class.java,
                        "sleep_history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}