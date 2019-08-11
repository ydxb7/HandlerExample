package ai.tomorrow.handlerexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ai.tomorrow.base.BaseManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public BaseManager getBaseManager() {
        return  ((MyApplication)getApplication()).getBaseManager();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("TAG", "onResume");

    }

    @Override
    public void onPause() {
        Log.d("TAG", "onPause");
        super.onPause();
    }
}

