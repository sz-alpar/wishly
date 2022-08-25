package net.repeatuntil.wishly.error

sealed interface CommonError : RegisterUserError {
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
