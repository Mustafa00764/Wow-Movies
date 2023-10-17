package com.example.wowmovie_.model

import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity("save")
data class SaveMovie(
        @PrimaryKey(autoGenerate = true)
        val id :Int?=null,
        val image:String,
        val title:String,
        val ball:String,
        val time:String

)


//    @Entity("carts list")
//    data class Cart(
//
//        @PrimaryKey(autoGenerate = true)
//        val id:Int?=null,
//        val image:String,
//        val title:String,
//        var price:Double,
//        var count:Int=1
//
//        )
//
//    @Entity("data")
//    data class WishList(
//
//        @PrimaryKey(autoGenerate = true)
//        val id:Int?=null,
//        val image:String?=null,
//        val title:String,
//        val price: String,
//        val rating:String,
//        val description:String)

