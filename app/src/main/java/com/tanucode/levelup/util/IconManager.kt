package com.tanucode.levelup.util

import com.tanucode.levelup.R

object IconManager {

    // Mapea los nombres que se guardan en la entidad de lista de la DB a recursos
    private val iconMap: Map<String, Int> = mapOf(
        "ic_inbox"  to R.drawable.ic_inbox,
        "ic_target" to R.drawable.ic_target,
        "ic_task_list" to R.drawable.ic_task_list,
        "ic_notes" to R.drawable.ic_notes,
        "ic_star"      to R.drawable.ic_star,
        "ic_flight" to R.drawable.ic_flight,
        "ic_book_1" to R.drawable.ic_book_1,
        "ic_book_2" to R.drawable.ic_book_2,
        "ic_excercise" to R.drawable.ic_exercise,
        "ic_work" to R.drawable.ic_work,
        "ic_self_improvement" to R.drawable.ic_self_improvement,
        "ic_favorite" to R.drawable.ic_favorite,
        "ic_payments" to R.drawable.ic_payments,
        "ic_wallet" to R.drawable.ic_wallet,
        "ic_fastfood" to R.drawable.ic_fastfood,
        "ic_movie" to R.drawable.ic_movie,
        "ic_gift" to R.drawable.ic_gift,
        "ic_code" to R.drawable.ic_code,
        "ic_lightbulb" to R.drawable.ic_lightbulb,
        "ic_forest" to R.drawable.ic_forest,
        "ic_pet" to R.drawable.ic_pet,
        "ic_plant"     to R.drawable.ic_plant,
        "ic_work"      to R.drawable.ic_work,
        "ic_coffee" to R.drawable.ic_coffee,
        "ic_reward" to R.drawable.ic_reward
    )


     // Devuelve el drawable asociado al iconName.

    fun getIconResId(iconName: String?): Int =
        iconMap[iconName] ?: 0
}
