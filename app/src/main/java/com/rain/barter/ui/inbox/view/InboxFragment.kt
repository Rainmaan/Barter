package com.rain.barter.ui.inbox.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rain.barter.R
import com.rain.barter.ui.inbox.viewmodel.InboxViewModel

class InboxFragment : Fragment() {

    private lateinit var inboxViewModel: InboxViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inboxViewModel = ViewModelProviders.of(this).get(InboxViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
