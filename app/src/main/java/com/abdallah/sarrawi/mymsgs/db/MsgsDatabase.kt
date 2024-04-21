package com.abdallah.sarrawi.mymsgs.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdallah.sarrawi.mymsgs.db.Dao.FavoriteDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsTypesDao
import com.abdallah.sarrawi.mymsgs.models.FavoriteModel
import com.abdallah.sarrawi.mymsgs.models.LocalDateTimeConverter
import com.abdallah.sarrawi.mymsgs.models.MsgsModel
import com.abdallah.sarrawi.mymsgs.models.MsgsTypesModel

@Database(entities = [MsgsTypesModel::class, MsgsModel::class, FavoriteModel::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class MsgsDatabase : RoomDatabase() {

    abstract fun typesDao(): MsgsTypesDao
    abstract fun msgsDao(): MsgsDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var instance: MsgsDatabase? = null

        fun getInstance(context: Context): MsgsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MsgsDatabase {
            return Room.databaseBuilder(context.applicationContext, MsgsDatabase::class.java, "MsgsDatabase.db")

                .build()
        }


    }
}

