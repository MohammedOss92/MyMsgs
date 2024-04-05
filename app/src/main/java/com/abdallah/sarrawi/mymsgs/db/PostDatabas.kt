package com.abdallah.sarrawi.mymsgs.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_7_8
import com.abdallah.sarrawi.mymsgs.db.Dao.FavoriteDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsTypesDao
import com.abdallah.sarrawi.mymsgs.models.FavoriteModel
import com.abdallah.sarrawi.mymsgs.models.LocalDateTimeConverter
import com.abdallah.sarrawi.mymsgs.models.MsgsModel
import com.abdallah.sarrawi.mymsgs.models.MsgsTypesModel

@Database(entities = [MsgsTypesModel::class, MsgsModel::class, FavoriteModel::class], version = 8, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class PostDatabase : RoomDatabase() {

    abstract fun typesDao(): MsgsTypesDao
    abstract fun msgsDao(): MsgsDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var instance: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PostDatabase {
            return Room.databaseBuilder(context, PostDatabase::class.java, "PostDatabase.db")
                .addMigrations(MIGRATION_6_7,MIGRATION_7_8)
                .build()
        }

        val MIGRATION_6_7: Migration = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // تنفيذ عملية الترحيل هنا
                database.execSQL("ALTER TABLE msg_table ADD COLUMN createdAt INTEGER")

            }
        }
            val MIGRATION_7_8:Migration = object : Migration(7,8){
                override fun migrate(database: SupportSQLiteDatabase) {

                }

            }
        }
    }


/*
* import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abdallah.sarrawi.mymsgs.db.Dao.FavoriteDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsTypesDao
import com.abdallah.sarrawi.mymsgs.models.FavoriteModel
import com.abdallah.sarrawi.mymsgs.models.LocalDateTimeConverter
import com.abdallah.sarrawi.mymsgs.models.MsgsModel
import com.abdallah.sarrawi.mymsgs.models.MsgsTypesModel

@Database(entities = [MsgsTypesModel::class,MsgsModel::class,FavoriteModel::class], version =7, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
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
    }*/