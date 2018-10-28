package aswanabidin.footballmatchschedule.network

import aswanabidin.footballmatchschedule.model.match.MatchEventResponse
import aswanabidin.footballmatchschedule.model.teams.TeamsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface IRestTheSportDB {

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id:String) : Flowable<MatchEventResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String) : Flowable<MatchEventResponse>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String) : Flowable<TeamsResponse>


    @GET("lookupevent.php")
    fun getEventById(@Query("id") id:String) : Flowable<MatchEventResponse>
}