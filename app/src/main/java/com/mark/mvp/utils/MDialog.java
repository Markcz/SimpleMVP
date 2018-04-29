package com.mark.mvp.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mark.mvp.R;

/**
 * Created by mark on 18-4-29.
 */

public class MDialog {

    public static final int DIALOG_STYLE_BOTTOM_WINDOW = 1;
    public static final int DIALOG_STYLE_ACTION_SHEET = 2;
    public static final int DIALOG_STYLE_CENTER = 3;


    private Context mContext;
    private Dialog mDialog;
    private View contentView;
    private static MDialog mMDialog;


    private MDialog(Context context) {
        mContext = context;
        mDialog = new Dialog(context, R.style.MDialog);
        mDialog.setCanceledOnTouchOutside(true);
    }

    public MDialog setContentView(@NonNull @LayoutRes int layoutId) {
        contentView = LayoutInflater.from(mContext).inflate(layoutId, null);
        return setContentView(contentView);
    }

    public MDialog setContentView(@NonNull View contentView) {
        mDialog.setContentView(contentView);
        return mMDialog;
    }

    public MDialog setAnimationStyle(@NonNull int styleId) {
        mDialog.getWindow().setWindowAnimations(styleId);
        return mMDialog;
    }

    private MDialog setGravity(@NonNull int gravity) {
        mDialog.getWindow().setGravity(gravity);
        return mMDialog;
    }

    public void show() {
        mDialog.show();
    }

    public void show(int style,int height) {
        switch (style) {
            case DIALOG_STYLE_BOTTOM_WINDOW:
                setDialogStyleBottomWindow(height);
                break;
            case DIALOG_STYLE_ACTION_SHEET:
                setDialogStyleActionSheet();
                break;
            case DIALOG_STYLE_CENTER:
                setDialogStyleCenter(height);
                break;
        }
        mDialog.show();
    }


    private void setDialogStyleActionSheet() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = mContext.getResources().getDisplayMetrics().widthPixels - 50;
        params.bottomMargin = 20;
        contentView.setLayoutParams(params);
        setGravity(Gravity.BOTTOM);
    }

    private void setDialogStyleBottomWindow(int height) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = mContext.getResources().getDisplayMetrics().widthPixels;
        params.height = height;
        contentView.setLayoutParams(params);
        setGravity(Gravity.BOTTOM);
    }

    private void setDialogStyleCenter(int height) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = mContext.getResources().getDisplayMetrics().widthPixels - 50;
        //params.height = height;
        contentView.setLayoutParams(params);
        setGravity(Gravity.CENTER);
    }


    public void dismiss() {
        mDialog.dismiss();
    }


    public static class Builder {

        public Builder(Context context) {
            mMDialog = new MDialog(context);
        }

        public MDialog create() {
            return mMDialog;
        }

        public MDialog show() {
            mMDialog.show();
            return mMDialog;
        }

        public MDialog dissmiss() {
            mMDialog.dismiss();
            return mMDialog;
        }
    }


}
