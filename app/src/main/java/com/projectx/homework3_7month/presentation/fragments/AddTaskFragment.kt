package com.projectx.homework3_7month.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.projectx.homework3_7month.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.projectx.homework3_7month.databinding.FragmentAddTaskBinding
import com.projectx.homework3_7month.presentation.model.TaskUI
import com.projectx.homework3_7month.presentation.viewmodel.TaskViewModel


class AddTaskFragment : Fragment() {

    private val binding by lazy {
        FragmentAddTaskBinding.inflate(layoutInflater)
    }
    private val viewModel: TaskViewModel by viewModel()
    private lateinit var imageLauncher: ActivityResultLauncher<String>
    private var imageString: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.changePhoto.setImageURI(uri)
                imageString = uri.toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.changePhoto.setOnClickListener {
            imageLauncher.launch("image/*")
        }

        binding.btnAddTask.setOnClickListener {
            val task = binding.tvTask2.text.toString()
            val date = binding.tvDate2.text.toString()

            val taskUI = TaskUI(0, task, date, imageString.toString())
            viewModel.insertTask(taskUI)

            viewModel.insertMessage.observe(viewLifecycleOwner) { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)
            }
        }
    }
}



