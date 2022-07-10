package com.boratuzun.todoapp.room

import androidx.room.*
import com.boratuzun.todoapp.data.entity.Isler

@Dao
interface IslerDao {
    @Query("SELECT * FROM yapilacaklar")
    suspend fun tumKisiler() : List<Isler>

    @Insert
    suspend fun isEkle(kisi:Isler)

    @Update
    suspend fun isGuncelle(kisi:Isler)

    @Delete
    suspend fun isSil(kisi:Isler)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%' || :aramaKelimesi || '%'")
    suspend fun isArama(aramaKelimesi:String) : List<Isler>
}