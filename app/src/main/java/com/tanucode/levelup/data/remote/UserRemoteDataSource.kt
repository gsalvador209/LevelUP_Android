package com.tanucode.levelup.data.remote

import com.google.firebase.Firebase



import com.tanucode.levelup.data.db.entity.UserEntity
import kotlinx.coroutines.tasks.await

class UserRemoteDataSource(
    private val firestore: Firebase
) {
//    private val collection = firestore.collection("users")
//
//    suspend fun syncUser(user: UserEntity) {
//        collection
//            .document(user.id)
//            .set(user.toMap(), SetOptions.merge())
//            .await()
//    }
//
//    private fun UserEntity.toMap(): Map<String, Any?> = mapOf(
//        "name" to name,
//        "avatarUri" to avatarUri,
//        "goldCoins" to goldCoins,
//        "silverCoins" to silverCoins,
//        "registrationDate" to registrationDate
//    )
}
