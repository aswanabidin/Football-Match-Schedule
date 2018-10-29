package aswanabidin.footballmatchschedule.utils

import io.reactivex.Scheduler

class IAppSchedule {

    interface View {
        fun view(): Scheduler
        fun result(): Scheduler

    }

}
