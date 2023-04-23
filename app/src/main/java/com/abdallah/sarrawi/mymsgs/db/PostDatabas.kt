package com.abdallah.sarrawi.mymsgs.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abdallah.sarrawi.mymsgs.db.Dao.FavoriteDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsTypesDao
import com.abdallah.sarrawi.mymsgs.models.FavoriteModel
import com.abdallah.sarrawi.mymsgs.models.MsgsModel
import com.abdallah.sarrawi.mymsgs.models.MsgsTypesModel

@Database(entities = [MsgsTypesModel::class,MsgsModel::class,FavoriteModel::class], version =6, exportSchema = false)
abstract class PostDatabas : RoomDatabase() {

    abstract fun TypesDao():MsgsTypesDao
    abstract fun Msgs_Dao(): MsgsDao
    abstract fun FavoriteDao():FavoriteDao

    companion object{

        @Volatile
        private var instance :PostDatabas?=null

        fun getInstance(con:Context):PostDatabas{

            if(instance == null){
                instance = Room.databaseBuilder(con,PostDatabas::class.java,"PostDatabase.db")
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    })
                    .build()
            }

            return instance!!
        }
    }
}