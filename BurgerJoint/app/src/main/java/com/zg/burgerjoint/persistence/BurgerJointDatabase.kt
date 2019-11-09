package com.zg.burgerjoint.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.persistence.daos.BurgerDao

@Database(
    entities = [
        BurgerVO::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BurgerJointDatabase : RoomDatabase() {
    abstract fun getBurgerDao() : BurgerDao

    companion object {

        private const val DB_NAME = "BurgerJoint.db"

        @Volatile
        private var INSTANCE: BurgerJointDatabase? = null

        fun getInstance(context: Context): BurgerJointDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BurgerJointDatabase::class.java, DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }
}