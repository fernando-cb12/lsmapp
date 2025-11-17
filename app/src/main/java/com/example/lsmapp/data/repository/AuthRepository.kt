class AuthRepository(
    private val client: SupabaseClient = SupabaseClient
) {

    private val auth = client.client.auth

    suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            auth.signUpWith(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWith(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signOut() = auth.signOut()
}
