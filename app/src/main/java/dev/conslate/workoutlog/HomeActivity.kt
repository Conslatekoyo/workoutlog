package dev.conslate.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var fcvHome: FragmentContainerView
    lateinit var bnvHome:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castViews()
        setupBottomNav()
    }
    fun castViews(){
        fcvHome= findViewById(R.id.fcvhome)
        bnvHome= findViewById(R.id.bnvhome)
    }
    fun setupBottomNav(){
        bnvHome.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan-> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvhome, PlanFragment())
                    transaction.commit()
                    true
                }

                R.id.track ->{
                    val transaction= supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvhome,TrackFragment())
                    transaction.commit()
                    true
                }
                R.id.profile->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvhome,ProfileFragment()).commit()
                    true
                }
                else->false
            }

        }
    }
}