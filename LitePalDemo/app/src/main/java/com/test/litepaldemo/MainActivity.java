package com.test.litepaldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.litepaldemo.bean.CompanyBase;
import com.test.litepaldemo.bean.Developer;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void action(View view) {
        switch (view.getId()) {
            case R.id.button:
                save();
                break;
            case R.id.button2:
                update();
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                query();
                break;
            default:
                break;
        }
    }

    private void query() {
        List<Developer> developers = DataSupport.findAll(Developer.class);
        List<CompanyBase> companyBases = DataSupport.findAll(CompanyBase.class);
        for (Developer developer :
                developers) {
            Log.d("", developer.getName() + developer.getAge() + developer.getIncome() + developer.getBirthday());
        }
        for (CompanyBase base :
                companyBases) {
            Log.d("", base.getName() + base.getAddress() + base.getDevelopers().size());
        }
    }

    private void update() {
        Developer developer = DataSupport.find(Developer.class, 1);
        developer.setBirthday(new Date(1991, 9, 14));
        developer.save();

        Developer developer2 = new Developer();
        developer2.setIncome(11.9f);
        developer2.updateAll("name = ?", "canbot");

    }

    private void save() {
        Toast.makeText(this, "save start.", 0).show();
        Developer developer1 = new Developer();
        developer1.setName("wzb");
        developer1.setAge(25);
        developer1.save();

        Developer developer2 = new Developer();
        developer2.setName("canbot");
        developer2.setAge(25);
        developer2.save();


        CompanyBase base = new CompanyBase();
        base.setName("Honeywell ShenZhen R&D");
        base.setAddress("永新汇1号楼18楼");
        base.setTel("0755-49499249");
        base.getDevelopers().add(developer1);
        base.getDevelopers().add(developer2);
        base.save();
        Toast.makeText(this, "success", 0).show();
    }
}
