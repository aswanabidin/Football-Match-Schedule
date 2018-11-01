package aswanabidin.footballmatchschedule.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestAppScheduler : IAppScheduler {

    override fun view(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun result(): Scheduler {
        return Schedulers.trampoline()
    }

}