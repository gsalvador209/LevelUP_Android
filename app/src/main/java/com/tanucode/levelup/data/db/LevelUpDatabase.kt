package com.tanucode.levelup.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tanucode.levelup.data.db.converter.DateTypeConverter
import com.tanucode.levelup.data.db.converter.UUIDTypeConverter
import com.tanucode.levelup.data.db.dao.ListDao
import com.tanucode.levelup.data.db.dao.StatsDao
import com.tanucode.levelup.data.db.dao.TaskCompletionDao
import com.tanucode.levelup.data.db.dao.TaskDao
import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.db.entity.TaskCompletionEntity
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.data.local.dao.OwnedStickerDao
import com.tanucode.levelup.data.local.dao.ProductDao
import com.tanucode.levelup.data.local.dao.SpaceDao
import com.tanucode.levelup.data.local.entity.OwnedStickerEntity
import com.tanucode.levelup.data.local.entity.ProductEntity
import com.tanucode.levelup.data.local.entity.SpaceEntity
import com.tanucode.levelup.util.Constants

@Database(
    entities = [
            TaskEntity::class,
            ListEntity::class,
            UserEntity::class,
            TaskCompletionEntity::class,
            ProductEntity::class,
            SpaceEntity::class,
            OwnedStickerEntity::class
               ],
    version = 9,
    exportSchema = false
)

@TypeConverters(
    UUIDTypeConverter::class,
    DateTypeConverter::class
)

abstract class LevelUpDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
    abstract fun listDao() : ListDao
    abstract fun userDao() : UserDao
    abstract fun statsDao() : StatsDao
    abstract fun taskCompletionDao() : TaskCompletionDao
    abstract fun productDao() : ProductDao
    abstract fun spaceDao(): SpaceDao
    abstract fun ownedStickerDao() : OwnedStickerDao

    companion object {
        @Volatile
        private var INSTANCE: LevelUpDatabase? = null

        fun getDatabase(context: Context): LevelUpDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LevelUpDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}