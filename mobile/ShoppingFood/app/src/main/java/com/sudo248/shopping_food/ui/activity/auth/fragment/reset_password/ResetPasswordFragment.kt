package com.sudo248.shopping_food.ui.activity.auth.fragment.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sudo248.shopping_food.databinding.FragmentResetPasswordBinding
import com.sudo248.shopping_food.domain.model.ResetPassRequest
import com.sudo248.shopping_food.ui.activity.auth.AuthViewModel

class ResetPasswordFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()

    private lateinit var binding: FragmentResetPasswordBinding
    private val args: ResetPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        binding.txtReset.setOnClickListener {
            viewModel.resetPassword(ResetPassRequest(
                args.email,
                binding.edtPassword.text.toString()
            ))
        }
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.resetPasswordSuccess.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}