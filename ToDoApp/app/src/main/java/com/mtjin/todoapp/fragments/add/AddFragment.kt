package com.mtjin.todoapp.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mtjin.todoapp.R
import com.mtjin.todoapp.data.ToDoViewModel
import com.mtjin.todoapp.data.models.ToDoData
import com.mtjin.todoapp.fragments.ShareViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        //메뉴 세팅
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val vaildation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (vaildation) {
            //데이터베이스에 데이터 추가
            val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "저장 성공", Toast.LENGTH_SHORT).show()
            //네비게이션 back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "저장 실패", Toast.LENGTH_SHORT).show()
        }
    }


}