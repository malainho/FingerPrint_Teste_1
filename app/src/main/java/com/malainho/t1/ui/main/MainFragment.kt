package com.malainho.t1.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt.*
import com.malainho.t1.R
import com.malainho.t1.extensions.promptBiometricChecker

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        promptBiometricChecker(
            "Desbloqueia Por Favor",
            "Use Seu FingerPrint",
            "Cancelar",
            confirmationRequired = true,
            null,
            { result ->
                when (result.authenticationType) {
                    AUTHENTICATION_RESULT_TYPE_BIOMETRIC -> {
                        toast("sucesso fingerprint or face!")
                    }
                    AUTHENTICATION_RESULT_TYPE_UNKNOWN -> {
                        toast("sucesso por meio legado ou desconhecido")
                    }
                    AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL -> {
                        toast("sucesso pin, pattern or password")
                    }
                }
            },
            { error, errorMsg ->
                toast("$error: $errorMsg")
            })
    }

    private fun toast(texto : String){
        Toast.makeText(requireContext(), texto, Toast.LENGTH_LONG).show()
    }
}