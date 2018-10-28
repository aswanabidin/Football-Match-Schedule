package aswanabidin.footballmatchschedule.model.database

import android.content.Context
import aswanabidin.footballmatchschedule.constants.FavoriteConstants
import aswanabidin.footballmatchschedule.database.database
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DatabasePresenter(private val context: Context) : DatabaseContracts.databaseCrud {

    override fun getMatchData(): List<FavoriteConstants> {
        lateinit var favoriteList: List<FavoriteConstants>
        context.database.use {
            val result = select(FavoriteConstants.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteConstants>())
            favoriteList = favorite
        }
        return favoriteList
    }

    override fun insertMatchData(matchEventId: String, homeId: String, awayId: String) {
        context.database.use {
            insert(
                FavoriteConstants.TABLE_FAVORITE,
                FavoriteConstants.EVENT_ID to matchEventId,
                FavoriteConstants.HOME_TEAM_ID to homeId,
                FavoriteConstants.AWAY_TEAM_ID to awayId
            )

        }
    }

    override fun deleteMatchData(matchEventId: String) {
        context.database.use {
            delete(
                FavoriteConstants.TABLE_FAVORITE, "(EVENT_ID = {id})",
                "id" to matchEventId
            )
        }
    }

    override fun checkFavorite(matchEventId: String): List<FavoriteConstants> {
        return context.database.use {
            val result = select(FavoriteConstants.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to matchEventId)
            val favorite = result.parseList(classParser<FavoriteConstants>())
            favorite
        }
    }

}
