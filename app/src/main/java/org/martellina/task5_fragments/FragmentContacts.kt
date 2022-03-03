package org.martellina.task5_fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class FragmentContacts: Fragment(R.layout.fragment_contacts), BackPressedListener {

    private lateinit var name0: TextView
    private lateinit var surname0: TextView
    private lateinit var number0: TextView
    private lateinit var name1: TextView
    private lateinit var surname1: TextView
    private lateinit var number1: TextView
    private lateinit var name2: TextView
    private lateinit var surname2: TextView
    private lateinit var number2: TextView

    private lateinit var contact0: CardView
    private lateinit var contact1: CardView
    private lateinit var contact2: CardView

    private lateinit var contactClickListener: ContactClickListener

    private lateinit var contactsList: ArrayList<Person>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactClickListener = context as ContactClickListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name0 = view.findViewById(R.id.name0)
        surname0 = view.findViewById(R.id.surname0)
        number0 = view.findViewById(R.id.number0)
        name1 = view.findViewById(R.id.name1)
        surname1 = view.findViewById(R.id.surname1)
        number1 = view.findViewById(R.id.number1)
        name2 = view.findViewById(R.id.name2)
        surname2 = view.findViewById(R.id.surname2)
        number2 = view.findViewById(R.id.number2)

        contactsList = requireArguments().getParcelableArrayList(LIST_EXTRA)!!

        name0.text = contactsList[0].name
        surname0.text = contactsList[0].surname
        number0.text = contactsList[0].number
        name1.text = contactsList[1].name
        surname1.text = contactsList[1].surname
        number1.text = contactsList[1].number
        name2.text = contactsList[2].name
        surname2.text = contactsList[2].surname
        number2.text = contactsList[2].number

        contact0 = view.findViewById(R.id.card_view_1)
        contact1 = view.findViewById(R.id.card_view_2)
        contact2 = view.findViewById(R.id.card_view_3)

        contact0.setOnClickListener {
            contactClickListener.onContactClicked(contactsList, 0)
        }

        contact1.setOnClickListener {
            contactClickListener.onContactClicked(contactsList, 1)
        }


        contact2.setOnClickListener {
            contactClickListener.onContactClicked(contactsList, 2)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(LIST_EXTRA, contactsList)
    }

    override fun onBackPressed(): Boolean = false

    companion object {
        const val TAG_FC = "TAG_FC"
        const val LIST_EXTRA = "LIST_EXTRA"

        fun newInstance(contactsList: ArrayList<Person>) = FragmentContacts().also {
            it.arguments = Bundle().apply {
                putParcelableArrayList(LIST_EXTRA, contactsList)
            }
        }
    }

    interface ContactClickListener {
        fun onContactClicked(contactsList: ArrayList<Person>, id: Int)
    }

}