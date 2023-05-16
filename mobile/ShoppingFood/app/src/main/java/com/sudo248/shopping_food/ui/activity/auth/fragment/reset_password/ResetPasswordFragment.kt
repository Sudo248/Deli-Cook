package com.sudo248.shopping_food.ui.activity.auth.fragment.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sudo248.shopping_food.databinding.FragmentResetPasswordBinding
import com.sudo248.shopping_food.ui.activity.auth.AuthViewModel

class ResetPasswordFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()

    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }
}