package aswanabidin.footballmatchschedule.features.favorite

import aswanabidin.footballmatchschedule.model.database.DatabasePresenter
import aswanabidin.footballmatchschedule.model.match.MatchEventModel
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.utils.AppScheduler
import io.reactivex.disposables.CompositeDisposable

class FavoriteMatchPresenter(private val mView: FavoriteMatchContracts.View,
                             private val matchPresenter: MatchEventPresenter,
                             private val databasePresenter: DatabasePresenter,
                             private val scheduler: AppScheduler) : FavoriteMatchContracts.Presenter {

    override fun getFootballMatchData() {
        val compositeDisposable = CompositeDisposable()
        mView.showLoading()
        val favoriteList = databasePresenter.getMatchData()

        val eventList: MutableList<MatchEventModel> = mutableListOf()
        for (fav in favoriteList) {
            compositeDisposable.add(matchPresenter.getEventById(fav.idEvent)
                .observeOn(scheduler.view())
                .subscribeOn(scheduler.result())
                .subscribe {
                    eventList.add(it.events[0])
                    mView.displayFootballMatch(eventList)
                    mView.hideLoading()
                    mView.hideSwipeRefresh()
                })
        }

        if (favoriteList.isEmpty()) {
            mView.hideLoading()
            mView.displayFootballMatch(eventList)
            mView.hideSwipeRefresh()
        }

    }
}