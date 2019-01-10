package com.gmail.martsulgp.gympo.presentation.menu.trainingsMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed
import com.gmail.martsulgp.gympo.data.repository.TrainingsRepository
import com.gmail.martsulgp.gympo.presentation.menu.adapters.TrainingsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject

class TrainingsFragment : MvpFragment(), TrainingsView {

    private val trainingsRepository: TrainingsRepository by inject()

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: TrainingsPresenter

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = TrainingsPresenter(trainingsRepository)

    @ProvidePresenterTag(presenterClass = TrainingsPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Trainings list"

    @BindView(R.id.trainingsFragmentHeader)
    lateinit var header: TextView

    @BindView(R.id.trainings_list_recyclerView)
    lateinit var trainingsRecycler: RecyclerView

    @BindView(R.id.trainings_list_fab)
    lateinit var fab: FloatingActionButton

    companion object {
        fun newInstance() = TrainingsFragment()
    }
    private val adapter = TrainingsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_trainings, container, false)
        ButterKnife.bind(this, view)

        trainingsRecycler.apply {
            adapter = this@TrainingsFragment.adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        presenter.downloadTrainings()





        return view
    }




    override fun viewTrainings(listTrainings: List<TrainingsFeed>) {
        adapter.notifyDataSetChanged()
        adapter.dataChanged(listTrainings)
    }

}