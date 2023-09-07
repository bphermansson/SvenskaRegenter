package com.example.SvenskaRegenter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// A Data Class — Defining the table and all the fields for the table
@Entity(tableName = "Regenter")
data class Regent(
     @PrimaryKey(autoGenerate = true) var uid: Int,
     @ColumnInfo(name = "first_name") var firstName: String?,
     @ColumnInfo(name = "last_name") var lastName: String?,
     @ColumnInfo(name = "StartYear") var StartYear: Int,
     @ColumnInfo(name = "StopYear") var StopYear: Int,
     @ColumnInfo(name = "InfoText") var InfoText: String
    )