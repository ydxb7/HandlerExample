package ai.tomorrow.base;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseManager {
    private static final String TAG = "BaseManager";
    private OkHttpClient client;

    public interface Callback {
        void onResponse(String s);
        void onError(String e);
    }

    public BaseManager(Context applicationContext) {


    }


    public OkHttpClient getOkHttpClient() {
        if (client == null) {
            client = new OkHttpClient();
        }

        return client;
    }

    public void run(String url, Callback callback) {
        Log.d(TAG, "inx run: start ");
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = null;
        try  {

            response = getOkHttpClient().newCall(request).execute();

            Log.d(TAG, "inx run message:: " + response.message());
            String res = response.body().string();
            Log.d(TAG, "inx run body:: " + res);
            callback.onResponse(res);



        } catch (Exception e) {
            callback.onError(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
