package com.example.nytimes.ui.article

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.graduationapp.CoroutineTestRule
import com.example.graduationapp.getOrAwaitValue
import com.example.nytimes.data.ArticleResponse
import com.example.nytimes.data.repo.DefaultRepo
import com.example.nytimes.ui.ArticleViewModel
import com.example.nytimes.ui.ArticleViewModelFactory
import com.example.nytimes.ui.details.DetailsViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ArticlesFragmentTest : TestCase(){
    private lateinit var viewModel: ArticleViewModel
    lateinit var repository: DefaultRepo
    var article:ArticleResponse?=ArticleResponse(null,false, arrayListOf(),0,"OK")

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @get:Rule
    val taskRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        repository = mockk(relaxed = true)
    }

    @Test
    fun testWhenViewModelInit_VerifyFetchMoviesCalled() {
        // Given
        coEvery { repository.fetchArticle() } returns mockk()

        // When
        viewModel = ArticleViewModel(ApplicationProvider.getApplicationContext(),repository)
        viewModel.getAllArticle()

        // Then
        coVerify { repository.fetchArticle() }
    }


    @Test
    fun testWhenMoviesListReturnedSuccessfully_VerifyMoviesListChanged() {
        coEvery { repository.fetchArticle() } returns article

        viewModel = ArticleViewModel(ApplicationProvider.getApplicationContext(),repository)
        viewModel.getAllArticle()

        assertEquals(article, viewModel.allArticle.getOrAwaitValue())
    }

    @Test
    fun testMoviesViewModelFactory() {
        coEvery { repository.fetchArticle() } returns mockk(relaxed = true)

        val viewModel = ArticleViewModelFactory(ApplicationProvider.getApplicationContext(),repository).create(ArticleViewModel::class.java)

        assert(viewModel is ArticleViewModel)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testMoviesViewModelFactoryOfUnknownType_VerifyExceptionThrown() {

        ArticleViewModelFactory(ApplicationProvider.getApplicationContext(),repository).create(
            DetailsViewModel::class.java)
    }

    @After
    fun tear() {
        clearAllMocks()
    }




}