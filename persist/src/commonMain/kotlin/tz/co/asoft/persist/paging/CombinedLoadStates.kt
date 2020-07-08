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

data class CombinedLoadStates(
    val source: LoadStates,
    val mediator: LoadStates? = null
) {
    val refresh: LoadState = (mediator ?: source).refresh
    val prepend: LoadState = (mediator ?: source).prepend
    val append: LoadState = (mediator ?: source).append

    inline fun forEach(op: (LoadType, Boolean, LoadState) -> Unit) {
        source.forEach { type, state ->
            op(type, false, state)
        }
        mediator?.forEach { type, state ->
            op(type, true, state)
        }
    }

    internal companion object {
        val IDLE_SOURCE = CombinedLoadStates(
            source = LoadStates.IDLE
        )
        val IDLE_MEDIATOR = CombinedLoadStates(
            source = LoadStates.IDLE,
            mediator = LoadStates.IDLE
        )
    }
}
