package es.uc3m.g1.musey.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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
        }
        R.binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.recommendations -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }*/
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search -> {
                val search = FragmentSearch.newInstance()
                displayFragment(search)
                true
            }
            R.id.recommendations -> {
                val recommendations = FragmentRecommendation.newInstance()
                displayFragment(recommendations)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun displayFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}