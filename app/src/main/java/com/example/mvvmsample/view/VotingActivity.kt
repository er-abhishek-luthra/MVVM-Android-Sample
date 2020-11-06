package com.example.mvvmsample.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvpsample.presenter.VotingViewModel
import com.example.mvvmsample.R
import com.example.mvvmsample.pojo.President


class VotingActivity : AppCompatActivity() {
    private lateinit var mBtnVoteTrump: Button
    private lateinit var mBtnVoteBiden: Button
    private lateinit var mTvVoteCount: TextView
    private lateinit var mVotingViewModel: VotingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_user)
        mVotingViewModel = ViewModelProvider(this@VotingActivity).get(VotingViewModel::class.java)
        initViews()
        initObservables()
    }

    fun initViews() {
        mBtnVoteTrump = findViewById(R.id.btn_vote_trump)
        mBtnVoteBiden = findViewById(R.id.btn_vote_biden)
        mTvVoteCount = findViewById(R.id.tv_vote_count_details)
        mBtnVoteTrump.setOnClickListener {
            mVotingViewModel.voteFor(President.TRUMP)
        }
        mBtnVoteBiden.setOnClickListener {
            mVotingViewModel.voteFor(President.BIDEN)
        }
    }

    fun initObservables() {
        mVotingViewModel.voteCountDetailLiveData.observe(
            this@VotingActivity,
            object : Observer<String> {
                override fun onChanged(voteCountDetails: String?) {
                    mTvVoteCount.text = voteCountDetails
                }

            })
    }
}