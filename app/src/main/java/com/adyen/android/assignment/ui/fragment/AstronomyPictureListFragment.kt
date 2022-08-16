package com.adyen.android.assignment.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adyen.android.assignment.R
import com.adyen.android.assignment.api.model.AstronomyPicture
import com.adyen.android.assignment.databinding.FragmentAstronomyPictureListBinding
import com.adyen.android.assignment.utils.CheckInternetConnectionLiveData
import com.adyen.android.assignment.ui.adapter.AstronomyListAdapter
import com.adyen.android.assignment.ui.viewmodel.AstronomyPictureListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.api_error_screen.view.*
import kotlinx.android.synthetic.main.fragment_astronomy_picture_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AstronomyPictureListFragment : Fragment() {
    private var _binding: FragmentAstronomyPictureListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AstronomyListAdapter
    private var pictureList = mutableListOf<AstronomyPicture>()


    private val astronomyPictureListViewModel: AstronomyPictureListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        checkInternetConnectvityAndObserveData()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstronomyPictureListBinding.inflate(layoutInflater)
        return binding.root
    }

    fun errorViewClickLisener() {
        binding.errorView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.frameLayout.visibility = View.GONE
        binding.pictureListRecyclerView.visibility = View.GONE
        setupClickListeners()
    }

    private fun setObserver() {
        //using only one xml for response and error so checking the visibility here
        astronomyPictureListViewModel.astronomyPicture.observe(viewLifecycleOwner) {
            binding.errorView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            adapter.showListOnUI(it)
            pictureList.clear()
            pictureList.addAll(it)
        }
        astronomyPictureListViewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.latestItem.visibility = View.GONE
            errorViewClickLisener()
        }

        astronomyPictureListViewModel.showProgressbar.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                hideOtherViews()
            } else {
                showAstronomyListOnView()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun hideOtherViews() {
        binding.latestItem.visibility = View.GONE
        binding.errorView.visibility = View.GONE
        binding.frameLayout.visibility = View.GONE
        binding.pictureListRecyclerView.visibility = View.GONE
    }

    private fun showAstronomyListOnView() {
        binding.pictureListRecyclerView.visibility = View.VISIBLE
        binding.frameLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.latestItem.visibility = View.VISIBLE
    }

    private fun setupViews() = with(binding) {
        adapter = astronomyListAdapter
        pictureListRecyclerView.apply {
            binding.pictureListRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        pictureListRecyclerView.adapter = adapter
        binding.frameLayout.visibility = View.VISIBLE

        frameLayout.setOnClickListener {
            reorderListAlertDialog()
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            astronomyPictureListViewModel.getAstronomyPictureList()
        }
    }


    private fun setupClickListeners() {
        binding.errorView.error_view.refresh_btn.setOnClickListener {
            checkInternetConnectvityAndObserveData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*  the main reason of below implementation is navigate the detail page with position
      I added the navigation here because viewModel is basically use for storing the data so I feel UI
      related stuff we can keep in activity or fragment*/

    private
    val astronomyListAdapter: AstronomyListAdapter by lazy {
        AstronomyListAdapter { position ->
            val direction =
                AstronomyPictureListFragmentDirections.actionAstronomyPictureListFragmentToAstronomyPictureDetailsFragment(
                    astronomyPictureListViewModel._astronomyPicture.value!![position]
                )
            findNavController().navigate(direction)
        }
    }

    //alert dialog implementation to short the list element by title and date
    private fun reorderListAlertDialog() {
        val items = arrayOf("Reorder by title", "Reorder by Date")
        val checkedItem = 1

        context?.let { it ->
            MaterialAlertDialogBuilder(it)
                .setTitle(R.string.reorder_list).setSingleChoiceItems(
                    items, checkedItem
                ) { dialog, which ->
                    when (which) {
                        0 -> pictureList.sortBy { it.title }
                        1 -> pictureList.sortBy { it.date }
                    }
                }
                .setPositiveButton(R.string.apply) { _positiveBtn, which ->
                    adapter.showListOnUI(pictureList)

                }
                .setNegativeButton(R.string.cancel) { dialog, which ->
                    dialog.dismiss()
                }.show()
        }
    }

    private fun checkInternetConnectvityAndObserveData() {
        val connectivity = CheckInternetConnectionLiveData(requireContext())
        connectivity.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                setObserver()
            } else {
                errorViewClickLisener()
            }
        }
    }
}