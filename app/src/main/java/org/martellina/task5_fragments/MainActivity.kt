package org.martellina.task5_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.martellina.task5_fragments.FragmentContacts.Companion.TAG_FC
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), FragmentContacts.ContactClickListener, FragmentDetails.SaveButtonClickListener {

    private var isTablet = false
    lateinit var contactsList: ArrayList<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactsList = ArrayList<Person>()

        for (i in 0 .. 2) {
            contactsList.add(i, Person("name$i", "surname$i", "123456$i"))
        }

        isTablet = applicationContext.resources.getBoolean(R.bool.isTablet)

        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragment_container, FragmentContacts.newInstance(contactsList), TAG_FC)
            commit()
        }
    }

    override fun onContactClicked(contactsList: ArrayList<Person>, id: Int) {

        if (isTablet) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.fragment_container1, FragmentDetails.newInstance(contactsList, id))
                addToBackStack(FragmentDetails.TAG_FD)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.fragment_container, FragmentDetails.newInstance(contactsList, id))
                addToBackStack(FragmentDetails.TAG_FD)
                commit()
            }
        }
    }

    override fun onSaveButtonClicked(contactsList: ArrayList<Person>) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragment_container, FragmentContacts.newInstance(contactsList))
            commit()
        }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? BackPressedListener
        if (currentFragment?.onBackPressed() != false) {
            super.onBackPressed()
        } else {
            moveTaskToBack(true)
            exitProcess(-1)
        }
    }
}