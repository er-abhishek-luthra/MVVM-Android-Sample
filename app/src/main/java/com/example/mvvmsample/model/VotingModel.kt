package com.example.mvvmsample.model

import androidx.lifecycle.MutableLiveData
import com.example.mvvmsample.pojo.President
import com.example.mvvmsample.pojo.VoteCount

/*
For Simplicity, we are not consuming any backend api and are just storing the votes locally.
In real world Application, It will be much more complex than the implementaion below.
 */
class VotingModel {
    private var trumpVote: Int = 0
    private var bidenVote: Int = 0
    val voteCountLiveData: MutableLiveData<VoteCount> =
        MutableLiveData<VoteCount>().apply {
            value = VoteCount()
        }

    fun vote(president: President) {
        when (president) {
            President.TRUMP -> trumpVote++
            President.BIDEN -> bidenVote++
        }
        voteCountLiveData.value = VoteCount(trumpVote, bidenVote)
    }
}