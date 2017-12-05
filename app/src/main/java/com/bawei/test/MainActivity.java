package com.bawei.test;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bawei.test.inject.ContentView;
import com.bawei.test.inject.ViewInject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";
    @ViewInject(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定
        inject(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText("我是注解设置的");
            }
        });

    }

    public static void inject(Activity activity){
        injectContentView(activity);
        injectViews(activity);
    }

    //首先是注入主布局文件的代码：
    public static void injectContentView(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        // 查询类上是否存在ContentView注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null)// 存在
        {
            int contentViewLayoutId = contentView.value();
            try
            {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
                        int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewLayoutId);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

   // 接下来是注入Views
    public static void injectViews(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // 遍历所有成员变量
        for (Field field : fields)
        {
            ViewInject viewInjectAnnotation = field
                    .getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null)
            {
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1)
                {
                    Log.e("TAG", viewId+"");
                    // 初始化View
                    try
                    {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
                                int.class);
                        Object resView = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

}
