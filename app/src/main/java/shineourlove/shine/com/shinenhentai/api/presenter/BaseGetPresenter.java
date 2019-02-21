package shineourlove.shine.com.shinenhentai.api.presenter;

import shineourlove.shine.com.shinenhentai.api.DataPostListener;
import shineourlove.shine.com.shinenhentai.api.contract.BaseGetContract;
import shineourlove.shine.com.shinenhentai.api.model.BaseGetModelImp;

/**
 * created by shineourlove on 2019/2/21
 */
public class BaseGetPresenter implements BaseGetContract.BaseGetPresenter {
    private BaseGetContract.BaseGetModel baseGetModel;
    private DataPostListener listener;

    public BaseGetPresenter(DataPostListener listener) {
        this.listener = listener;
        baseGetModel = new BaseGetModelImp();
    }

    @Override
    public void getData(String url) {
        baseGetModel.getData(url, listener);
    }
}
