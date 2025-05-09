package com.tanucode.levelup.data.repository
import androidx.lifecycle.LiveData
import com.tanucode.levelup.data.db.dao.ListDao
import com.tanucode.levelup.data.db.entity.ListEntity
import java.util.UUID

class ListRepository(private val listDao: ListDao) {

    suspend fun insertList(listEntity: ListEntity) {
        listDao.insertList(listEntity)
    }

    suspend fun updateList(listEntity: ListEntity) {
        listDao.updateList(listEntity)
    }

    suspend fun deleteList(listEntity: ListEntity) {
        listDao.deleteList(listEntity)
    }

    suspend fun getListById(listId: UUID): ListEntity? {
        return listDao.getListById(listId)
    }

    suspend fun getAllLists(): List<ListEntity> {
        return listDao.getAllLists()
    }

    fun getAllListsLive(): LiveData<List<ListEntity>> =
        listDao.getAllListsLive()
}
