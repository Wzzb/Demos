package test.com.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.com.retrofitdemo.model.GetProjectInterface;
import test.com.retrofitdemo.model.GetRequest_interface;
import test.com.retrofitdemo.model.PostRequest_Interface;
import test.com.retrofitdemo.model.ProjectBean;
import test.com.retrofitdemo.model.ProjectBody;
import test.com.retrofitdemo.model.Translation;
import test.com.retrofitdemo.model.Translation1;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
//        request();
//        request2();
//        request3();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request3();
            }
        });
    }

    private void request2() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_interface request = retrofit.create(GetRequest_interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
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

    public void request3() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall(editText.getText().toString());

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                String tgt = response.body().getTranslateResult().get(0).get(0).getTgt();
                System.out.println(tgt);
                textView.setText(tgt);
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
                textView.setText("请求失败");
            }
        });
    }
}
