package com.example.gestash.comics

import java.text.DateFormat
import java.util.*


data class ComicsViewModel(
        val date: String,
        val num: String,
        val img: String,
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

