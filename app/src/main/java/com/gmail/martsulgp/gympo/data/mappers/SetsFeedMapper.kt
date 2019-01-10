package com.gmail.martsulgp.gympo.data.mappers

import com.gmail.martsulgp.gympo.data.model.entity.SetsFeed
import com.gmail.martsulgp.gympo.data.model.response.SetsResponse

object SetsFeedMapper {
    fun map(setResponse: SetsResponse?) = SetsFeed(
            restTime = setResponse?.restTime ?: 0L,
            repWeight = setResponse?.repWeight ?: 0,
            created = setResponse?.created ?: 0L,
            updated = setResponse?.updated ?: 0L,
            reqTime = setResponse?.reqTime ?: 0L,
            setNumber = setResponse?.setNumber ?: 0,
            repsNum = setResponse?.repsNum ?: 0,
            ownerId = setResponse?.ownerId ?: "OwnerId not defined",
            objectId = setResponse?.objectId ?: "ObjectId not defined"
    )

    fun map(listSetsResponse: List<SetsResponse>?) : List<SetsFeed> = listSetsResponse?.map { map(it) }?.toList() ?: listOf()
}