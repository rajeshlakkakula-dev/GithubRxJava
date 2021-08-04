package com.rl.githubrxjava;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class GithubClient {


    private static final String GITHUB_URL = "https://api.github.com/";

    private static GithubClient instance;
    private GithubService githubService;


    private GithubClient() {

        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        githubService = retrofit.create(GithubService.class);

    }


    public static GithubClient getInstance() {

        if (instance == null) {

            instance = new GithubClient();
        }
        return instance;

    }


    public Observable<List<GitHubRepo>> getStarredRepos(String userName) {
        return githubService.getStarredRepos(userName);

    }


}
