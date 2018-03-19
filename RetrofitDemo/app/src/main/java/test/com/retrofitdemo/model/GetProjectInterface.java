package test.com.retrofitdemo.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by H235952 on 2018/3/19.
 */

public interface GetProjectInterface {

    @GET("AppStoreServer/projects?type=json")
    Call<ProjectBody> getProjects();
}
