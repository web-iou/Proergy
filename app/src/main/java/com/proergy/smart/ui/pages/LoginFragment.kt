package com.proergy.smart.ui.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.proergy.smart.R
import com.proergy.smart.apis.Http
import com.proergy.smart.databinding.FragmentLoginBinding
import com.proergy.smart.utils.AesUtil
import kotlinx.coroutines.launch

class
LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            var password = binding.etPassword.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                password= AesUtil.encrypt(password)
                val response= Http.user.login(username,password)
            }
        }
    }

}
