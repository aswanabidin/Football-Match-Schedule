package aswanabidin.footballmatchschedule.features.match.last

import aswanabidin.footballmatchschedule.features.match.MatchFragmentContracts
import aswanabidin.footballmatchschedule.model.match.MatchEventModel
import aswanabidin.footballmatchschedule.model.match.MatchEventPresenter
import aswanabidin.footballmatchschedule.model.match.MatchEventResponse
import aswanabidin.footballmatchschedule.utils.IAppScheduler
import aswanabidin.footballmatchschedule.utils.TestAppScheduler
import io.reactivex.Flowable
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {

    @Mock
    lateinit var mView: MatchFragmentContracts.LastMatchFragmentView

    @Mock
    lateinit var matchEventPresenter: MatchEventPresenter
    lateinit var scheduler: IAppScheduler
    lateinit var mPresenter: LastMatchPresenter
    lateinit var matchEventResponse: MatchEventResponse
    lateinit var footballMatch: Flowable<MatchEventResponse>
    private val event = mutableListOf<MatchEventModel>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestAppScheduler()
        matchEventResponse = MatchEventResponse(event)
        footballMatch = Flowable.just(matchEventResponse)
        mPresenter = LastMatchPresenter(mView, matchEventPresenter, scheduler)
        Mockito.`when`(matchEventPresenter.getLastMatch("12345")).thenReturn(footballMatch)
    }

    @Test
    fun getFootballLastMatch() {
        mPresenter.getFootballLastMatch()
        Mockito.verify(mView).showProgress()
        Mockito.verify(mView).displayFootballMatch(event)
        Mockito.verify(mView).hideProgress()
    }
}