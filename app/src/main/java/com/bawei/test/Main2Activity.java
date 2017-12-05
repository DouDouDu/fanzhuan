package com.bawei.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.test.inject.ViewInject;
import com.bawei.test.injectutil.BindAction;
import com.bawei.test.injectutil.BindView;
import com.bawei.test.injectutil.inject;

import java.lang.annotation.Target;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.tv2)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        inject.injectview(this);

       tv.setText("注解设置");
        click();
    }

    @BindAction(click = R.id.btn)
    private void click(){
        Toast.makeText(Main2Activity.this,"点击",Toast.LENGTH_SHORT).show();
    }

}
