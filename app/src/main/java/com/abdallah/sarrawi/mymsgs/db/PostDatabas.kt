package com.abdallah.sarrawi.mymsgs.db


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import com.abdallah.sarrawi.mymsgs.db.Dao.FavoriteDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsDao
import com.abdallah.sarrawi.mymsgs.db.Dao.MsgsTypesDao
import com.abdallah.sarrawi.mymsgs.models.FavoriteModel
import com.abdallah.sarrawi.mymsgs.models.LocalDateTimeConverter
import com.abdallah.sarrawi.mymsgs.models.MsgsModel
import com.abdallah.sarrawi.mymsgs.models.MsgsTypesModel

@Database(entities = [MsgsTypesModel::class, MsgsModel::class, FavoriteModel::class], version = 1, exportSchema = false)
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
            return Room.databaseBuilder(context.applicationContext, PostDatabase::class.java, "PostDatabase.db")
//                .addMigrations(MIGRATION_6_7, MIGRATION_7_8,MIGRATION_8_9)

                .build()
        }

//        private val MIGRATION_6_7: Migration = object : Migration(6, 7) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Log.d("Migration", "Executing migration from version $startVersion to version $endVersion")
//
//                // تنفيذ عملية الترحيل هنا
//            }
//        }
//
//        private val MIGRATION_7_8: Migration = object : Migration(7, 8) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE msg_table ADD COLUMN createdAt String")
//                Log.d("Migration", "Executing migration from version $startVersion to version $endVersion")
//
//            }
//        }
//
//        private val MIGRATION_8_9: Migration = object : Migration(8, 9) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Log.d("Migration", "Executing migration from version $startVersion to version $endVersion")
//
//            }
//        }


    }
}


