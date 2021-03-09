package es.uc3m.g1.musey.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import es.uc3m.g1.musey.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val navigation: NavigationView = findViewById(R.id.navigation_view)
        navigation.setNavigationItemSelectedListener {menuItem ->
            val title: Int
            title = when (menuItem.getItemId()) {
                R.id.search -> R.string.search
                else -> throw IllegalArgumentException("Menu option not implemented!!")
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }*/
    }
}