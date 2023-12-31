/*
 * Copyright 2022 Moriafly
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * SOURCE: https://github.com/Moriafly/Regret/
 */

package com.moriafly.regret

/**
 * @author Moriafly
 * https://github.com/Moriafly/Regret/
 *
 */
class Action(
    val key: String,
    val value: Any
) {
    override fun equals(other: Any?): Boolean {
        return other is Action && other.key == key && other.value == value
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}