package aswanabidin.footballmatchschedule.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedule : IAppSchedule.View {
    override fun view() = AndroidSchedulers.mainThread()
    override fun result() = Schedulers.io()
}
