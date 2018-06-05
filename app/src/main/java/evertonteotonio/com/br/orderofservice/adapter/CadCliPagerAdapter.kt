package evertonteotonio.com.br.orderofservice.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CadCliPagerAdapter(fm: FragmentManager, private var tabCount: Int):
        FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {
        TODO("not implemented")

//        when (position) {
//            0 -> return Tab1Fragment()
//            1 -> return Tab2Fragment()
//            2 -> return Tab3Fragment()
//            3 -> return Tab4Fragment()
//            else -> return null
//        }
    }

//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> {
//                FirstFragment()
//            }
//            1 -> SecondFragment()
//            else -> {
//                return ThirdFragment()
//            }
//        }
//    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "First Tab"
            1 -> "Second Tab"
            else -> {
                return "Third Tab"
            }
        }
    }
}