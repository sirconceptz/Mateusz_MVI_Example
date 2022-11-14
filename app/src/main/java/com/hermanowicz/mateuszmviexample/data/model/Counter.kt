package com.hermanowicz.mateuszmviexample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Counter(
    @PrimaryKey(autoGenerate = true) val id: Int,
)