package com.mark.mvp.presenter;

import com.mark.mvp.model.IModel;
import com.mark.mvp.model.OneModel;
import com.mark.mvp.view.IView;

import java.util.List;

/**
 * Created by mark on 18-4-29.
 */

public class OnePresenter {

    IView iView;
    IModel iModel;


    public OnePresenter(IView view){
        iView = view;
        iModel = new OneModel();
    }

    public void start(){
        if (iView != null){
            iView.showLoading("数据加载中...");
            if (iModel != null){
                iModel.loadData(new IModel.DataLoadListener() {
                    @Override
                    public void onComplete(List datas) {
                        iView.show(datas);
                    }

                    @Override
                    public void onFailure(String msg) {
                        iView.showLoading(msg);
                    }
                });
            }
        }
    }

}
