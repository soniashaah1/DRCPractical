package com.example.drcpractical.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(ctx: Context) :
    SQLiteOpenHelper(ctx, DatabaseConstant.DATABASE_NAME, null, DatabaseConstant.DATABASE_VERSION) {

    companion object {
        private lateinit var INSTANCE: DatabaseHelper
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false

        fun closeDatabase() {
            if (database.isOpen && databaseOpen) {
                database.close()
                databaseOpen = false

                Log.i("Database", "Database close")
            }
        }

        fun initDatabaseInstance(ctx: Context): DatabaseHelper {
            INSTANCE = DatabaseHelper(ctx)
            return INSTANCE
        }

        fun insertData(place: PlaceTable): Long {

            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val values = ContentValues()
            values.put(DatabaseConstant.ROW_LAT, place.lat)
            values.put(DatabaseConstant.ROW_LONG, place.lan)
            values.put(DatabaseConstant.ROW_NAME, place.name)
            values.put(DatabaseConstant.ROW_VICINITY, place.vicinity)
            return database.insert(DatabaseConstant.DATABASE_TABLE, null, values)
        }

        fun updateData(place: PlaceTable): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val values = ContentValues()
            values.put(DatabaseConstant.ROW_LAT, place.lat)
            values.put(DatabaseConstant.ROW_LONG, place.lan)
            values.put(DatabaseConstant.ROW_NAME, place.name)
            values.put(DatabaseConstant.ROW_VICINITY, place.vicinity)
            return database.update(
                DatabaseConstant.DATABASE_TABLE,
                values,
                "${DatabaseConstant.ROW_ID} = ${place.id}",
                null
            )
        }

        @SuppressLint("Range")
        fun getAllData(): MutableList<PlaceTable> {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val data: MutableList<PlaceTable> = ArrayList()
            val cursor = database.rawQuery("SELECT * FROM ${DatabaseConstant.DATABASE_TABLE}", null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {
                        val place = PlaceTable()
                        place.id = cur.getInt(cur.getColumnIndex(DatabaseConstant.ROW_ID))
                        place.lat = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_LAT))
                        place.lan = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_LONG))
                        place.name =
                            cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_NAME))
                        place.vicinity =
                            cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_VICINITY))
                        data.add(place)

                    } while (cursor.moveToNext())
                }
            }
            Log.e("Database","size ${data.size}");
            return data
        }

        fun deleteData(id: Int): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }
            return database.delete(
                DatabaseConstant.DATABASE_TABLE,
                "${DatabaseConstant.ROW_ID} = $id",
                null
            )
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(DatabaseConstant.QUERY_CREATE)
        Log.i("DATABASE", "DATABASE CREATED")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DatabaseConstant.QUERY_UPGRADE)
        Log.i("DATABASE", "DATABASE UPDATED")
    }

}