package aswanabidin.footballmatchschedule.features.detail

import aswanabidin.footballmatchschedule.model.TeamsModel

class DetailContracts {

    interface View{
        fun displayTeamBadgeHome(team: TeamsModel)
        fun displayTeamBadgeAway(team: TeamsModel)
    }

    interface Presenter{
        fun getTeamsBadgeAway(id:String)
        fun getTeamsBadgeHome(id:String)
    }
}