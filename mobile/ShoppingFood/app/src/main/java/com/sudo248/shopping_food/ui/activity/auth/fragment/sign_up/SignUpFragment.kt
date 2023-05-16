package com.sudo248.shopping_food.ui.activity.auth.fragment.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sudo248.shopping_food.databinding.FragmentSignInBinding
import com.sudo248.shopping_food.databinding.FragmentSignUpBinding
import com.sudo248.shopping_food.domain.model.UserRequest
import com.sudo248.shopping_food.ui.activity.auth.AuthViewModel

class SignUpFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        setupUi()
        viewModel.signUpSuccess.observe(viewLifecycleOwner) {
            if (it) findNavController().popBackStack()
        }
        return binding.root
    }

    private fun setupUi() {
        binding.apply {
            txtSignUp.setOnClickListener {
                val name = edtName.text.toString().trim()
                val email = edtEmail.text.toString().trim()
                val password = edtPassword.text.toString().trim()

                viewModel.signUp(
                    UserRequest(
                        name = name,
                        email = email,
                        password = password
                    )
                )
            }

            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}