package com.example.a2021_02_memoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2021_02_memoapp.OnDeleteListener
import com.example.a2021_02_memoapp.R
import com.example.a2021_02_memoapp.table_memo.MemoEntity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_memo.view.*


/**
 * Created by ymKwon on 2021-02-02 오후 1:38.
 */
class MyAdapter(
    val context: Context,
    var list: List<MemoEntity>,
    var onDeleteListener: OnDeleteListener
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    // 한칸 한칸을 감싸는 전체 holder 를 만들어주는 작업
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // LayoutInflater 레이아웃을 만들어주는 도구
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_memo, parent, false)

        return MyViewHolder(itemView)
    }


    // onCreateViewHolder 에서 만든 ViewHolder 가 넘어옴
    //  - 만든 틀과 내용을 합쳐주는(bind) 하는 역할
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // list = 1, 2, 3
        val memo = list[position]

        holder.memo.text = memo.memo
        holder.root.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                    onDeleteListener.onDeleteListener(memo)
                return true
            }
        })

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memo = itemView.itmMemo
        val root = itemView.root
    }


}