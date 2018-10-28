package aswanabidin.footballmatchschedule.model.database

import aswanabidin.footballmatchschedule.constants.FavoriteConstants

class DatabaseContracts {

    interface databaseCrud {
        fun getMatchData(): List<FavoriteConstants>
        fun insertMatchData(matchEventId: String, homeId: String, awayId: String)
        fun deleteMatchData(matchEventId: String)
        fun checkFavorite(matchEventId: String): List<FavoriteConstants>
    }

}