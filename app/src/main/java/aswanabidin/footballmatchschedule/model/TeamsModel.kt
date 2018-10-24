package aswanabidin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class TeamsModel(

    @SerializedName("idTeam")
    var idTeam: String,

    @SerializedName("idSoccerXML")
    var idSoccerXML: String,

    @SerializedName("intLoved")
    var intLoved: String,

    @SerializedName("strTeam")
    var strTeam: String,

    @SerializedName("strTeamShort")
    var strTeamShort: String,

    @SerializedName("strAlternate")
    var strAlternate: String,

    @SerializedName("intFormedYear")
    var intFormedYear: String,

    @SerializedName("strSport")
    var strSport: String,

    @SerializedName("strLeague")
    var strLeague: String,

    @SerializedName("idLeague")
    var idLeague: String,

    @SerializedName("strDivision")
    var strDivision: Any,

    @SerializedName("strManager")
    var strManager: String,

    @SerializedName("strStadium")
    var strStadium: String,

    @SerializedName("strKeywords")
    var strKeywords: String,

    @SerializedName("strRSS")
    var strRSS: String,

    @SerializedName("strStadiumThumb")
    var strStadiumThumb: String,

    @SerializedName("strStadiumDescription")
    var strStadiumDescription: String,

    @SerializedName("strStadiumLocation")
    var strStadiumLocation: String,

    @SerializedName("intStadiumCapacity")
    var intStadiumCapacity: String,

    @SerializedName("strWebsite")
    var strWebsite: String,

    @SerializedName("strFacebook")
    var strFacebook: String,

    @SerializedName("strTwitter")
    var strTwitter: String,

    @SerializedName("strInstagram")
    var strInstagram: String,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String,

    @SerializedName("strDescriptionDE")
    var strDescriptionDE: String,

    @SerializedName("strDescriptionFR")
    var strDescriptionFR: Any,

    @SerializedName("strDescriptionCN")
    var strDescriptionCN: Any,

    @SerializedName("strDescriptionIT")
    var strDescriptionIT: String,

    @SerializedName("strDescriptionJP")
    var strDescriptionJP: Any,

    @SerializedName("strDescriptionRU")
    var strDescriptionRU: Any,

    @SerializedName("strDescriptionES")
    var strDescriptionES: Any,

    @SerializedName("strDescriptionPT")
    var strDescriptionPT: Any,

    @SerializedName("strDescriptionSE")
    var strDescriptionSE: Any,

    @SerializedName("strDescriptionNL")
    var strDescriptionNL: Any,

    @SerializedName("strDescriptionHU")
    var strDescriptionHU: Any,

    @SerializedName("strDescriptionNO")
    var strDescriptionNO: Any,

    @SerializedName("strDescriptionIL")
    var strDescriptionIL: Any,

    @SerializedName("strDescriptionPL")
    var strDescriptionPL: Any,

    @SerializedName("strGender")
    var strGender: String,

    @SerializedName("strCountry")
    var strCountry: String,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String,

    @SerializedName("strTeamJersey")
    var strTeamJersey: String,

    @SerializedName("strTeamLogo")
    var strTeamLogo: String,

    @SerializedName("strTeamFanart1")
    var strTeamFanart1: String,

    @SerializedName("strTeamFanart2")
    var strTeamFanart2: String,

    @SerializedName("strTeamFanart3")
    var strTeamFanart3: String,

    @SerializedName("strTeamFanart4")
    var strTeamFanart4: String,

    @SerializedName("strTeamBanner")
    var strTeamBanner: String,

    @SerializedName("strYoutube")
    var strYoutube: String,

    @SerializedName("strLocked")
    var strLocked: String

)