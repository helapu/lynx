package com.helapu.lynx.common.youzan;

import com.helapu.lynx.common.youzan.entity.AuthTokenResult;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenService {
	
//	@Headers({"Content-Type:application/x-www-form-urlencoded"})
	@POST("oauth/token")
	Call<AuthTokenResult> getToken(@Body RequestBody map);

}
