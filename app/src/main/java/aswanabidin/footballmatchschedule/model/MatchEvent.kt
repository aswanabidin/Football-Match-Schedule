package aswanabidin.footballmatchschedule.model

import aswanabidin.footballmatchschedule.network.IRestTheSportDB
import io.reactivex.Flowable

class MatchEvent(private val iRestTheSportDB: IRestTheSportDB) {

    fun getLastMatch(id: String): Flowable<MatchEventResponse> = iRestTheSportDB.getLastMatch(id)

    fun getNextMatch(id: String): Flowable<MatchEventResponse> = iRestTheSportDB.getNextMatch(id)

    fun getTeam(id: String): Flowable<TeamsResponse> = iRestTheSportDB.getTeam(id)

    fun getEventById(id: String): Flowable<MatchEventResponse> = iRestTheSportDB.getEventById(id)

}