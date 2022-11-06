package com.barros9.hotelnow.data.repository

import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result
import com.barros9.hotelnow.domain.model.getResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal fun singleSourceOfTruthStrategy(
    readLocalData: suspend () -> List<Hotel>,
    readRemoteData: suspend () -> List<Hotel>,
    saveLocalData: suspend (List<Hotel>) -> Unit,
): Flow<Result<List<Hotel>>> = flow {
    val localData = getResult { readLocalData() }

    if (localData is Result.Success && localData.data.isNotEmpty()) {
        emit(localData)
    } else {
        val remoteData = getResult { readRemoteData() }
        if (remoteData is Result.Success && remoteData.data.isNotEmpty()) {
            saveLocalData(remoteData.data)
            val localDataUpdated = getResult { readLocalData() }
            emit(localDataUpdated)
        } else {
            emit(Result.Error((remoteData as Result.Error).throwable))
        }
    }
}