package com.kjh.umc_project9

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kjh.umc_project9.databinding.ActivityMainBinding
import com.kjh.umc_project9.model.CookRecipe
import com.kjh.umc_project9.model.Ministry
import com.kjh.umc_project9.model.Weather
import com.kjh.umc_project9.network.ApiService
import com.kjh.umc_project9.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onGetApi1Button()
        onGetApi2Button()
        onGetApi3Button()
        onSocialLoginButton()
        onSocialLogoutButton()
        onMembershipWithdrawal()
    }

    // API 1
    private fun onGetApi1Button() {
        binding.btGetApi1.setOnClickListener {
            retrofit = RetrofitBuilder.getApiService("https://api.odcloud.kr/")

            // 보건복지부 전국 지역보건의료기관 현황
            retrofit.getMinistryService(1,1,BuildConfig.API_KEY1).enqueue(object : Callback<Ministry> {
                override fun onResponse(call: Call<Ministry>, response: Response<Ministry>) {
                    if(response.isSuccessful) {
                        val responseData = response.body()

                        if(responseData != null) {
                            Log.d("Retrofit", "#### Response API 1 ####\n$responseData")
                        }
                    }else {
                        Log.d("Retrofit", "Response Not Successful ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Ministry>, t: Throwable) {
                    Log.e("Retrofit", "Error!", t)
                }
            })
        }
    }

    // API 2
    private fun onGetApi2Button() {
        binding.btGetApi2.setOnClickListener {
            retrofit = RetrofitBuilder.getApiService("https://openapi.foodsafetykorea.go.kr/")

            // 조리식품의 레시피
            retrofit.getRecipe().enqueue(object : Callback<CookRecipe> {
                override fun onResponse(call: Call<CookRecipe>, response: Response<CookRecipe>) {
                    if(response.isSuccessful) {
                        val responseData = response.body()

                        if(responseData != null) {
                            Log.d("Retrofit", "#### Response API 2 ####\n$responseData")
                        }
                    }else {
                        Log.d("Retrofit", "Response Not Successful ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CookRecipe>, t: Throwable) {
                    Log.e("Retrofit", "Error!", t)
                }
            })
        }
    }

    // API 3
    private fun onGetApi3Button() {
        binding.btGetApi3.setOnClickListener {
            retrofit = RetrofitBuilder.getApiService("https://api.openweathermap.org/")

            // 날씨
            retrofit.getWeather("Seoul", BuildConfig.API_KEY3).enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if(response.isSuccessful) {
                        val responseData = response.body()

                        if(responseData != null) {
                            Log.d("Retrofit", "#### Response API 3 ####\n$responseData")
                        }
                    }else {
                        Log.d("Retrofit", "Response Not Successful ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    Log.e("Retrofit", "Error!", t)
                }
            })
        }
    }

    // 소셜 로그인
    private fun onSocialLoginButton() {
        binding.btLogin.setOnClickListener {
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨

            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    when {
                        error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                            Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                            Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                            Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                            Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                            Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                            Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.ServerError.toString() -> {
                            Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                        }
                        error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                            Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                        }
                        else -> { // Unknown
                            Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else if (token != null) {
                    Toast.makeText(this, "카카오계정으로 로그인 성공 ${token.accessToken}", Toast.LENGTH_SHORT).show()
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Toast.makeText(this, "카카오톡으로 로그인 실패", Toast.LENGTH_SHORT).show()

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Toast.makeText(this, "카카오톡으로 로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    // 로그아웃
    private fun onSocialLogoutButton() {
        binding.btLogout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "카카오 로그아웃 실패 !", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "카카오 로그아웃 성공 !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 회원 탈퇴
    private fun onMembershipWithdrawal() {
        binding.btMembershipWithdrawal.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Toast.makeText(this, "회원 탈퇴 실패 !", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "회원 탈퇴 성공 !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}