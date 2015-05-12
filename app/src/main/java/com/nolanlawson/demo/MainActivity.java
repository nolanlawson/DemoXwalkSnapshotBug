package com.nolanlawson.demo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

  MyWebView webView;
  ImageView imageView;
  Bitmap oldBitmap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    webView = (MyWebView) findViewById(R.id.webview);
    webView.setClient(new MyWebViewClient(webView));

    webView.loadUrl("file:///android_asset/index.html");

    imageView = (ImageView) findViewById(R.id.image_view);
    imageView.setVisibility(View.INVISIBLE);
    webView.setVisibility(View.VISIBLE);

    TextView usingCrosswalkTextView = (TextView) findViewById(R.id.text_using_crosswalk);
    if (BuildConfig.FLAVOR.startsWith("withCrosswalk")) {
      usingCrosswalkTextView.setText("We are using Crosswalk's XWalkView");
    } else {
      usingCrosswalkTextView.setText("We are using the standard WebView");
    }

    Button showImageView = (Button) findViewById(R.id.button_show_snapshot);

    showImageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageView.setVisibility(View.VISIBLE);
        webView.setVisibility(View.INVISIBLE);
      }
    });

    Button showWebView = (Button) findViewById(R.id.button_show_webview);

    showWebView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageView.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.VISIBLE);
      }
    });

    Button takeSnapshot = (Button) findViewById(R.id.button_take_snapshot);

    takeSnapshot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        takeSnapshot();
        Toast.makeText(MainActivity.this, "Took snapshot of WebView!",
            Toast.LENGTH_LONG).show();
      }
    });
  }

  private void takeSnapshot() {

    if (oldBitmap != null) {
      oldBitmap.recycle();
    }

    ViewGroup parent = (ViewGroup) webView.getParent();

    Bitmap bitmap = Bitmap.createBitmap(
        parent.getWidth(),
        parent.getHeight(),
        Bitmap.Config.ARGB_8888);

    // bind a canvas to the bitmap, let the view draw it
    Canvas canvas = new Canvas(bitmap);
    webView.draw(canvas);

    imageView.setImageBitmap(bitmap);
    oldBitmap = bitmap;
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
