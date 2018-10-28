package aswanabidin.footballmatchschedule.features.favorite

import aswanabidin.footballmatchschedule.model.match.MatchEventModel

class FavoriteMatchContracts {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList: List<MatchEventModel>)
        fun hideSwipeRefresh()
    }

    interface Presenter {
        fun getFootballMatchData()

    }
}