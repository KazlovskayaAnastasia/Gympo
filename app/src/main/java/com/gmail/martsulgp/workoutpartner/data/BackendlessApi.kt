package com.gmail.martsulgp.workoutpartner.data

import com.gmail.martsulgp.workoutpartner.model.request.UserDataRequest
import com.gmail.martsulgp.workoutpartner.model.response.LogInRequest
import com.gmail.martsulgp.workoutpartner.model.response.RegisterRequest
import com.gmail.martsulgp.workoutpartner.model.response.UserDataResponse
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Observable
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
    fun checkToken(@Path("token") param: String): Observable<Boolean>

    //    @GET("users/logout")
//    abstract fun logOut(@Header("user-token") token: String): Observable<Response<Void>>


    // User block
    @GET("users/{userId}?props")
    fun getUserInfo(@Path("userId") id: String): Observable<UserDataResponse>

    @PUT("users/{userId}")
    fun updateUser(@Path("userId") id: String, @Body info: UserDataRequest): Completable






//    @GET
//    abstract fun getTrainings(@Url url: String): Observable<List<TrainingsFeed>>
//
//    @GET
//    abstract fun getExercises(@Url string: String): Observable<TrainingsFeed>
//
//    @GET
//    abstract fun getSets(@Url url: String): Observable<ExercisesFeed>
//
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