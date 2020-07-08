/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tz.co.asoft.persist.paging

import tz.co.asoft.persist.paging.LoadState.NotLoading.Companion.Incomplete

data class LoadStates(
    val refresh: LoadState,
    val prepend: LoadState,
    val append: LoadState
) {
    init {
        require(!refresh.endOfPaginationReached) {
            "Refresh state may not set endOfPaginationReached = true"
        }
    }

    inline fun forEach(op: (LoadType, LoadState) -> Unit) {
        op(LoadType.REFRESH, refresh)
        op(LoadType.PREPEND, prepend)
        op(LoadType.APPEND, append)
    }

    internal companion object {
        val IDLE = LoadStates(
            refresh = Incomplete,
            prepend = Incomplete,
            append = Incomplete
        )
    }
}
