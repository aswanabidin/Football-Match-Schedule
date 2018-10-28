package aswanabidin.footballmatchschedule.model.match

import aswanabidin.footballmatchschedule.model.teams.TeamsResponse
import aswanabidin.footballmatchschedule.network.IRestTheSportDB
import io.reactivex.Flowable


class MatchEventPresenter(private val iRestTheSportDB: IRestTheSportDB) {

    fun getLastMatch(id: String): Flowable<MatchEventResponse> = iRestTheSportDB.getLastMatch(id)

    fun getNextMatch(id: String): Flowable<MatchEventResponse> = iRestTheSportDB.getNextMatch(id)

    fun getTeam(id: String): Flowable<TeamsResponse> = iRestTheSportDB.getTeam(id)

    fun getEventById(id: String): Flowable<MatchEventResponse> = iRestTheSportDB.getEventById(id)

}