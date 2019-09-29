package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Display extends AppCompatActivity {
    String st;
    String pd;
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        st = getIntent().getExtras().getString("value");
        pd = getIntent().getExtras().getString("value2");

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //getPosts();
        //getcomments();
        createPost();
    }
    private void getPosts()
    {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("code: " + response.code() );
                    return;
                }
                List<Post> posts = response.body();
                for(Post post : posts)
                {
                    String content = "";
                    content += "ID: "+ post.getId() + "\n";
                    content += "User ID: "+ post.getUserId() + "\n";
                    content += "Name: "+ post.getTitle() + "\n";
                    content += "text: "+ post.getText() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
    private void getcomments()
    {
        Call<List<Comment>> call = jsonPlaceHolderApi.getcomments();

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("code: " + response.code() );
                    return;
                }
                List<Comment> comments = response.body();
                for(Comment comment : comments)
                {
                    String content = "";
                    content += "ID: "+ comment.getId() + "\n";
                    content += "User ID: "+ comment.getPostId() + "\n";
                    content += "Name: "+ comment.getName() + "\n";
                    content += "text: "+ comment.getEmail() + "\n";
                    content += "text: "+ comment.getText() + "\n\n";

                    textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
    private void createPost()
    {
        Post post = new Post(47,st,pd);
        Call<Post> call = jsonPlaceHolderApi.createpost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("code: " + response.code() );
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code: "+ response.code() + "\n";
                content += "ID: "+ postResponse.getId() + "\n";
                content += "User ID: "+ postResponse.getUserId() + "\n";
                content += "Name: "+ postResponse.getTitle() + "\n";
                content += "text: "+ postResponse.getText() + "\n\n";

                textViewResult.setText(content);
;
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}
