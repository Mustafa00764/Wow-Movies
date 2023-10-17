package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.wowmovie_.R


class HomeFragment : Fragment(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }
    private fun initViews(view: View) {
        loadFragments()

    }


    private fun loadFragments() {


    }
}