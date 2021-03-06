package com.gmail.martsulgp.gympo.data.service

import com.gmail.martsulgp.gympo.data.model.request.LogInRequest
import com.gmail.martsulgp.gympo.data.model.request.RegisterRequest
import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest
import com.gmail.martsulgp.gympo.data.model.response.TrainingResponse
import com.gmail.martsulgp.gympo.data.model.response.UserDataResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface BackendlessApi {

    // Auth block
    @POST("users/login")
    fun logInUser(@Body profile: LogInRequest): Observable<UserDataResponse>

    @POST("users/register")
    fun regUser(@Body profile: RegisterRequest): Observable<UserDataResponse>

    @GET("users/isvalidusertoken/{token}")
    fun checkToken(@Path("token") param: String): Single<Boolean>

    @GET("users/logout")
    fun logOut(@Header("user-token") token: String): Observable<Void>

    // User block
    @GET("users/{userId}?props")
    fun getUserInfo(@Path("userId") id: String): Observable<UserDataResponse>

    @PUT("users/{userId}")
    fun updateUser(@Path("userId") id: String, @Body info: UserDataRequest): Observable<UserDataResponse>

    @POST("users/social/facebook/sdk/login")
    fun loginUserWithFB(@Body accessToken: String): Single<UserDataResponse>

    // Exercises block

    @GET
    fun getTrainings(@Url url: String): Observable<List<TrainingResponse>>
//
//    @GET
//    fun getExercises(@Url string: String): Observable<TrainingsFeed>
//
//    @GET
//    abstract fun getSets(@Url url: String): Observable<ExercisesFeed>
//
//
//    @POST("data/timetable")
//    fun newTraining(@Body feed: TrainingsFeed): Observable<TrainingsFeed>
//
//    @POST("data/exercises")
//    fun newExercise(@Body feed: ExercisesFeed): Observable<ExercisesFeed>
//
//    @PUT
//    abstract fun updateTraining(@Url string: String, @Body feed: TrainingsFeed): Observable<TrainingsFeed>
//
//    @POST
//    abstract fun updateExercise(@Url string: String, @Body feed: ExercisesFeed): Observable<ExercisesFeed>
//
//
//    @DELETE
//    abstract fun delItem(@Url url: String): Observable<DeleteResponse>
//
//
//    @PUT("data/Timetable/{objectId}/exercise")
//    abstract fun addRelation(@Path("objectId") id: String, @Body relation: Relation): Observable<Int>
//
//
//    @PUT("data/Exercises/{objectId}/sets")
//    abstract fun addSetRelation(@Path("objectId") id: String, @Body relation: Relation): Observable<Int>
//
//    @POST("data/sets")
//    abstract fun newSet(@Body feed: SetsFeed): Observable<SetsFeed>


    companion object {
        fun create(): BackendlessApi {
            val s = "https://api.backendless.com/C5528678-BFAB-F70F-FF82-D2CB10670100/0F2D67BF-E621-6650-FFB0-F02FF5CFC100/"
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .baseUrl(s)
                    .client(OkHttpClient.Builder()
                            .readTimeout(20, TimeUnit.SECONDS)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .addInterceptor(
                                    HttpLoggingInterceptor()
                                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .build()
            return retrofit.create(BackendlessApi::class.java)
        }
    }
}