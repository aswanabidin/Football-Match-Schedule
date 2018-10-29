package aswanabidin.footballmatchschedule.features.match.next

import aswanabidin.footballmatchschedule.features.match.MatchFragmentContracts
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.utils.IAppSchedule
import io.reactivex.disposables.CompositeDisposable

class NextMatchPresenter(
    private val matchView: MatchFragmentContracts.NextMatchFragmentView,
    private val matchEventPresenter: MatchEventPresenter,
    private val scheduler: IAppSchedule.View) : MatchFragmentContracts.NextMatchFragmentPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballNextMatch() {
        matchView.showProgress()
        compositeDisposable.add(matchEventPresenter.getNextMatch("4332")
            .observeOn(scheduler.view())
            .subscribeOn(scheduler.result())
            .subscribe {
                matchView.displayFootballMatch(it.events)
                matchView.hideProgress()
            })
    }
}