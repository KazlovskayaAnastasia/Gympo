package com.gmail.martsulgp.workoutpartner.data

import com.gmail.martsulgp.workoutpartner.model.response.LogInRequest
import com.gmail.martsulgp.workoutpartner.model.response.RegisterRequest
import com.gmail.martsulgp.workoutpartner.model.response.UserInfoResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface BackendlessApi {

    @POST("users/login")
    abstract fun logInUser(@Body profile: LogInRequest): Observable<UserInfoResponse>

    @POST("users/register")
    abstract fun regUser(@Body profile: RegisterRequest): Observable<UserInfoResponse>

    @GET("users/isvalidusertoken/{token}")
    abstract fun checkToken(@Path("token") param: String): Observable<Boolean>

//    @GET
//    abstract fun getTrainings(@Url url: String): Observable<List<TrainingsFeed>>
//
//    @GET
//    abstract fun getExercises(@Url string: String): Observable<TrainingsFeed>
//
//    @GET
//    abstract fun getSets(@Url url: String): Observable<ExercisesFeed>
//
//    @GET("users/{userId}?props")
//    abstract fun getUserInfo(@Path("userId") id: String): Observable<UserInfoResponse>
//
//    @POST("data/timetable")
//    abstract fun newTraining(@Body feed: TrainingsFeed): Observable<TrainingsFeed>
//
//    @POST("data/exercises")
//    abstract fun newExercise(@Body feed: ExercisesFeed): Observable<ExercisesFeed>
//
//    @PUT
//    abstract fun updateTraining(@Url string: String, @Body feed: TrainingsFeed): Observable<TrainingsFeed>
//
//    @POST
//    abstract fun updateExercise(@Url string: String, @Body feed: ExercisesFeed): Observable<ExercisesFeed>
//
//    @PUT
//    abstract fun updateUser(@Url string: String, @Body info: UserInfo): Observable<UserInfoResponse>
//
//    @DELETE
//    abstract fun delItem(@Url url: String): Observable<DeleteResponse>
//
//    @GET("users/logout")
//    abstract fun logOut(@Header("user-token") token: String): Observable<Response<Void>>
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