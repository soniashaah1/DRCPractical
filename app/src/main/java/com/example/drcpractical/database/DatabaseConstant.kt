package com.example.drcpractical.database

class DatabaseConstant {

    companion object {
        const val DATABASE_NAME = "MAP"
        const val DATABASE_VERSION = 1

        const val DATABASE_TABLE = "PLACE"
        const  val ROW_ID = "_id"
        const  val ROW_LAT = "lat"
        const  val ROW_LONG = "long"
        const  val ROW_NAME = "name"
        const  val ROW_VICINITY = "vicinity"

        const val QUERY_CREATE =
            "CREATE TABLE IF NOT EXISTS $DATABASE_TABLE ($ROW_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ROW_LAT TEXT , $ROW_LONG TEXT , $ROW_NAME TEXT , $ROW_VICINITY TEXT)"
        const  val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABLE"
    }
}