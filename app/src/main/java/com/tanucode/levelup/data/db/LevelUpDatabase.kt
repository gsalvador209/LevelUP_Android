package com.tanucode.levelup.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tanucode.levelup.data.db.converter.DateTypeConverter
import com.tanucode.levelup.data.db.converter.UUIDTypeConverter
import com.tanucode.levelup.data.db.dao.ListDao
import com.tanucode.levelup.data.db.dao.TaskDao
import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.util.Constants

@Database(
    entities = [
            TaskEntity::class,
            ListEntity::class,
            UserEntity::class
               ],
    version = 6,
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