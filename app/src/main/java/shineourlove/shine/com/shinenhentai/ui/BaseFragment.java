package shineourlove.shine.com.shinenhentai.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shineourlove.shine.com.shinenhentai.api.BookApi;

/**
 * created by shineourlove on 2019/2/21
 */
public abstract class BaseFragment extends Fragment {
    public BookApi bookApi;
    public View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract @LayoutRes
    int getLayoutResId();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResId(), container, false);
        bookApi = BookApi.getInstance();
        init();
        return view;
    }

    public abstract void init();
}
