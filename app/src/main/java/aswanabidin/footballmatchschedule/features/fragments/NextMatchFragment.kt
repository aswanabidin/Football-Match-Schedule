package aswanabidin.footballmatchschedule.features.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.adapter.TeamsAdapter
import aswanabidin.footballmatchschedule.model.MatchEventModel
import aswanabidin.footballmatchschedule.model.MatchEventPresenter
import aswanabidin.footballmatchschedule.network.IRestTheSportDB
import aswanabidin.footballmatchschedule.network.RetrofitInstance
import aswanabidin.footballmatchschedule.utils.hide
import aswanabidin.footballmatchschedule.utils.show
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_next_match.*


class NextMatchFragment : Fragment(), MatchFragmentContracts.NextMatchFragmentView {

    private lateinit var mPresenter: MatchFragmentContracts.NextMatchFragmentPresenter

    private var matchLists: MutableList<MatchEventModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = RetrofitInstance.getClient().create(IRestTheSportDB::class.java)
        val request = MatchEventPresenter(service)
        val schedulerProvider = NextMatchFragment.AppSchedulerProvider()
        mPresenter = NextMatchPresenter(this, request, schedulerProvider)

        mPresenter.getFootballNextMatch()

    }

    override fun showProgress() {
        progressNextMatch.show()
        rvNextMatch.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        progressNextMatch.hide()
        rvNextMatch.visibility = View.VISIBLE

    }

    override fun displayFootballMatch(matchList: List<MatchEventModel>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvNextMatch.layoutManager = layoutManager
        rvNextMatch.adapter = TeamsAdapter(matchList, context)
    }

    class NextMatchPresenter(
        private val matchView: MatchFragmentContracts.NextMatchFragmentView,
        private val matchEventPresenter: MatchEventPresenter,
        private val scheduler: NextMatchFragment.SchedulerProvider
    ) : MatchFragmentContracts.NextMatchFragmentPresenter {

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

    class AppSchedulerProvider : SchedulerProvider {
        override fun view() = AndroidSchedulers.mainThread()
        override fun result() = Schedulers.io()
    }


    interface SchedulerProvider {
        fun view(): Scheduler
        fun result(): Scheduler
    }

}
