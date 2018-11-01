package aswanabidin.footballmatchschedule.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppScheduler : IAppScheduler {
    override fun view(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun result(): Scheduler {
        return Schedulers.io()
    }

}
