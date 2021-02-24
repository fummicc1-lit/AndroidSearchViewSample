package dev.fummicc1.lit.androidsearchviewsample

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchResultsActivity : AppCompatActivity(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val qiitaClient: QiitaClientInterface = QiitaClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        handleIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent == null) return
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_SEARCH) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (query == null) return
            launch {
                val items = qiitaClient.getItems(query, 1, 10)


            }
        }
    }
}