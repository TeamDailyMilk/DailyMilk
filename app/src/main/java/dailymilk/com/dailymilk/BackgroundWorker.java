package dailymilk.com.dailymilk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import dailymilk.com.dailymilk.Admin.DashboardActivity;
import dailymilk.com.dailymilk.Admin.OpenOrdersActivity;
import dailymilk.com.dailymilk.User.MainActivity;
import dailymilk.com.dailymilk.User.OrderStateActivity;

import static dailymilk.com.dailymilk.Admin.DashboardActivity.EXTRA_USER;
import static dailymilk.com.dailymilk.Admin.OpenOrdersActivity.EXTRA_USERNAME;
import static dailymilk.com.dailymilk.User.OrderStateActivity.EXTRA_RESULT;


/**
 * Created by Gabsii on 28.11.2016.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    String type;
    String user_name;
    Intent admintent;
    public BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "http://dailymilk.tk/login.php";
        String order_url = "http://dailymilk.tk/order.php";
        String admin_url = "http://dailymilk.tk/openorders.php";
        if (type.equals("login")) {
            try {
                user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream oS = httpURLConnection.getOutputStream();
                BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(oS, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bW.write(post_data);
                bW.flush();
                bW.close();
                oS.close();
                InputStream iS = httpURLConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(iS, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bR.readLine()) != null) {
                    result += line;
                }
                bR.close();
                iS.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("order")) {
            try {
                String user = params[1];
                String order = params[2];
                URL url = new URL(order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&" +
                        URLEncoder.encode("order", "UTF-8") + "=" + URLEncoder.encode(order, "UTF-8");
                bW.write(data);
                bW.flush();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bR.readLine()) != null) {
                    result += line;
                }
                bR.close();
                IS.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("view")) {
            try {
                String user = params[1];
                URL url = new URL(order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
                bW.write(data);
                bW.flush();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bR.readLine()) != null) {
                    result += line;
                }
                bR.close();
                IS.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("admin")) {
            try {
                String user = params[1];
                URL url = new URL(admin_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
                bW.write(data);
                bW.flush();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bR.readLine()) != null) {
                    result += line;
                }
                bR.close();
                IS.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (type.equals("login")) {
            if (result.equals("Username/Password is wrong")) {
                alertDialog.setMessage(result);
                alertDialog.show();
            } else {
                if (result.equals("isAdmin")) {
                    //new BackgroundWorker(context).execute("admin", "rootAdmin");
                    LoginActivity.ADMIN_STATUS = 1;
                    Intent intent = new Intent(context, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra(EXTRA_USER, user_name.toString());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(MainActivity.EXTRA_MESSAGE, user_name);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra(MainActivity.EXTRA_DRINKS, result);
                    context.startActivity(intent);
                }
            }
        }
        if (type.equals("order")) {
            Intent intent = new Intent(context, OrderStateActivity.class);
            intent.putExtra(EXTRA_RESULT, result);
            context.startActivity(intent);
        }

        if (type.equals("view")) {
            Intent intent = new Intent(context, OrderStateActivity.class);
            intent.putExtra(EXTRA_RESULT, result);
            context.startActivity(intent);
        }
        if (type.equals("admin")) {
            // new BackgroundWorker(context).execute("admin", user_name);
            admintent = new Intent(context, OpenOrdersActivity.class);
            admintent.putExtra(EXTRA_USERNAME, user_name);
            admintent.putExtra(OpenOrdersActivity.EXTRA_RESULT, result);
            context.startActivity(admintent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}




