package aswanabidin.footballmatchschedule

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import aswanabidin.footballmatchschedule.features.match.last.LastMatchFragment
import aswanabidin.footballmatchschedule.features.match.next.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.lastMatch -> {
                    loadLastMatch(savedInstanceState)
                }
                R.id.nextMatch -> {
                    loadNextMatch(savedInstanceState)
                }
                R.id.favMatch -> {
                    loadFavoritesMatch(savedInstanceState)
                }
            }
            true
        }


    }

    private fun loadLastMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
//                .commit()
        }
    }

//    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//
//        override fun getItem(position: Int): Fragment {
//            return when (position) {
//                0 -> {
//                    LastMatchFragment()
//                }
//                else -> {
//                    NextMatchFragment()
//
//                }
//            }
//        }
//
//        override fun getCount(): Int {
//            return 2
//        }
//
//        override fun getPageTitle(position: Int): CharSequence {
//            return when (position) {
//                0 -> "Last Match"
//                else -> {
//                    "Next Match"
//                }
//            }
//        }
//
//
//    }


}
