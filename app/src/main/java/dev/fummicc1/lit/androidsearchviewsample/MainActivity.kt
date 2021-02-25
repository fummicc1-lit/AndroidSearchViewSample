package dev.fummicc1.lit.androidsearchviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fummicc1.lit.androidsearchviewsample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job: Job = Job()

    lateinit var binding: ActivityMainBinding

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val qiitaClient: QiitaClientInterface = QiitaClient()

    private val qiitaList: MutableLiveData<List<Qiita>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = QiitaRecyclerViewAdapter(this)
        val layoutManager = LinearLayoutManager(this)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager

            qiitaList.observe(this@MainActivity, Observer {
                adapter.updateArtilces(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        // SearchViewの設定
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setIconifiedByDefault(false)
            queryHint = "Qiitaの記事を検索"
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText == null) return true
                    launch {
                        val items = qiitaClient.getItems(newText, 1, 10)

                        withContext(Dispatchers.Main) {
                            qiitaList.postValue(items)
                        }
                    }
                    return true
                }

            })
        }

        return true
    }
}