package ai.tomorrow.handlerexample;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import ai.tomorrow.base.BaseManager;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private Handler uiHandler = new Handler();
    private TextView textView;

    // one background thread
    private Handler backgroundHandler;
    private HandlerThread handlerThread = new HandlerThread("background_thread_xx");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Main/ui thread

        handlerThread.start();
        backgroundHandler = new Handler(handlerThread.getLooper());

        textView = findViewById(R.id.hello_world);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "inx onClick: ");

                        getBaseManager().run("http://10.0.2.2:5000/", new BaseManager.Callback() {
                            @Override
                            public void onResponse(final String s) {
                                Log.d(TAG, "inx onResponse: " + s);

                                uiHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(s);
                                    }
                                });

                            }

                            @Override
                            public void onError(final String e) {
                                Log.d(TAG, "inx onError: " + e);


//                                uiHandler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        textView.setText(e);
//                                    }
//                                });
                            }
                        });
//                            getBaseManager().run("http://127.0.0.1:5000/");
                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        uiHandler.postDelayed(mainThreadRunner, 3000);

        backgroundHandler.postDelayed(backgroundThreadRunner, 6000);
    }

    @Override
    public void onPause() {
        uiHandler.removeCallbacks(mainThreadRunner);
        // remove all runnable posted on this handler.
        uiHandler.removeCallbacksAndMessages(null);

        backgroundHandler.removeCallbacks(backgroundThreadRunner);


        super.onPause();
    }


    private Runnable mainThreadRunner = new Runnable() {
        @Override
        public void run() {
            Log.d("TAG", "mainThreadRunner, thread name: " + Thread.currentThread().getName());

//            uiHandler.postDelayed(mainThreadRunner, 3000);
        }
    };

    private Runnable backgroundThreadRunner = new Runnable() {
        @Override
        public void run() {
            Log.d("TAG", "backgroundThreadRunner, thread name: " + Thread.currentThread().getName());

//            backgroundHandler.postDelayed(backgroundThreadRunner, 6000);
//
//            uiHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    textView.setText("xxxxxxxxxxx inx");
//                }
//            });



//            showToast();
        }
    };


    private void showToast() {
        Toast.makeText(this, "hello worlds", Toast.LENGTH_SHORT).show();
    }
}
