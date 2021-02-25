package dev.fummicc1.lit.androidsearchviewsample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QiitaRecyclerViewAdapter(val context: Context): RecyclerView.Adapter<QiitaRecyclerViewAdapter.ViewHolder>() {

    private val articles: MutableList<Qiita> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = view.findViewById(R.id.bodyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_qiita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        holder.titleTextView.text = article.title
        holder.bodyTextView.text = article.renderedBody
    }

    override fun getItemCount(): Int = articles.size

    fun updateArtilces(articles: List<Qiita>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }
}