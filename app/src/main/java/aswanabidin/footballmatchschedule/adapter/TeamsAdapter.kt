package aswanabidin.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.features.detail.DetailActivity
import aswanabidin.footballmatchschedule.model.match.MatchEventModel
import kotlinx.android.synthetic.main.card_match_item.view.*
import org.jetbrains.anko.startActivity


class TeamsAdapter(private val matchEventList: List<MatchEventModel>, val context: Context?) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): TeamsViewHolder {
        return TeamsViewHolder(LayoutInflater.from(context).inflate(R.layout.card_match_item, parent, false))
    }

    override fun getItemCount(): Int = matchEventList.size


    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(matchEventList[position])
    }


    inner class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(matchEvent: MatchEventModel) {

            itemView.dateMatch.text = matchEvent.dateEvent
            itemView.tvHomeName.text = matchEvent.strHomeTeam
            itemView.tvHomeScore.text = matchEvent.intHomeScore
            itemView.tvAwayName.text = matchEvent.strAwayTeam
            itemView.tvAwayScore.text = matchEvent.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>("match" to matchEvent)

            }
        }
    }
}