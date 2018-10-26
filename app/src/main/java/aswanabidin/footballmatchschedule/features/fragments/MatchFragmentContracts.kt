package aswanabidin.footballmatchschedule.features.fragments

import aswanabidin.footballmatchschedule.model.MatchEventModel

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