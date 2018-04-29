package com.mark.mvp;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mark.mvp.presenter.OnePresenter;
import com.mark.mvp.utils.MDialog;
import com.mark.mvp.view.IView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    OnePresenter mOnePresenter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    public void showLoading(String msg) {
        //加载视图
    }

    @Override
    public void show(List datas) {
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas));
    }


    public void loadData(View view) {
        mOnePresenter = new OnePresenter(this);
        mOnePresenter.start();
    }

    private boolean isLongPressed = false;
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            isLongPressed = false;
            Toast.makeText(this, "longPress", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                event.startTracking();
                if(event.getRepeatCount() == 0){
                    isLongPressed = true;
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if(isLongPressed){
                //Toast.makeText(this, "isLongPressed", Toast.LENGTH_LONG).show();
                //showBottomActionSheetDialog();
                //showCenterDialog();
                showBottomWindowDialog();
            }
            isLongPressed = false;
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    void showCenterDialog() {
        new MDialog.Builder(this)
                .create()
                .setContentView(R.layout.center_dialog)
                .show(MDialog.DIALOG_STYLE_CENTER,0);
    }

    void showBottomActionSheetDialog() {
        new MDialog.Builder(this)
                .create()
                .setContentView(R.layout.action_sheet)
                .show(MDialog.DIALOG_STYLE_ACTION_SHEET,0);
    }


    void showBottomWindowDialog(){
        new MDialog.Builder(this)
                .create()
                .setContentView(R.layout.bottom_dialog)
                .show(MDialog.DIALOG_STYLE_BOTTOM_WINDOW,500);
    }


}
