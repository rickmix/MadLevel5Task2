package com.example.madlevel5task2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        getActivity()?.setTitle("Add game");
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            addGame()
        }

        observeGame()
    }

    private fun observeGame() {
        viewModel.success.observe(viewLifecycleOwner, androidx.lifecycle.Observer { success ->
            findNavController().navigate(
                R.id.action_SecondFragment_to_FirstFragment
            )
        })
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun addGame() {
        val gameTitle = TextInputTile.text.toString()
        val gamePlatform = TextInputPlatform.text.toString()
        val dayTime = DateDay.text.toString()
        val monthTime = DateMonth.text.toString()
        val yearTime = DateYear.text.toString()
        val timeFull = "$yearTime-$monthTime-$dayTime"

        val format = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date: Date = format.parse(timeFull)
            viewModel.insertGame(gameTitle, date, gamePlatform)
            Toast.makeText(
                activity,
                "Game added", Toast.LENGTH_SHORT
            ).show()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }
}