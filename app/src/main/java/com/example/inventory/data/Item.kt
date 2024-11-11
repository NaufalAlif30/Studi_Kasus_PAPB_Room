/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

/**
 * Digunakan untuk mengakses anotasi @Entity dan @PrimaryKey, yang merupakan
 * bagian dari library Room
 */
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */

@Entity(tableName = "items") //Mendefinisikan kelas Item sebagai entitas (tabel) dalam database Room.
data class Item( //Deklarasi kelas data Item. Kelas ini merepresentasikan baris atau record tunggal dalam tabel items.
    @PrimaryKey(autoGenerate = true)
    //Menandakan bahwa id adalah kunci utama untuk entitas Item, dan Room akan menghasilkan nilai id secara otomatis setiap kali data baru dimasukkan.

    val id: Int = 0,
    //Mengindikasikan bahwa id memiliki tipe Int dengan nilai default 0.

    val name: String,
    //name adalah kolom yang merepresentasikan nama Item di tabel items dan bertipe String

    val price: Double,
    //price merepresentasikan harga dari Item dan bertipe Double.

    val quantity: Int
    //quantity merepresentasikan jumlah Item yang tersedia dan bertipe Int.
)