package aswanabidin.footballmatchschedule.utils

import io.reactivex.Scheduler

interface IAppScheduler {

    fun view(): Scheduler
    fun result(): Scheduler


}
