package com.projectx.homework3_7month.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.projectx.homework3_7month.R
import com.projectx.homework3_7month.databinding.FragmentTaskListBinding
import com.projectx.homework3_7month.presentation.adapter.TaskAdapter
import com.projectx.homework3_7month.presentation.viewmodel.TaskViewModel


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTask()
        initialize()
        showTask()
    }

    private fun addTask() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }
    }

    private fun initialize() {
        taskAdapter = TaskAdapter(emptyList(), { task ->
            val action = TaskListFragmentDirections.actionTaskListFragmentToDetailFragment(task.id)
            findNavController().navigate(action)
        }, { task ->
            viewModel.deleteTask(task)
        })
        binding.rvTask.adapter = taskAdapter
        taskAdapter.attachSwipeToRecyclerView(binding.rvTask)
    }

    private fun showTask() {
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.updateTasks(tasks)
        }
        viewModel.loadTasks()
    }
}


