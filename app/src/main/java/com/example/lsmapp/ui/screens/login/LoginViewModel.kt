import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lsmapp.data.repository.AuthRepository
import com.example.lsmapp.ui.screens.login.LoginState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState

    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome = _navigateToHome

    fun updateEmail(value: String) { _email.value = value }
    fun updatePassword(value: String) { _password.value = value }

    fun login() {
        if (email.value.isBlank() || password.value.isBlank()) {
            _loginState.value = LoginState.Error("Please fill all fields")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val result = authRepository.signIn(email.value, password.value)

            if (result.isSuccess) {
                _loginState.value = LoginState.Success
                _navigateToHome.emit(Unit)
            } else {
                _loginState.value = LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}
