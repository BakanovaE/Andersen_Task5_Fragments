package org.martellina.task5_fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class FragmentDetails: Fragment(R.layout.fragment_details) {

    private lateinit var nameDetail: EditText
    private lateinit var surnameDetail: EditText
    private lateinit var numberDetail: EditText

    private lateinit var buttonSave: Button

    private lateinit var saveButtonClickListener: SaveButtonClickListener
    private lateinit var contactsList: ArrayList<Person>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        saveButtonClickListener = context as SaveButtonClickListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameDetail = view.findViewById(R.id.name_d)
        surnameDetail = view.findViewById(R.id.surname_d)
        numberDetail = view.findViewById(R.id.number_d)

        buttonSave = view.findViewById(R.id.button_save)

        contactsList = requireArguments().getParcelableArrayList<Person>(FragmentContacts.LIST_EXTRA)!!
        val id = requireArguments().getInt(ID_EXTRA)

        val person = contactsList[id]

        nameDetail.setText(person.name)
        surnameDetail.setText(person.surname)
        numberDetail.setText(person.number)

        buttonSave.setOnClickListener {
            val newPerson = Person(nameDetail.text.toString(), surnameDetail.text.toString(), numberDetail.text.toString())
            when (id) {

                0 -> contactsList[0] = newPerson
                1 -> contactsList[1] = newPerson
                2 -> contactsList[2] = newPerson
            }
            saveButtonClickListener.onSaveButtonClicked(contactsList)
        }

    }

    companion object {
        const val TAG_FD = "FRAGMENT_DETAILS"

        private const val ID_EXTRA = "ID_EXTRA"

        fun newInstance(contactsList: ArrayList<Person>, id: Int): FragmentDetails {
            return FragmentDetails().also {
                it.arguments = Bundle().apply {
                    putParcelableArrayList(FragmentContacts.LIST_EXTRA, contactsList)
                    putInt(ID_EXTRA, id)
                }
            }
        }
    }

    interface SaveButtonClickListener {
        fun onSaveButtonClicked(contactsList: ArrayList<Person>)
    }

}