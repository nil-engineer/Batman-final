package com.test.batman.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.test.batman.model.RatingModel
import com.test.batman.model.SearchItemModel
import com.test.batman.model.VideoDetailModel

@Database(
    entities = [SearchItemModel::class, VideoDetailModel::class, RatingModel::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(RatingTypeConverter::class)

abstract class BatmanRoomDatabase : RoomDatabase() {
    @Volatile
    private var INSTANCE: BatmanRoomDatabase? = null

    abstract fun searchItemModelDao(): SearchItemModelDao?
    abstract fun videoDetailModelDao(): VideoDetailModelDao?
    abstract fun ratingModelDao(): RatingModelDao?

    companion object {
        private var INSTANCE: BatmanRoomDatabase? = null
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { PopulateDbAsync(
                    it
                ).execute() }
            }
        }
        fun getDatabase(context: Context): BatmanRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(BatmanRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BatmanRoomDatabase::class.java,
                            "batman.db"
                        )
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    private class PopulateDbAsync internal constructor(db: BatmanRoomDatabase) :
        AsyncTask<Void?, Void?, Void?>() {
        private val searchItemModelDao: SearchItemModelDao
//        protected override fun doInBackground(vararg params: Void): Void? {
////            transactionDao.deleteAll();
////            transactionDao.insert();
////            TransactionModel transaction = new TransactionModel(1, 1, 5000, "09123456789");
////            transactionDao.insert(transaction);
////            transaction = new TransactionModel(2,1,2000, "09123456789");
////            transactionDao.insert(transaction);
//            return null
//        }

        init {
            searchItemModelDao = db.searchItemModelDao()!!
        }

        override fun doInBackground(vararg params: Void?): Void? {
            return null
        }
    }

}