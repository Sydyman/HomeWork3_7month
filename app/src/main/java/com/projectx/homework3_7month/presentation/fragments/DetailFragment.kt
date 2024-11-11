package com.projectx.homework3_7month.presentation.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.projectx.homework3_7month.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.projectx.homework3_7month.databinding.FragmentDetailBinding
import com.projectx.homework3_7month.presentation.model.TaskUI
import com.projectx.homework3_7month.presentation.viewmodel.TaskViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel:TaskViewModel by viewModel()

    private var taskId: Int = -1
    private var taskUI: TaskUI? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            taskId = it.getInt("taskId")
        }
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskUI = tasks.find { it.id == taskId }
            taskUI?.let { updateUI(it) }
        }

        return binding.root
    }

    private fun updateUI(task: TaskUI) {
        binding.tvTask2.setText(task.taskName)
        binding.tvDate2.setText(task.taskDate)
        task.taskPhoto?.let {
            binding.addPhoto.setImageURI(Uri.parse(it))
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveChange.setOnClickListener {
            val updatedTask = taskUI?.copy(
                taskName = binding.tvTask2.text.toString(),
                taskDate = binding.tvDate2.text.toString())
            updatedTask?.let {
                viewModel.updateTask(it)
                findNavController().navigateUp()
            }
        }
    }
}
