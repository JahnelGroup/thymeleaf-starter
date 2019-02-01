package com.jahnelgroup.start.service.context

import java.time.Instant

interface DateTimeService {
    fun getInstant(): Instant
}