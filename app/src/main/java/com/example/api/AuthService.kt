import com.example.api.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/api.php") // Sesuaikan dengan lokasi skrip PHP di server
    fun login(@Body user: User): Call<ApiResponse>
}
