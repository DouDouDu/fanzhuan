package com.bawei.test.injectutils;

import android.app.Activity;
import android.view.View;

import com.bawei.test.injectutil.*;
import com.bawei.test.injectutil.BindAction;
import com.bawei.test.injectutil.BindView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1：类的用途
 * 2：@author 张倩
 * 3:@date 2017/12/5 16:29
 */

public class util {

    public static void inject(final Activity activity){
        //通过反射拿到对象
        Class<? extends Activity> activityClass = activity.getClass();
        //拿到属性
        Field[] fields = activityClass.getFields();
        for (Field field:fields){
            if(field.isAnnotationPresent(com.bawei.test.injectutil.BindView.class)){
                int value = field.getAnnotation(BindView.class).value();
                activity.findViewById(value);
                activity.findViewById(value);
                try {
                    field.setAccessible(true);
                    field.set(activity,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //遍历方法
        Method[] methods = activityClass.getMethods();
        for (final Method method:methods){
            //注解处理器
            final com.bawei.test.injectutil.BindAction action = method.getAnnotation(BindAction.class);
            if(action!=null){
                int click = action.click();
                activity.findViewById(click).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.setAccessible(true);
                            method.invoke(activity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

    }


}
