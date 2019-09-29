package com.example.login_app;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts(
    );
    @GET("posts")
    Call<List<Post>> getposts(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getcomments(@Path("id") int postId);

    @GET
    Call<List<Comment>>getcomments();

    @POST("posts")
    Call<Post> createpost(@Body Post post);
}
