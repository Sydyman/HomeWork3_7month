package com.projectx.homework3_7month.presentation.fragments.taskList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.projectx.addtask.TaskActivity
import com.projectx.homework3_7month.R
import com.projectx.homework3_7month.databinding.FragmentTaskListBinding
import com.projectx.homework3_7month.presentation.fragments.adapter.TaskAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TaskListFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskListBinding.inflate(layoutInflater)
    }
    private val viewModel: TaskViewModel by viewModel()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    companion object{
        fun newInstance():TaskListFragment{
            return TaskListFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTask()
        initialize()
        showTask()
        viewModel.loadTasks()
        viewModel.fetchTask()
    }

    private fun addTask() {
        binding.btnAdd.setOnClickListener {
            val intent = TaskActivity.createIntent(requireContext())
            startActivity(intent)
        }
    }

    private fun initialize() {
        taskAdapter = TaskAdapter(emptyList(), { task ->
            viewModel.viewModelScope.launch {
                viewModel.fetchTask()
            }
            val action = TaskListFragmentDirections.actionTaskListFragmentToDetailFragment(task)
            findNavController().navigate(action)
        }, { task ->
            viewModel.deleteTask(task)
        })
        binding.rvTask.adapter = taskAdapter
        taskAdapter.attachSwipeToRecyclerView(binding.rvTask)
    }

    private fun showTask() {
        viewModel.viewModelScope.launch {
            viewModel.taskFlow.collectLatest {
                taskAdapter.updateTasks(it)
            }
        }


    }
}


