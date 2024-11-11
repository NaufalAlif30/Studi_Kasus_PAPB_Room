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

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao //Menunjukkan bahwa ItemDao adalah DAO untuk Room. DAO adalah antarmuka yang berisi metode untuk mengakses atau memodifikasi data di dalam database.
interface ItemDao {
    //Digunakan untuk menambahkan Item ke dalam tabel serta menginstruksikan Room untuk mengabaikan (tidak menambahkan) item jika terjadi konflik, seperti data dengan id yang sudah ada.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //Menjadikan fungsi ini berjalan secara asinkron (digunakan dalam coroutines).
    suspend fun insert(item: Item)

    //Digunakan untuk memperbarui data Item yang sudah ada di dalam tabel berdasarkan id.
    @Update
    //Bersifat asinkron untuk mendukung operasi coroutines.
    suspend fun update(item: Item)

    //Menghapus Item dari tabel berdasarkan id.
    @Delete
    //Berjalan secara asinkron.
    suspend fun delete(item: Item)

    //Digunakan untuk mengambil satu Item dari tabel berdasarkan id.
    @Query("SELECT * from items WHERE id = :id")
    //Fungsi ini mengembalikan Flow<Item>, yang secara otomatis melacak dan memberikan data terbaru jika ada perubahan pada Item yang diambil.
    fun getItem(id: Int): Flow<Item>

    //Digunakan untuk mengambil semua Item dari tabel items, diurutkan berdasarkan name dalam urutan menaik.
    @Query("SELECT * from items ORDER BY name ASC")
    //Fungsi ini mengembalikan Flow<List<Item>>, yang memungkinkan aplikasi memantau perubahan data pada tabel items dan otomatis memperbarui daftar jika ada perubahan.
    fun getAllItems(): Flow<List<Item>>
}