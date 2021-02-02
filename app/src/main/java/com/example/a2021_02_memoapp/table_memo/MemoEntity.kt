package com.example.a2021_02_memoapp.table_memo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ymKwon on 2021-02-02 오전 11:29.
 */

// Room database 에서는 anotation을 사용해서 어떤 것인지 알려줌
@Entity(tableName = "memo")
data class MemoEntity(
    // 프라이머리키 항상 적어 줘야함
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var memo : String = ""
)