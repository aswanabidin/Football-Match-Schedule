package aswanabidin.footballmatchschedule.features.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.R.menu.item_menu
import aswanabidin.footballmatchschedule.constants.FavoriteConstants
import aswanabidin.footballmatchschedule.model.database.DatabasePresenter
import aswanabidin.footballmatchschedule.model.match.MatchEventModel
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.model.teams.TeamsModel
import aswanabidin.footballmatchschedule.network.IRestTheSportDB
import aswanabidin.footballmatchschedule.network.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailContracts.View {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
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
        val database = DatabasePresenter(applicationContext)

        mPresenter = DetailPresenter(this, request, database)
        matchEvent = intent.getParcelableExtra<MatchEventModel>("match")
        mPresenter.checkMatch(matchEvent.idEvent.toString())
        mPresenter.getTeamsBadgeAway(matchEvent.idAwayTeam)
        mPresenter.getTeamsBadgeHome(matchEvent.idHomeTeam)
        data(matchEvent)
        supportActionBar?.title = matchEvent.strEvent
    }

    private fun data(matchEvent: MatchEventModel) {


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(item_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (!isFavorite) {
                    mPresenter.insertMatch(
                        matchEvent.idEvent.toString(), matchEvent.idHomeTeam, matchEvent.idAwayTeam
                    )
                    toast("Added match to favorite")

                    isFavorite = !isFavorite
                } else {
                    mPresenter.deleteMatch(matchEvent.idEvent.toString())
                    toast("Remove match from favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fill_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_no_fill_favorites)
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

    override fun setFavoriteState(favList: List<FavoriteConstants>) {
        if (!favList.isEmpty()) isFavorite = true
    }


}
