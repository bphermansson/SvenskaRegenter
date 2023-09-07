package com.example.SvenskaRegenter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

// A DAO Interface — Includes all the possible Queries used in the Project, using Annotations.
@Dao
interface RegentDao {
    @Query("SELECT * FROM Regenter")
    //suspend fun getAllRegents(): List<Regent>
    fun getAllRegents(): List<Regent>

    @Query("SELECT * FROM Regenter where last_name = ''")
    fun getNoLastname(): List<Regent>

    @Query("SELECT * FROM Regenter WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Regent>

    @Query("SELECT * FROM Regenter WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Regent

    @Insert
    fun insertAll(vararg users: Regent)

    @Delete
    fun delete(user: Regent)

    @Query("SELECT * FROM Regenter")
    fun getNotes(): List<Regent>
}