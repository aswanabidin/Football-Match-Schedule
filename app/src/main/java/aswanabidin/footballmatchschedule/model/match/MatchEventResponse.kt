package aswanabidin.footballmatchschedule.model.match

import com.google.gson.annotations.SerializedName

data class MatchEventResponse (

    @SerializedName("events")
    var events: List<MatchEventModel>
)
