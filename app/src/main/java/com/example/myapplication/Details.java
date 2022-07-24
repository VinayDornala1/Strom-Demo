package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
public class Details extends AppCompatActivity {
    TextView textView, textView1;
    private static ProgressDialog mProgressDialog;
    String htmltext;
    ImageView imageView;
    private String URLString = "https://app.stormoverseas.com/API/ArticlesAPI/ArticleDetails?ArticleId=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.textView2);
        textView1 = findViewById(R.id.text2);
        String tru=textView1.getText().toString();
        String text = getIntent().getStringExtra("ArticleId");
        Log.e("process", ">>" + getIntent().getStringExtra("ArticleId"));
        getSelectDetail(text);
//     textView1.setText(spanned);    String htmlString="<html> <p href=\\\"http://example.com/\\\">example.com</p> </html>";
    }
    private void getSelectDetail(String text) {
        showSimpleProgressDailog("Loading...", "fetching Json", false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLString + text,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        Log.e("response", "response===> " + response);
                        try {
                            removeSimpleProgressDailog();
                            JSONObject obj = new JSONObject(response);
                            textView.setText(obj.getString("Title"));
                            Spanned spanned = HtmlCompat.fromHtml((obj.getString("Description")),HtmlCompat.FROM_HTML_MODE_COMPACT);
                            textView1.setText(spanned);
                            Picasso.get().load(obj.getString("ImageUrl")).into(imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Details.this);
        requestQueue.add(stringRequest);
    }
    private void removeSimpleProgressDailog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showSimpleProgressDailog(String title, String msg, boolean isCanceled) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(this, title, msg);
                mProgressDialog.setCancelable(isCanceled);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
