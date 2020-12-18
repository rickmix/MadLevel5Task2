package com.example.madlevel5task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getActivity()?.setTitle("Game backlog");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        print(games)
        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_history -> {
                Toast.makeText(context, "REMOVE", Toast.LENGTH_SHORT).show()
                viewModel.deleteAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        rvGame.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rvGame.adapter = gameAdapter
        rvGame.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvGame)
        observeAddGame()
    }

    private fun observeAddGame() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this@FirstFragment.games.clear()
            this@FirstFragment.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                viewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}