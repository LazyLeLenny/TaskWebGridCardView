package com.example.taskwebgridcardview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var gridViewGV : GridView
    private lateinit var toolbarTB : Toolbar
    private val list = mutableListOf(
        GridViewModel("Steam", R.drawable._drbfb, "https://store.steampowered.com/"),
        GridViewModel("Google", R.drawable.google_2015_logo_svg, "https://www.google.com/"),
        GridViewModel("Кинопоиск", R.drawable.kinopoisk_colored_logo__2021_present__svg, "https://www.kinopoisk.ru/"),
        GridViewModel("Gismeteo", R.drawable.logo_gismeteo, "https://www.gismeteo.ru/"),
        GridViewModel("Twitch", R.drawable.twitch_logo_png_seeklogo_274042, "https://www.twitch.tv/"),
        GridViewModel("YouTube", R.drawable.youtube_logo_2017_svg, "https://www.youtube.com/")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        gridViewGV = findViewById(R.id.gridViewGV)

        val adapter = GridViewAdapter(list = list, this@MainActivity)
        gridViewGV.adapter = adapter

        gridViewGV.onItemClickListener = AdapterView.OnItemClickListener{_, _, position, _ ->
            startActivity(Intent(this, WebActivity::class.java)
                .putExtra("url", list[position].url)
            )
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exit_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> {
                finish()
                exitProcess(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}