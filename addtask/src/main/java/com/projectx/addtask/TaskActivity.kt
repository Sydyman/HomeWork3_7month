package com.projectx.addtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.projectx.addtask.databinding.ActivityTaskBinding
import com.projectx.homework3_7month.presentation.fragments.taskList.TaskListFragment
import com.projectx.homework3_7month.presentation.fragments.taskList.TaskViewModel
import com.projectx.homework3_7month.presentation.model.TaskUI

class TaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTaskBinding.inflate(layoutInflater)
    }
    private val viewModel: AddTaskViewModel by viewModel()

    private lateinit var imageLauncher: ActivityResultLauncher<String>
    private var imageString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setUpListeners()
        imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.changePhoto.setImageURI(uri)
                imageString = uri.toString()
            }
        }


    }

    companion object{
        fun createIntent(context:Context):Intent{
            return Intent(context,TaskActivity::class.java)
        }
    }
    private fun setUpListeners() {
        binding.btnAddTask.setOnClickListener {
            binding.changePhoto.setOnClickListener {
                imageLauncher.launch("image/*")
            }
            val task = binding.tvTask2.text.toString()
            val date = binding.tvDate2.text.toString()
            val taskUI = TaskUI(0, task, date,imageString.toString())
            viewModel.insertTask(taskUI)
        }
        binding.btnBack.setOnClickListener {
         supportFragmentManager.beginTransaction()
             .replace(R.id.main,TaskListFragment.newInstance())
             .commit()
        }
    }
}