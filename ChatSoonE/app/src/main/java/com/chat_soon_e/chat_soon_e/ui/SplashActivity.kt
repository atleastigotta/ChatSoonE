package com.chat_soon_e.chat_soon_e.ui

import android.os.Handler
import android.os.Looper
import com.chat_soon_e.chat_soon_e.data.remote.auth.AuthService
import com.chat_soon_e.chat_soon_e.databinding.ActivitySplashBinding

// BaseActivity를 상속받기 때문에 BaseActivity 안에서 onCreate() 실행되면서 자동적으로 뷰 바인딩을 해준다.
// 따라서 SplashActivity에서는 그 코드를 쓸 필요가 없다.
// <> Generic: 아직 정의되지 않은 타입을 정의할 때 사용하는데, 여기서는 이 안에 어떤 뷰 바인딩을 할 것인지를 넣어준다.
class SplashActivity: BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate),
    SplashView {

    // BaseActivity onCreate()에서 바인딩 끝나고 자동적으로 호출이 되게끔 해준다.
    override fun initAfterBinding() {
        // SplashActivity에서 2초가 지난 뒤 autoLogin() 수행
        Handler(Looper.getMainLooper()).postDelayed({
            // autoLogin()
            startNextActivity(MainActivity::class.java)
        }, 2000)
    }

    private fun autoLogin() {
        AuthService.autoLogin(this)
    }

    override fun onAutoLoginLoading() {

    }

    override fun onAutoLoginSuccess() {
        startActivityWithClear(MainActivity::class.java)
    }

    override fun onAutoLoginFailure(code: Int, message: String) {
        startActivityWithClear(LoginActivity::class.java)
    }
}