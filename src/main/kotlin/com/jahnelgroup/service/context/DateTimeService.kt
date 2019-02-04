package com.jahnelgroup.service.context

import java.time.Instant

interface DateTimeService {
    fun getInstant(): Instant
}