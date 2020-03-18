package org.d3if2036.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "diary_table")
data class Diary(

    @PrimaryKey(autoGenerate = true)
    var diaryId : Long = 0L,

    @ColumnInfo(name = "tanggal_diary")
    var tanggalDiary : String = "",

    @ColumnInfo(name = "isi_diary")
    var isidiary : String = ""
)