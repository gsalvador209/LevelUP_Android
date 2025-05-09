package com.tanucode.levelup.util

import android.content.Context
import com.tanucode.levelup.R
import com.tanucode.levelup.data.db.entity.ListEntity

object ListNameResolver {

    //Mapeo para las listas del sistema
    private val systemNameMap = mapOf(
        "inbox" to R.string.list_inbox,
        "objectives" to R.string.list_objectives
    )

    fun resolve(context: Context, list: ListEntity): String =
        if (list.type == "system"){
            val resId = systemNameMap[list.systemKey]
            if (resId != null) context.getString(resId)
            else ""
        } else  {
         list.customName ?: ""
        }

}