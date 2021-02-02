package com.example.a2021_02_memoapp.table_memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by ymKwon on 2021-02-02 오전 11:33.
 */

@Database(entities = arrayOf(MemoEntity::class), version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDAO() : MemoDAO

    // 데이터베이스를 만드는 작업은 리소스를 많이 사용하기 때문에
    // 앱 전체에서 객체를 한번만 생성할 수 있게 만들어야함
    // 싱글톤 패턴 구현
    companion object {
        var INSTANCE : MemoDatabase? = null

        fun getInstance(context : Context) : MemoDatabase? {
            if (INSTANCE == null) {
                synchronized(MemoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MemoDatabase::class.java, "memo.db")
                        .fallbackToDestructiveMigration() // 데이터베이스를 한번 생성해서 그 스키마로 그대로 가는 것이 아니라 데이터엔티티 수정이 있을 경우(버전을 올려야함), 현재는 데이터를 모두 삭제하는 방식
                        .build()
                }
            }

            return INSTANCE
        }

    }

}