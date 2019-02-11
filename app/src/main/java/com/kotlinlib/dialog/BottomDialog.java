package com.kotlinlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

;import com.androidui.R;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
/*
 <style name="BottomDialog" parent="@android:style/Theme.Dialog">

        <!-- 背景透明 -->
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowContentOverlay">@null</item>
        <!-- 浮于Activity之上 -->
        <item name="android:windowIsFloating">true</item>
        <!-- 边框 -->
        <item name="android:windowFrame">@null</item>
        <!-- Dialog以外的区域模糊效果 -->
        <item name="android:backgroundDimEnabled">true</item>
        <!-- 无标题 -->
        <item name="android:windowNoTitle">true</item>
        <!-- 半透明 -->
        <item name="android:windowIsTranslucent">true</item>
        <!-- Dialog进入及退出动画 -->
        <item name="android:windowAnimationStyle">@style/DialogAnimation</item>
    </style>

     <!-- ActionSheet进出动画 -->
    <style name="DialogAnimation" parent="@android:style/Animation.Dialog">
        <item name="android:windowEnterAnimation">@anim/dialog_in</item>
        <item name="android:windowExitAnimation">@anim/dialog_out</item>
    </style>
 */

public class BottomDialog{

    private View root;
    private Dialog dialog;

    /**
     *
     * @param ctx
     * @param layoutId 对话框布局
     * @param getDialogView 处理对话框中的UI事件
     */
    public BottomDialog(Context ctx, int layoutId, OnGetDialogView getDialogView){
        dialog = new Dialog(ctx, R.style.BottomDialog);
        root = LayoutInflater.from(ctx).inflate(
                layoutId, null);
        getDialogView.doDialogView(root);
        dialog.setContentView(root);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width =  ctx.getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    /**
     *
     * @param ctx
     * @param layoutId 对话框布局
     */
    public BottomDialog(Context ctx, int layoutId, OnGetDialog getDialog){
        dialog = new Dialog(ctx, R.style.BottomDialog);
        root = LayoutInflater.from(ctx).inflate(
                layoutId, null);
        getDialog.doDialog(dialog, root);
        dialog.setContentView(root);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width =  ctx.getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    public interface OnGetDialogView{
        void doDialogView(View dialogView);
    }

    public interface OnGetDialog{
        void doDialog(Dialog dialog, View dialogView);
    }

    public View getDialogView(){
        return root;
    }

    public Dialog getDialog(){
        return dialog;
    }

}

