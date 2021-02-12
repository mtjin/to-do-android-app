package com.mtjin.todoapp.utils

import android.text.format.DateFormat
import java.util.*

//현재 타임스템프 얻기
fun getTimestamp() = Calendar.getInstance(Locale.KOREA).timeInMillis

//타임스탬프 -> 날짜 시간:분 (2021.01.07 08:23)
fun Long.convertDateTimeMinute(): String =
    DateFormat.format("yyyy.MM.dd HH:mm", this).toString()