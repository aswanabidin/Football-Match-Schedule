package aswanabidin.footballmatchschedule.features.match.last

import aswanabidin.footballmatchschedule.features.match.MatchFragmentContracts
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.utils.AppSchedule
import aswanabidin.footballmatchschedule.utils.IAppSchedule
import io.reactivex.disposables.CompositeDisposable

class LastMatchPresenter(
    private val matchView: MatchFragmentContracts.LastMatchFragmentView,
    private val matchEventPresenter: MatchEventPresenter,
    private val scheduler: IAppSchedule.View) : MatchFragmentContracts.LastMatchFragmentPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballLastMatch() {
        matchView.showProgress()
        compositeDisposable.add(matchEventPresenter.getLastMatch("4332")
            .observeOn(scheduler.view())
            .subscribeOn(scheduler.result())
            .subscribe {
                matchView.displayFootballMatch(it.events)
                matchView.hideProgress()
            })
    }
}