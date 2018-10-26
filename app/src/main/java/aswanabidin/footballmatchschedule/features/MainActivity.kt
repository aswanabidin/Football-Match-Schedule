package aswanabidin.footballmatchschedule.features

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import aswanabidin.footballmatchschedule.R
import aswanabidin.footballmatchschedule.features.fragments.LastMatchFragment
import aswanabidin.footballmatchschedule.features.fragments.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter (supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        tabs_main.setSelectedTabIndicatorColor(Color.parseColor("#00574B"));
        tabs_main.setTabTextColors(Color.parseColor("#E0E0E0"), Color.parseColor("#ffffff"));

    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    LastMatchFragment()
                }
                else -> {
                    NextMatchFragment()

                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Last Match"
                else -> {
                    "Next Match"
                }
            }
        }


    }


}
