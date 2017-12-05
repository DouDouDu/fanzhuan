package com.bawei.test.injectutil2;

import android.app.Activity;
import android.view.View;

import com.bawei.test.injectutil.BindAction;
import com.bawei.test.injectutil.BindView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1：类的用途
 * 2：@author 张倩
 * 3:@date 2017/12/5 15:32
 */

public class Inject {

    public static void inject(final Activity activity){
        //遍历属性,对设置注解的view进行初始化
        Class<Activity> activityClass= (Class<Activity>) activity.getClass();
        Field fields[]=activityClass.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(BindView.class)){
                int viewId=field.getAnnotation(BindView.class).value();
                View view=activity.findViewById(viewId);
                try {
                    //这一行代码是必须的，否则直接调用set方法不生效
                    field.setAccessible(true);
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //遍历方法
        Method[] methods = activityClass.getMethods();
        for (final Method method:methods){
            final BindAction action = method.getAnnotation(BindAction.class);
            if(action!=null){
                int click = action.click();
                activity.findViewById(click).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        method.setAccessible(true);
                        try {
                            method.invoke(activity);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
