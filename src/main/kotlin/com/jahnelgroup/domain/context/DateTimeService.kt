package com.jahnelgroup.domain.context

import java.time.Instant

interface DateTimeService {
    fun getInstant(): Instant
}