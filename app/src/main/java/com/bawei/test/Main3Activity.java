package com.bawei.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.test.injectutil.BindAction;
import com.bawei.test.injectutil.BindView;
import com.bawei.test.injectutil2.Inject;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.tv3)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Inject.inject(this);

        tv.setText("hahihaohudhadhaifedifu");
        onClick();
    }

    @BindAction(click = R.id.btn)
    private void onClick(){
        Toast.makeText(Main3Activity.this,"吐司",Toast.LENGTH_SHORT).show();
    }
}
