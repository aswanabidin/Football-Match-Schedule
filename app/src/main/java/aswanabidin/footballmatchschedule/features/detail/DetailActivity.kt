package aswanabidin.footballmatchschedule.features.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.graphics.ColorUtils
import android.view.Menu
import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.model.MatchEventModel
import aswanabidin.footballmatchschedule.model.MatchEventPresenter
import aswanabidin.footballmatchschedule.model.TeamsModel
import aswanabidin.footballmatchschedule.network.IRestTheSportDB
import aswanabidin.footballmatchschedule.network.RetrofitInstance
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContracts.View {

    private lateinit var matchEvent: MatchEventModel
    private lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        val service = RetrofitInstance.getClient().create(IRestTheSportDB::class.java)
        val request = MatchEventPresenter(service)
        mPresenter = DetailPresenter(this, request)

        matchEvent = intent.getParcelableExtra<MatchEventModel>("match")
        mPresenter.getTeamsBadgeAway(matchEvent.idAwayTeam)
        mPresenter.getTeamsBadgeHome(matchEvent.idHomeTeam)
        Data(matchEvent)
        supportActionBar?.title = matchEvent.strEvent
    }

    private fun Data(matchEvent: MatchEventModel) {

        if (matchEvent.intHomeScore == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                matchDate.setTextColor(applicationContext.getColor(R.color.colorAccent))
            }
        }

        matchDate.text = matchEvent.dateEvent
        homeScoreMatch.text = matchEvent.intHomeScore
        awayScoreMatch.text = matchEvent.intAwayScore

        homeGoalKeeper.text = matchEvent.strHomeLineupGoalkeeper
        awayGoalKeeper.text = matchEvent.strAwayLineupGoalkeeper

        homeDefense.text = matchEvent.strHomeLineupDefense
        awayDefense.text = matchEvent.strAwayLineupDefense

        homeMidField.text = matchEvent.strHomeLineupMidfield
        awayMidField.text = matchEvent.strAwayLineupMidfield

        homeForward.text = matchEvent.strHomeLineupForward
        awayForward.text = matchEvent.strAwayLineupForward

        homeSubtitutes.text = matchEvent.strHomeLineupSubstitutes
        awaySubtitutes.text = matchEvent.strAwayLineupSubstitutes

    }

    override fun displayTeamBadgeHome(team: TeamsModel) {
        Picasso.get()
            .load(team.strTeamBadge)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imgHome)
    }

    override fun displayTeamBadgeAway(team: TeamsModel) {
        Picasso.get()
            .load(team.strTeamBadge)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imgAway)
    }


    class DetailPresenter(
        private val mView: DetailContracts.View,
        private val matchEventPresenter: MatchEventPresenter
    ) {
        private val compositeDisposable = CompositeDisposable()
        fun getTeamsBadgeHome(id: String) {
            compositeDisposable.add(matchEventPresenter.getTeam(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.displayTeamBadgeHome(it.teams[0])
                })
        }

        fun getTeamsBadgeAway(id: String) {
            compositeDisposable.add(matchEventPresenter.getTeam(
                id
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.displayTeamBadgeAway(it.teams[0])
                })
        }


    }

}
