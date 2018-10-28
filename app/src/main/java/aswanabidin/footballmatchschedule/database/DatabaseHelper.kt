package aswanabidin.footballmatchschedule.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import aswanabidin.footballmatchschedule.constants.FavoriteConstants
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteConstants.TABLE_FAVORITE, true,
            FavoriteConstants.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteConstants.EVENT_ID to TEXT + UNIQUE,
            FavoriteConstants.HOME_TEAM_ID to TEXT,
            FavoriteConstants.AWAY_TEAM_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteConstants.TABLE_FAVORITE, true)
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)