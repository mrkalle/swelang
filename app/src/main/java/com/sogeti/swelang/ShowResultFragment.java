package com.sogeti.swelang;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShowResultFragment extends Fragment{

    private App.OnAppEventListener mCallback;

    private TextView mShowResultTextView;
    private TextView mFromShowResultTextView;

    private String mFromLanguage;
    private String mToLanguage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_showresult, container, false);

        Log.d("Swelang", "ShowResultFragment::onCreateView called");

        Bundle args = getArguments();
        if (args != null) {
            mFromLanguage = args.getString(App.FROM_LANGUAGE);
            mToLanguage = args.getString(App.TO_LANGUAGE);
        }

        mShowResultTextView = view.findViewById(R.id.showresult_textview);
        mFromShowResultTextView = view.findViewById(R.id.fromshowresult_textview);

        return view;
    }

    @Override
    public void onStart () {
        super.onStart();

        Log.d("Swelang", "ShowResultFragment::onStart called");

        JsonObjectRequest jsonObjectRequest = createAzureRequest();
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

        Log.d("Swelang", "ShowResultFragment::onStart addToRequestQueue executed");
    }

    private void handleCognitiveResult(String objectname) {
        return;
        //StringRequest stringRequest = createGoogleTranslateRequest(objectname);

        /*

        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=sv&dt=t&q=ball";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Swelang", "ShowResultFragment::onResponse called");
                        mFromShowResultTextView.setText("boll");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Swelang", "ShowResultFragment::onErrorResponse called");
                        String apa = "katt";
                    }
                })
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");

                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
*/
/*
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        String apa = "test";
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                String katt = "testar";
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);

        */

        //VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (App.OnAppEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAppEventListener");
        }

        Log.d("Swelang", "ShowResultFragment::onAttach klar");
    }

    private JsonObjectRequest createAzureRequest() {
        String url = "http://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze?visualFeatures=Objects";
        Log.d("Swelang", "ShowResultFragment::createAzureRequest called, url: " + url);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", "https://www.cykloteket.se/_files/ProductMedia/29950_13083.jpg");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Swelang", "ShowResultFragment::createAzureRequest::onResponse, Response : " + response);
                        Log.d("Swelang", "ShowResultFragment::createAzureRequest::onResponse, length: " + response.length());

                        if (response.length() > 0) {
                            try {
                                JSONArray objects = response.getJSONArray("objects");
                                JSONObject objects2 = objects.getJSONObject(0);
                                String result = (String)objects2.get("object");
                                mShowResultTextView.setText(result);
                                handleCognitiveResult(result);
                            } catch (JSONException e) {
                                //e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Swelang", "ShowResultFragment::createAzureRequest::onErrorResponse Error getting response");
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Log.d("Swelang", "ShowResultFragment::createAzureRequest::onErrorResponse Error getting response TimeoutError NoConnectionError");
                        } else if (error instanceof AuthFailureError) {
                            Log.d("Swelang", "ShowResultFragment::createAzureRequest::onErrorResponse Error getting response AuthFailureError ");
                        } else if (error instanceof ServerError) {
                            Log.d("Swelang", "ShowResultFragment::createAzureRequest::onErrorResponse Error getting response ServerError ");
                        } else if (error instanceof NetworkError) {
                            Log.d("Swelang", "ShowResultFragment::createAzureRequest::onErrorResponse Error getting response NetworkError ");
                        } else if (error instanceof ParseError) {
                            Log.d("Swelang", "ShowResultFragment::createAzureRequest::onErrorResponse Error getting response ParseError: ");
                        }

                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Ocp-Apim-Subscription-Key", "7268be67bfe64b4d821a7d6c00fa5f73");
                params.put("Content-Type", "application/json");

                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    if (response.data.length == 0) {
                        byte[] responseData = "{}".getBytes("UTF8");
                        response = new NetworkResponse(response.statusCode, responseData, response.headers, response.notModified);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);
            }
        };
    }

/*
    private StringRequest createGoogleTranslateRequest(String objectname) {
        String url = "http://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ar&dt=t&q=sports ball";
        Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest called, url: " + url);

        return new StringRequest(Request.Method.GET, url, null,
                new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onResponse, Response : " + response);
                        Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onResponse, length: " + response.length());

                        mFromShowResultTextView.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onErrorResponse Error getting response");
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onErrorResponse Error getting response TimeoutError NoConnectionError");
                        } else if (error instanceof AuthFailureError) {
                            Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onErrorResponse Error getting response AuthFailureError ");
                        } else if (error instanceof ServerError) {
                            Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onErrorResponse Error getting response ServerError ");
                        } else if (error instanceof NetworkError) {
                            Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onErrorResponse Error getting response NetworkError ");
                        } else if (error instanceof ParseError) {
                            Log.d("Swelang", "ShowResultFragment::createGoogleTranslateRequest::onErrorResponse Error getting response ParseError: ");
                        }

                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");

                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    if (response.data.length == 0) {
                        byte[] responseData = "{}".getBytes("UTF8");
                        response = new NetworkResponse(response.statusCode, responseData, response.headers, response.notModified);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);
            }
        };
    }
    */
}
