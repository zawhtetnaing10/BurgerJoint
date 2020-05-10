package com.zg.burgerjoint.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "burgers")
class BurgerVO {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "burger_id_pk")
    var burgerId : Int = 0
    @ColumnInfo(name = "burger_name")
    var burgerName : String = ""
    @ColumnInfo(name = "burger_description")
    var burgerDescription : String = ""
    @ColumnInfo(name = "burger_image_url")
    var burgerImageUrl : String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BurgerVO

        if (burgerId != other.burgerId) return false

        return true
    }

    override fun hashCode(): Int {
        return burgerId
    }


}