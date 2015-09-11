package com.dvp.android.gallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bilel on 15/07/13.
 */
public class Infos extends Activity {

   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos);
       TextView lien = (TextView) findViewById(R.id.Site_Url);
       lien.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bardomuseum.tn/"));
               startActivity(in);
           }
       });
    }
}