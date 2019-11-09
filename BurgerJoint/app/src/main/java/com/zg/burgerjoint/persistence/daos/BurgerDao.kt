package com.zg.burgerjoint.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zg.burgerjoint.data.vos.BurgerVO

@Dao
interface BurgerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBurgers(burgers : List<BurgerVO>)

    @Query("SELECT * FROM burgers")
    fun getAllBurgers() : LiveData<List<BurgerVO>>

    @Query("SELECT * FROM burgers WHERE burger_id_pk = :id")
    fun findBurgerById(id : Int) : LiveData<BurgerVO>

    @Query("DELETE FROM burgers")
    fun deleteAllBurgers()
}