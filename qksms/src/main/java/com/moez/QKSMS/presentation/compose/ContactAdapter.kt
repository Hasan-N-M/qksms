package com.moez.QKSMS.presentation.compose

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.moez.QKSMS.R
import com.moez.QKSMS.data.model.Contact
import com.moez.QKSMS.presentation.base.QkAdapter
import com.moez.QKSMS.presentation.base.QkViewHolder
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.contact_list_item.view.*

class ContactAdapter(private val context: Context, data: Flowable<List<Contact>>) : QkAdapter<Contact, QkViewHolder>(data) {

    val contactSelected: Subject<Contact> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QkViewHolder {
        val layoutRes = R.layout.contact_list_item
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(layoutRes, parent, false)
        return QkViewHolder(view)
    }

    override fun onBindViewHolder(holder: QkViewHolder, position: Int) {
        val contact = getItem(position)
        val view = holder.itemView

        view.clicks().subscribe { contactSelected.onNext(contact) }

        view.avatar.contact = contact
        view.name.text = contact.name
        view.address.text = contact.address
    }

}