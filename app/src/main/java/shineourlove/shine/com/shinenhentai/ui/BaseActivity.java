package shineourlove.shine.com.shinenhentai.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import shineourlove.shine.com.shinenhentai.api.BookApi;

/**
 * created by shineourlove on 2019/2/21
 */
public abstract class BaseActivity extends AppCompatActivity {
    public BookApi bookApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
