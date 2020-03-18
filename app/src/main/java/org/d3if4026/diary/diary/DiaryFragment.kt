package org.d3if4026.diary.diary


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import org.d3if4026.diary.R
import org.d3if4026.diary.database.DiaryDatabase
import org.d3if4026.diary.databinding.FragmentDiaryBinding

/**
 * A simple [Fragment] subclass.
 */
class DiaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentDiaryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_diary, container , false)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
        val viewModelFactory = DiaryViewModelFactory(dataSource,application)

        val diaryViewModelFactory = ViewModelProviders.of(this,viewModelFactory).get(DiaryViewModel::class.java)


        binding.creatediary.setOnClickListener {
            it.findNavController().navigate(R.id.action_diaryFragment_to_diaryCreate)
        }
        setHasOptionsMenu(true)
        binding.setLifecycleOwner(this)
        binding.diaryViewModel = diaryViewModelFactory

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.hapus,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
        when(item.itemId){
            R.id.hapus -> DiaryViewModel(dataSource,application).onDelete()
        }

        return super.onOptionsItemSelected(item)
    }


}
