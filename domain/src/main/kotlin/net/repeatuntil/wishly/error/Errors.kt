package net.repeatuntil.wishly.error

// Common errors are added to the errors listed after the colon.
// With sealed classes/interfaces inheritance is reversed.
sealed interface CommonError : UserError, RegisterUserError, LoginUserError {
}

class CommonException(val error: CommonError) : Exception(error.toString())

sealed interface UserError : RegisterUserError {
    object InvalidPasswordFormat : UserError
}

class UserException(val error: UserError) : Exception(error.toString())

sealed interface RegisterUserError {
    object MissingEmail : RegisterUserError
    object MissingPassword : RegisterUserError
}

class RegisterUserException(val error: RegisterUserError) : Exception(error.toString())

sealed interface LoginUserError {
    object MissingEmail : LoginUserError
    object MissingPassword : LoginUserError
    object UserNotFound : LoginUserError
    object WrongPassword : LoginUserError
    object UserAlreadyLoggedIn: LoginUserError
    object SessionNotFound: LoginUserError
}

class LoginUserException(val error: LoginUserError) : Exception(error.toString())
