package com.sudo248.shopping_food.ui.activity.auth.fragment.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sudo248.shopping_food.databinding.FragmentSignInBinding
import com.sudo248.shopping_food.domain.model.UserRequest
import com.sudo248.shopping_food.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        setupUi()
        return binding.root
    }

    private fun setupUi() {
        binding.apply {
            txtSignIn.setOnClickListener {
                val email = edtEmail.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                viewModel.signIn(UserRequest(
                    email = email,
                    password = password
                ))
            }

            txtCreateAccount.setOnClickListener {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
            }

            txtForgotPassword.setOnClickListener {
                if (binding.edtEmail.text.isEmpty()) {
                    Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToResetPasswordFragment(binding.edtEmail.text.toString()))
                }
            }
        }
    }
}