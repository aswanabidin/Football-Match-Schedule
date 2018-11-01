package aswanabidin.footballmatchschedule.features.match.next

import aswanabidin.footballmatchschedule.features.match.MatchFragmentContracts
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.utils.IAppScheduler
import io.reactivex.disposables.CompositeDisposable

class NextMatchPresenter(
    private val matchView: MatchFragmentContracts.NextMatchFragmentView,
    private val matchEventPresenter: MatchEventPresenter,
    private val scheduler: IAppScheduler) : MatchFragmentContracts.NextMatchFragmentPresenter {

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