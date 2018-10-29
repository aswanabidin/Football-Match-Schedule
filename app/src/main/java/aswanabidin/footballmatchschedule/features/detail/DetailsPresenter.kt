package aswanabidin.footballmatchschedule.features.detail

import aswanabidin.footballmatchschedule.model.database.DatabasePresenter
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(private val mView: DetailContracts.View,
                      private val matchEventPresenter: MatchEventPresenter,
                      private val databasePresenter: DatabasePresenter) : DetailContracts.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getTeamsBadgeHome(id: String) {
        compositeDisposable.add(matchEventPresenter.getTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                mView.displayTeamBadgeHome(it.teams[0])
            })
    }

    override fun getTeamsBadgeAway(id: String) {
        compositeDisposable.add(matchEventPresenter.getTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                mView.displayTeamBadgeAway(it.teams[0])
            })
    }

    override fun deleteMatch(id: String) {
        databasePresenter.deleteMatchData(id)
    }

    override fun checkMatch(id: String) {
        mView.setFavoriteState(databasePresenter.checkFavorite(id))
    }

    override fun insertMatch(eventId: String, homeId: String, awayId: String) {
        databasePresenter.insertMatchData(eventId, homeId, awayId)
    }
}