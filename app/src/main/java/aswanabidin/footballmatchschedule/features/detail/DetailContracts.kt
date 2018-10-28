package aswanabidin.footballmatchschedule.features.detail

import aswanabidin.footballmatchschedule.constants.FavoriteConstants
import aswanabidin.footballmatchschedule.model.teams.TeamsModel

class DetailContracts {

    interface View{
        fun displayTeamBadgeHome(team: TeamsModel)
        fun displayTeamBadgeAway(team: TeamsModel)
        fun setFavoriteState(favList:List<FavoriteConstants>)
    }

    interface Presenter{
        fun getTeamsBadgeAway(id:String)
        fun getTeamsBadgeHome(id:String)
        fun deleteMatch(id:String)
        fun insertMatch(eventId: String, homeId: String, awayId: String)
        fun checkMatch(id:String)

    }

}