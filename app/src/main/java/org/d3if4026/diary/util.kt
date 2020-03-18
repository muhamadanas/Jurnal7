package org.d3if4026.diary

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import org.d3if2036.diary.database.Diary


fun ShowAllData(diaries : List<Diary>): Spanned {
    val sb = StringBuilder()

    sb.apply {
        diaries.forEach{
            append("${it.tanggalDiary}<br>")
            append("${it.isidiary}<br>")
            append("<br>")
            append("<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
