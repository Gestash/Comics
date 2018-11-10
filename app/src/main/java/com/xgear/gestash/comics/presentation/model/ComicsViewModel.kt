package com.xgear.gestash.comics.presentation.model

import com.xgear.gestash.comics.net.Comics
import java.text.DateFormat
import java.util.*


data class ComicsViewModel(
        val date: String,
        val number: String,
        val imageUrl: String,
        val title: String
)

fun ComicsViewModel(comics: Comics): ComicsViewModel {

    val calendar = GregorianCalendar(comics.year, comics.month - 1, comics.day)
    val dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault())

    val date = dateFormat.format(calendar.time)
    val num = "#${comics.num}"

    return ComicsViewModel(
            date,
            num,
            comics.img,
            comics.title
    )
}

