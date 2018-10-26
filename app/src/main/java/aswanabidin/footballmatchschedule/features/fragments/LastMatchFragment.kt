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
import kotlinx.android.synthetic.main.fragment_last_match.*

class LastMatchFragment : Fragment(), MatchFragmentContracts.LastMatchFragmentView {

    private lateinit var mPresenter: LastMatchPresenter

    private var matchLists: MutableList<MatchEventModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = RetrofitInstance.getClient().create(IRestTheSportDB::class.java)
        val request = MatchEventPresenter(service)
        val schedulerProvider = AppSchedulerProvider()
        mPresenter = LastMatchPresenter(this, request, schedulerProvider)
        mPresenter.getFootballLastMatch()

    }

    override fun showProgress() {
        progressLastMatch.show()
        rvLastMatch.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        progressLastMatch.hide()
        rvLastMatch.visibility = View.VISIBLE

    }

    override fun displayFootballMatch(matchList: List<MatchEventModel>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvLastMatch.layoutManager = layoutManager
        rvLastMatch.adapter = TeamsAdapter(matchList, context)
    }

    class LastMatchPresenter(
        private val matchView: MatchFragmentContracts.LastMatchFragmentView,
        private val matchEventPresenter: MatchEventPresenter,
        private val scheduler: SchedulerProvider
    ) : MatchFragmentContracts.LastMatchFragmentPresenter {

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

    class AppSchedulerProvider : SchedulerProvider {
        override fun view() = AndroidSchedulers.mainThread()
        override fun result() = Schedulers.io()
    }


    interface SchedulerProvider {
        fun view(): Scheduler
        fun result(): Scheduler
    }

}