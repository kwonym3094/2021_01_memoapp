package com.example.a2021_02_memoapp

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2021_02_memoapp.adapter.MyAdapter
import com.example.a2021_02_memoapp.table_memo.MemoDatabase
import com.example.a2021_02_memoapp.table_memo.MemoEntity
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity(), OnDeleteListener {

    lateinit var db : MemoDatabase
    var memoList = listOf<MemoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MemoDatabase.getInstance(this)!!

        btnAdd.setOnClickListener {
            val memo = MemoEntity(null,txtMemo.text.toString())
            insertMemo(memo)
            txtMemo.setText("")
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        getAllMemo()

    }

    fun insertMemo(memo : MemoEntity){
        // 1. MainThread vs WorkerThread(Background Thread)
        //  - UI 관련된 일은 MainThread
        //  - 데이터 관련된 통신은 WorkerThread
        // coroutine 을 사용하면 쉬우나 이번에는 coroutine 이전의 AsyncTask를 이용해봄
        // AsyncTask << 백그라운드에 한번 보내준다

        // 다음과 같은 annotation 을 붙이지 않으면 노란줄로 보임
        //  - android 에서는 lint 로 메모리 누수를 방지하는데
        //    AsyncTask로 메모리누수가 발생할 수 있어서 노란줄로 보임
        //  - @SuppressLint 이라는 annotation 을 붙여서 lint 을 억제함
        //  - 밑에도 계속 AsyncTask 를 사용하기 때문에 클래스 차원에서 (위 확인) 전체 적용
        val insertTask = object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().insert(memo)
            }

            // doInBackground가 끝나면 뭘해야하는가?
            //  - 데이터가 잘들어갔는지 recyclerView에서 보여줘야함
            //  - 그럴때 사용할 수 있는 메소드 : onPostExecute

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemo()
            }
        }
        insertTask.execute()
    }

    fun getAllMemo(){
        val getTask = object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(memoList)
            }
        }
        getTask.execute()
    }

    fun deleteMemo(memo : MemoEntity) {
        val deleteTask = object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().delete(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemo()
            }
        }
        deleteTask.execute()
    }

    fun setRecyclerView(memoList : List<MemoEntity>){
        recyclerView.adapter = MyAdapter(this,memoList,this)
    }

    override fun onDeleteListener(memo: MemoEntity) {
        deleteMemo(memo)
    }

}