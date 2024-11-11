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

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [Item::class], version = 1, exportSchema = false) mendefinisikan InventoryDatabase sebagai database Room.
//entities = [Item::class] menunjukkan bahwa database ini memiliki satu entitas, yaitu Item.
//version = 1 adalah versi database, yang harus ditingkatkan jika ada perubahan struktur.
//exportSchema = false digunakan untuk tidak menyimpan skema database dalam file JSON.
@Database(entities = [Item::class], version = 1, exportSchema = false)
//Mendefinisikan kelas InventoryDatabase sebagai kelas RoomDatabase. RoomDatabase adalah kelas dasar untuk database Room, yang menyediakan koneksi ke database.
abstract class InventoryDatabase : RoomDatabase() {
    //Mendeklarasikan fungsi abstrak untuk mengakses ItemDao. Room akan mengimplementasikan fungsi ini secara otomatis.
    abstract fun itemDao(): ItemDao

    //Companion object digunakan untuk mengimplementasikan pola singleton, memastikan hanya ada satu instance InventoryDatabase yang digunakan di seluruh aplikasi.
    companion object{
        //Mendeklarasikan variabel Instance untuk menampung instance database. @Volatile memastikan perubahan pada Instance dapat langsung dilihat oleh semua thread.
        @Volatile
        private var Instance: InventoryDatabase? = null

        //fun getDatabase(context: Context): InventoryDatabase adalah fungsi untuk mengakses instance InventoryDatabase.
        //Menggunakan synchronized(this) untuk memastikan bahwa hanya satu instance yang dibuat secara bersamaan oleh beberapa thread.
        //Room.databaseBuilder(...) digunakan untuk membangun database dengan nama "item_database". Jika instance belum ada, Room akan membuatnya, dan Instance akan diperbarui untuk menampung instance yang baru dibuat.
        fun getDatabase(context: Context): InventoryDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}