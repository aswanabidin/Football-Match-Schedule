package aswanabidin.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.model.MatchEvent

class TeamsAdapter(private val matchEventList: List<MatchEvent>, val context: Context?) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): TeamsViewHolder {
        return TeamsViewHolder(LayoutInflater.from(context).inflate(R.layout.card_match_item, parent, false));
    }

    override fun getItemCount(): Int = matchEventList.size


    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder
    }


    inner class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(matchEvent: MatchEvent){
        }

    }
}