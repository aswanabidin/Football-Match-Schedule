package aswanabidin.footballmatchschedule.features.favorite

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.adapter.TeamsAdapter
import aswanabidin.footballmatchschedule.model.database.DatabasePresenter
import aswanabidin.footballmatchschedule.model.match.MatchEventModel
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.network.IRestTheSportDB
import aswanabidin.footballmatchschedule.network.RetrofitInstance
import aswanabidin.footballmatchschedule.utils.AppSchedule
import aswanabidin.footballmatchschedule.utils.hide
import aswanabidin.footballmatchschedule.utils.show
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment(), FavoriteMatchContracts.View {

    private var matchLists: MutableList<MatchEventModel> = mutableListOf()
    private lateinit var mPresenter: FavoriteMatchPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = RetrofitInstance.getClient().create(IRestTheSportDB::class.java)
        val request = MatchEventPresenter(service)
        val database = DatabasePresenter(context!!)
        val appSchedule = AppSchedule()
        mPresenter = FavoriteMatchPresenter(this, request, database, appSchedule)
        mPresenter.getFootballMatchData()
        swipeLayotFavorite.onRefresh {
            mPresenter.getFootballMatchData()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }


    override fun hideLoading() {
        mainProgressFavorite.show()
        rvFavoriteMatch.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressFavorite.show()
        rvFavoriteMatch.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<MatchEventModel>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFavoriteMatch.layoutManager = layoutManager
        rvFavoriteMatch.adapter = TeamsAdapter(matchList, context)
    }


    override fun hideSwipeRefresh() {
        swipeLayotFavorite.isRefreshing = false
        mainProgressFavorite.hide()
        rvFavoriteMatch.visibility = View.VISIBLE
    }

}
