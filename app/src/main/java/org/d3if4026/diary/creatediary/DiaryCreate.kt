package org.d3if4026.diary.creatediary


import android.app.Application
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import org.d3if2036.diary.database.Diary

import org.d3if4026.diary.R
import org.d3if4026.diary.database.DiaryDatabase
import org.d3if4026.diary.database.DiaryDatabaseDao
import org.d3if4026.diary.databinding.FragmentDiaryCreateBinding
import org.d3if4026.diary.diary.DiaryCreateViewModel
import org.d3if4026.diary.diary.DiaryViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DiaryCreate : Fragment() {

    private lateinit var binding: FragmentDiaryCreateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_diary_create, container,false)

        binding.setLifecycleOwner(this)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataDiary = Diary()
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
////        val waktu = LocalDateTime.now()
//        val formatTanggal = DateTimeFormatter.ofPattern("EEEE, dd MMM  yyyy")
//        val format = waktu.format(formatTanggal)
        val sdf = SimpleDateFormat("dd, MMMM, yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        dataDiary.isidiary = binding.etData.text.toString()
        dataDiary.tanggalDiary = currentDate
        when(item.itemId){
            R.id.selesai -> saveDiary(dataSource,dataDiary,application)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveDiary(dataSource : DiaryDatabaseDao, diary : Diary, application : Application){
        DiaryCreateViewModel(dataSource,diary,application).onSave()
        binding.etData.text.clear()
    }


}
