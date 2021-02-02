package com.example.a2021_02_memoapp.table_memo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * Created by ymKwon on 2021-02-02 오전 11:30.
 */

// DAO Data Access Object
// 실질적으로 Database의 insert, delete를 수행
@Dao
interface MemoDAO {

    @Insert(onConflict = REPLACE)
    fun insert(memo : MemoEntity)

    @Query("SELECT * FROM memo")
    fun getAll() : List<MemoEntity>

    @Delete
    fun delete(memo : MemoEntity)

}