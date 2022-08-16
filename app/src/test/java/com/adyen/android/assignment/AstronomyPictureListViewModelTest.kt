package com.adyen.android.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adyen.android.assignment.api.model.AstronomyPicture
import com.adyen.android.assignment.api.repo.MainRepository
import com.adyen.android.assignment.ui.viewmodel.AstronomyPictureListViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import retrofit2.Response

class AstronomyPictureListViewModelTest : KoinTest {

    //Class to be tested
    lateinit var viewModel: AstronomyPictureListViewModel


    @MockK
    lateinit var repository: MainRepository


    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
        viewModel = AstronomyPictureListViewModel(repository)
    }

    @Test
    fun getPictureList(): Unit = runBlocking {
        val list = mockk<Response<List<AstronomyPicture>>>()
        every { runBlocking { repository.getAstronomyImageApiCall() } } returns (list)

        val result = repository.getAstronomyImageApiCall()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result,
            CoreMatchers.`is`(list)
        )
    }
}