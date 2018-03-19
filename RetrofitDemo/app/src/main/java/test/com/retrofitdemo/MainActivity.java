package test.com.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.com.retrofitdemo.model.GetProjectInterface;
import test.com.retrofitdemo.model.ProjectBean;
import test.com.retrofitdemo.model.ProjectBody;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        request();
    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.78.143.178:6080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetProjectInterface request = retrofit.create(GetProjectInterface.class);
        Call<ProjectBody> call = request.getProjects();
        call.enqueue(new Callback<ProjectBody>() {
            @Override
            public void onResponse(Call<ProjectBody> call, Response<ProjectBody> response) {
                ProjectBody body = response.body();
                StringBuilder builder = new StringBuilder();
                for (ProjectBean bean :
                        body.getProjects()) {
                    builder.append(bean.toString());
                }
                textView.setText(builder.toString());
            }

            @Override
            public void onFailure(Call<ProjectBody> call, Throwable t) {

            }
        });
    }
}
