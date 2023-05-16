package com.sudo248.shopping_food.data.api

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.sudo248.shopping_food.domain.Constant
import com.sudo248.shopping_food.domain.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

@ApiService(baseUrl = Constant.baseUrl)
@LoggingLever(level = Level.BODY)
@EnableAuthentication(Constant.TOKEN_KEY)
interface ApiService {
    // user
    @POST("auth/login")
    suspend fun signIn(@Body userRequest: UserRequest): Response<BaseResponse<TokenResponse>>

    @POST("auth/register")
    suspend fun signUp(@Body userRequest: UserRequest): Response<BaseResponse<User>>

    @GET("user/me")
    suspend fun getMe(): Response<BaseResponse<User>>

    // food
    @GET("food")
    suspend fun getAllFood(): Response<BaseResponse<List<Food>>>

    @GET("food/community")
    suspend fun getCommunity(): Response<BaseResponse<List<Community>>>

    @GET("food/{foodId}")
    suspend fun getFoodById(@Path("foodId") foodId: String): Response<BaseResponse<Food>>

    @POST("food/comment")
    suspend fun postComment(@Body comment: CommentRequest): Response<BaseResponse<Comment>>

    @POST("order")
    suspend fun postOrder(@Body order: Order): Response<BaseResponse<Order>>

    @GET("order/{orderId}")
    suspend fun getOrder(@Path("orderId") orderId: String): Response<BaseResponse<Order>>

    @PUT("order/{orderId}")
    suspend fun putOrder(@Path("orderId") orderId: String, @Body orderIngredient: OrderIngredient): Response<BaseResponse<Order>>

    @DELETE("order/{orderId}/delete/{ingredientId}")
    suspend fun deleteIngredient(@Path("orderId") orderId: String, @Path("ingredientId") ingredientId: String): Response<BaseResponse<Order>>

    @GET("order/active")
    suspend fun getActiveOrder(): Response<BaseResponse<Order>>

    @PUT("order/pay/{orderId}")
    suspend fun payOrder(@Path("orderId") orderId: String): Response<BaseResponse<String>>

}