package com.example.mvpsample.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mvvmsample.model.VotingModel
import com.example.mvvmsample.pojo.President
import com.example.mvvmsample.pojo.VoteCount

class VotingViewModel(val mVotingModel: VotingModel) : ViewModel() {

    constructor() : this(VotingModel())

    val voteCountDetailLiveData = MutableLiveData<String>().apply {
        value = ""
    }

    private val voteCountObserver = Observer<VoteCount> { voteCount ->
        val trumpVoteString = """${President.TRUMP.name} : ${voteCount.trumpVote}"""
        val bidenVoteString = """${President.BIDEN.name} : ${voteCount.bidenVote}"""
        voteCountDetailLiveData.value = """${trumpVoteString}   ${bidenVoteString}"""
    }

    init {
        mVotingModel.voteCountLiveData.observeForever(voteCountObserver)
    }


    fun voteFor(president: President) {
        mVotingModel.vote(president)
    }


    override fun onCleared() {
        mVotingModel.voteCountLiveData.removeObserver(voteCountObserver)
    }
}