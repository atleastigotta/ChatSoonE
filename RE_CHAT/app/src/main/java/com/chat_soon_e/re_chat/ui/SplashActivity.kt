package com.chat_soon_e.re_chat.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.chat_soon_e.re_chat.ApplicationClass.Companion.mSharedPreferences
import com.chat_soon_e.re_chat.data.entities.User
import com.chat_soon_e.re_chat.data.local.AppDatabase
import com.chat_soon_e.re_chat.data.remote.auth.USER_ID
import com.chat_soon_e.re_chat.databinding.ActivitySplashBinding
import com.chat_soon_e.re_chat.ui.ExplainActivity.ExplainActivity
import com.chat_soon_e.re_chat.utils.permissionGrantred
import com.chat_soon_e.re_chat.utils.saveID
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.skydoves.transformationlayout.TransformationCompat

// BaseActivity를 상속받기 때문에 BaseActivity 안에서 onCreate() 실행되면서 자동적으로 뷰 바인딩을 해준다.
// 따라서 SplashActivity에서는 그 코드를 쓸 필요가 없다.
// <> Generic: 아직 정의되지 않은 타입을 정의할 때 사용하는데, 여기서는 이 안에 어떤 뷰 바인딩을 할 것인지를 넣어준다.
@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity(),
    SplashView {
    val TAG = "splashtest"
    // BaseActivity onCreate()에서 바인딩 끝나고 자동적으로 호출이 되게끔 해준다.
    // 로그인 성곡

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        onTransformationStartContainer() // should be called before super.onCreate().
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //defaulValue=0, 설명창 보임=1, 더이상보이지 않음=2
        mSharedPreferences=getSharedPreferences("explain", MODE_PRIVATE)
        val isExplain= mSharedPreferences.getInt("explain", 0)

        if(isExplain==0 || isExplain==1) {
            val intent = Intent(this@SplashActivity, ExplainActivity::class.java)
            startActivity(intent)
        }
        else if(isExplain==2)
        {
            if(!permissionGrantred(this)) {
                val intent = Intent(this@SplashActivity, PermissionActivity::class.java)
                startActivity(intent)
            }
        }
        loginPermission()
        binding.splashKakaoBtn.setOnClickListener {
            if(binding.splashKakaoBtn.isVisible){
                login()
            }
        }
        binding.splashStartBtn.setOnClickListener {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            TransformationCompat.startActivity(binding.splashTransformationLayout, intent)
            finish()
        }
        //로그인이 되었다면 로그인은 안뜨게====O
        //데이터 다운이 완료되면 시작하기 버튼 활성화====X
    }

    //Token 존재 확인, 즉 로그인 확인
    fun loginPermission(){
        if (AuthApiClient.instance.hasToken()) {
            //Token 유효성 검증
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    //로그인 필요
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        binding.splashKakaoBtn.visibility=View.VISIBLE
                        binding.splashKakaoIv.visibility=View.VISIBLE
                    }
                    //기타 에러
                    else {
                        Log.d(TAG, error.message.toString())
                    }
                }
                //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                else {
                    Log.d(TAG, "토큰 유효")
                    binding.splashKakaoBtn.visibility=View.INVISIBLE
                    binding.splashKakaoIv.visibility=View.INVISIBLE

                }
            }
        }
        //토큰 없음(로그아웃 혹은 연결 끊김)
        else {
            binding.splashKakaoBtn.visibility=View.VISIBLE
            binding.splashKakaoIv.visibility=View.VISIBLE
        }
    }

    //카카오계정 로그인
    private fun login(){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                binding.splashKakaoBtn.visibility=View.INVISIBLE
                binding.splashKakaoIv.visibility=View.INVISIBLE
                saveUserInfo("login")
            }
        }

        //카카오톡 로그인 가능하다면 카카오톡으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    binding.splashKakaoBtn.visibility=View.INVISIBLE
                    binding.splashKakaoIv.visibility=View.INVISIBLE

                    saveUserInfo("login")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    //로그아웃
    private fun logout(){
        UserApiClient.instance.logout { error->
            if(error!=null)
                Log.d(TAG, "로그아웃 실패")
            else{
                val user=AppDatabase.getInstance(this)!!.userDao()
            }
        }
    }
    //탈퇴: 계정 연결 끊기
    private fun withdraw() {
        saveUserInfo("withdraw")
        UserApiClient.instance.unlink { error ->
            if (error != null)
                Log.e(TAG, "연결끊기 실패", error)
            else {
                Log.d(TAG, "연결 끊기 성공")
            }
        }
    }
    //User 정보 업데이트 및 생성
    private fun saveUserInfo(state:String){
        UserApiClient.instance.me { user, error ->
            if (error != null){
                Log.d(TAG, "사용자 정보 가져오기 실패")
            }
            else {
                if (user != null) {
                    //Log.d(TAG, user.id.toString())
                    val database=AppDatabase.getInstance(this)!!
                    val dao=database.userDao()
                    if(state=="login"){
                        //id 암호화(encrypted사용) 후 spf 저장, 일단은 그냥 local 사용해 저장=========================
                        USER_ID=user.id
                        saveID(user.id)
                        //==================================
                        var users=dao.getUser(user.id)
                        if(users==null){
                            //유저 인포 저장
                            dao.insert(User(user.id, user.kakaoAccount?.profile?.nickname.toString(), user.kakaoAccount?.email.toString(), "activate"))
                        }else{
                            if(users.status=="delete")
                            //유저 인포 업데이트
                                dao.update(User(user.id, user.kakaoAccount?.profile?.nickname.toString(), user.kakaoAccount?.email.toString(), "activate"))
                            else if(users.status=="inactivate")
                                dao.updateStatus(user.id, "activate")
                        }
                    }
                    //로그아웃 시
                    else if(state=="logout"){
                        saveID(-1)
                        dao.updateStatus(user.id, "inactivate")
                    }
                    //탈퇴 시
                    else if(state=="withdraw")
                        saveID(-1)
                        dao.updateStatus(user.id, "delete")
                    //Log.d(TAG, dao.getUsers().toString())
                }
            }
        }
    }

    //인터넷 연결 확인====추후 서버와의 연동시
    fun getConnectivityStatus():Boolean {
        // 네트워크 연결 상태 확인하기 위한 ConnectivityManager 객체 생성
        val cm = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {

            // 기기가 마시멜로우 버전인 Andorid 6 이상인 경우
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 활성화된 네트워크의 상태를 표현하는 객체
                val nc = cm.getNetworkCapabilities(cm.activeNetwork)
                if (nc != null) {
                    if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.d(TAG, "와이파이 연결됨")
                    } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.d(TAG, "셀룰러 통신 사용")
                    }
                    return true
                } else {
                    Log.d(TAG, "인터넷 연결 안됨")
                    return false
                }
            } else {
                // 기기 버전이 마시멜로우 버전보다 아래인 경우
                // getActiveNetworkInfo -> API level 29에 디플리케이트 됨
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null) {
                    // 연결된 네트워크 확인
                    if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                        Log.d(TAG, "와이파이 연결됨")
                    } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                        Log.d(TAG, "셀룰러 통신 사용")
                    }
                    return true
                } else {
                    Log.d(TAG, "인터넷 연결 안됨")
                    return false
                }
            }
        }
        else
            return false
    }
    override fun onBackPressed() {
        finish()
    }
    override fun onAutoLoginLoading() {
        TODO("Not yet implemented")
    }

    override fun onAutoLoginSuccess() {
        TODO("Not yet implemented")
    }

    override fun onAutoLoginFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}