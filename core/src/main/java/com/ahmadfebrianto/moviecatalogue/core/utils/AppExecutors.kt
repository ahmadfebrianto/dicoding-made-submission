package com.ahmadfebrianto.moviecatalogue.core.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors constructor(
    private val diskIO: Executor
) {
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

}
