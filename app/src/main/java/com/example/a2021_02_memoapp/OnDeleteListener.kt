package com.example.a2021_02_memoapp

import com.example.a2021_02_memoapp.table_memo.MemoEntity

/**
 * Created by ymKwon on 2021-02-02 오후 2:33.
 */

// interface : activity, activity 간의 통신이 가능하게 해주는 장치
interface OnDeleteListener {
    fun onDeleteListener(memo: MemoEntity)
}