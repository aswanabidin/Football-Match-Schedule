package aswanabidin.footballmatchschedule.features.match

import aswanabidin.footballmatchschedule.model.match.MatchEventModel

class MatchFragmentContracts {

    interface NextMatchFragmentView {
        fun showProgress()
        fun hideProgress()
        fun displayFootballMatch(matchList: List<MatchEventModel>)
    }

    interface NextMatchFragmentPresenter {
        fun getFootballNextMatch()
    }


    interface LastMatchFragmentView {
        fun showProgress()
        fun hideProgress()
        fun displayFootballMatch(matchList: List<MatchEventModel>)
    }

    interface LastMatchFragmentPresenter {
        fun getFootballLastMatch()
    }
}