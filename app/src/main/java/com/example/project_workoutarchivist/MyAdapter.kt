package com.example.project_workoutarchivist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_memo.view.*

class MyAdapter(val context : Context, var list : List<MemoEntity>) : RecyclerView.Adapter<MyAdapter.MyViewHolder> () {

    override fun getItemCount(): Int {
        return list.size
    }

    // 한칸 한칸을 감싸는 전체 holder를 만들어주는 작업
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // LayoutInflater 레이아웃을 만들어주는 도구
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_memo, parent,false)

        return MyViewHolder(itemView)
    }


    // onCreateViewHolder 에서 만든 ViewHolder 가 넘어옴
    //  - 만든 틀과 내용을 합쳐주는(bind) 하는 역할
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // list = 1, 2, 3
        val memo = list[position]

        holder.memo.text = memo.memo
        holder.root.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {

                return true
            }
        })

    }


    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val memo = itemView.tvMemo
        val root = itemView.root
    }



}