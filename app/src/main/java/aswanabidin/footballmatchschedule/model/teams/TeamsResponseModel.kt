package aswanabidin.footballmatchschedule.model.teams

import com.google.gson.annotations.SerializedName

data class TeamsResponse(

    @SerializedName("teams")
    var teams: List<TeamsModel>)