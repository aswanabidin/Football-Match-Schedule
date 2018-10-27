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
                match_date.setTextColor(applicationContext.getColor(R.color.colorAccent))
            }
        }

        match_date.text = matchEvent.dateEvent
        home_score_match.text = matchEvent.intHomeScore
        away_score_match.text = matchEvent.intAwayScore

        home_goalkeeper.text = matchEvent.strHomeLineupGoalkeeper
        away_goalkeeper.text = matchEvent.strAwayLineupGoalkeeper

        home_defense.text = matchEvent.strHomeLineupDefense
        away_defense.text = matchEvent.strAwayLineupDefense

        home_midfield.text = matchEvent.strHomeLineupMidfield
        away_midfield.text = matchEvent.strAwayLineupMidfield

        home_forward.text = matchEvent.strHomeLineupForward
        away_forward.text = matchEvent.strAwayLineupForward

        home_substitutes.text = matchEvent.strHomeLineupSubstitutes
        away_substitutes.text = matchEvent.strAwayLineupSubstitutes

    }

    override fun displayTeamBadgeHome(team: TeamsModel) {
        Picasso.get()
            .load(team.strTeamBadge)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(img_home)
    }

    override fun displayTeamBadgeAway(team: TeamsModel) {
        Picasso.get()
            .load(team.strTeamBadge)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(img_away)
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
