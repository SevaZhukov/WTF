package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.database.DatabaseError;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.model.firebase.Task;
import com.mrswimmer.shift.presentation.base.BaseFragment;
import com.mrswimmer.shift.presentation.main.fragment.tasks.recycler.TaskAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TasksFragment extends BaseFragment implements TasksFragmentView {

    @InjectPresenter
    TasksFragmentPresenter presenter;

    @ProvidePresenter
    public TasksFragmentPresenter presenter() {
        return new TasksFragmentPresenter();
    }

    @BindView(R.id.tasks_recycler)
    RecyclerView recyclerView;

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.setRecyclerData();
        Log.i("code", "setdata");
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tasks;
    }


    @Override
    public void setAdapter(List<Task> tasks) {
        recyclerView.setAdapter(new TaskAdapter(tasks));
    }

    @Override
    public void showErrorToast(DatabaseError e) {
        showToast(e.getMessage());
    }
}
