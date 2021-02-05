package com.mtjin.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mtjin.todoapp.R
import com.mtjin.todoapp.data.ToDoViewModel
import com.mtjin.todoapp.data.models.Priority
import com.mtjin.todoapp.data.models.ToDoData
import com.mtjin.todoapp.fragments.ShareViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: ShareViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        //메뉴세팅
        setHasOptionsMenu(true)

        view.current_title_et.setText(args.currentItem.title)
        view.current_description_et.setText(args.currentItem.descreiption)
        view.current_priorities_spinner.setSelection(parsePriority(args.currentItem.priority))
        view.current_priorities_spinner.onItemSelectedListener = mSharedViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = current_title_et.text.toString()
        val description = current_description_et.text.toString()
        val getPriority = current_priorities_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val updatedItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "업데이트 성공 :)", Toast.LENGTH_SHORT).show()
            //네비 백
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "업데이트 실패 :(", Toast.LENGTH_SHORT).show()
        }
    }
}