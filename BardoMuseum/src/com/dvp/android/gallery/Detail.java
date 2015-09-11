package com.dvp.android.gallery;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


public class Detail extends Activity {
    protected TextView Nom ;
    protected TextView detail ;
    ImageView _imgView = null;
    String li;
    ProgressDialog _busyDialog = null;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        _imgView = (ImageView) findViewById(R.id.imd);
        Nom= (TextView) findViewById(R.id.Nomd);
        String Name = getIntent().getStringExtra("Nom");
        li = getIntent().getStringExtra("LienImage");
        Nom.setText(Name);
        detail=(TextView) findViewById(R.id.Detail);
        String det = getIntent().getStringExtra("Detail");
        detail.setText(det);
        li = getIntent().getStringExtra("LienImage");
        showBusyDialog();
        new DownloadImageTask().execute(li);

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... url) {
// Need to show the busy dialog here, since we can't do it in
            // DownloadImageTask's doInBackground() method
            return loadImageFromNetwork(url[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            dismissBusyDialog();
            _imgView.setImageBitmap(result);
        }
    }

    private Bitmap loadImageFromNetwork(String url) {
        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url)
                    .getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public void showBusyDialog() {
        _busyDialog = ProgressDialog.show(this, "", "Downloading Image...",
                true);
    }

    public void dismissBusyDialog() {
        if (_busyDialog != null) {
            _busyDialog.dismiss();
        }
    }
}