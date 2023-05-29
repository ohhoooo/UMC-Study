package com.kjh.umc_project8.database

import androidx.room.*
import com.kjh.umc_project8.model.Memo

@Dao
interface MemoDao {

    @Insert
    fun insert(memo: Memo)

    @Delete
    fun delete(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Query("SELECT * FROM Memo")
    fun selectAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE memoId = :memoId")
    fun selectByMemoId(memoId: Int): Memo
}