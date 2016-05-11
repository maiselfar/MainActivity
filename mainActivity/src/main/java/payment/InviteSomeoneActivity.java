
package payment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hmkcode.android.sign.R;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import globalVariables.GlobalVariable;
import loginSignupPage.JSONParser;

import static java.lang.Thread.sleep;

public class InviteSomeoneActivity extends FragmentActivity  {

    Typeface face;
    static   ArrayList<String> list;
    ArrayList<String> invitedCustomers;
    private static String url_inviteSomeone= "http://52.11.144.56/inviteSomeone.php";
    TableLayout tl;
    String command;
	
	public void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.payment_invite_someone_screen);
        command="query";
		TextView tableNumber=new TextView(this);
        ActionBar bar = getActionBar();
//for color
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fb1d91db")));
     //   face = Typeface.createFromAsset(this.getAssets(),
       //         "orange juice 2.0.ttf");
	//	tableNumber.setTypeface(face);
		//Log.e("pep", this.getIntent().getExtras().getInt("tableNumber") + "");
		tableNumber.setText("Table " + this.getIntent().getExtras().getInt("tableNumber"));
		tableNumber.setTextColor(Color.parseColor("#fb1d91db"));
		tableNumber.setTextAlignment(3);
		tableNumber.setTextSize(26);
        tl = (TableLayout) findViewById(R.id.inviteTable);
		TableRow r1=new TableRow(this);
		r1.addView(tableNumber);
		tl.addView(r1);

           inviteSomeone x= new inviteSomeone();
        x.execute("query");




	}
public class inviteSomeone extends AsyncTask <String, String, String> {
    private final ProgressDialog dialog = new ProgressDialog(InviteSomeoneActivity.this);
    private boolean post=false;
   @Override
    protected void onPreExecute() {
       if(command.equals("query")){
           this.dialog.setMessage("Fetching customers' names on the table...");
           this.dialog.show();}
       else if (command.equals("update")){
           this.dialog.setMessage("Inviting customer...");
           this.dialog.show();

       }

    }

    @Override
    protected String doInBackground(String... params) {
        if(params[0].equals("query")){
        List<NameValuePair> paramss = new ArrayList<NameValuePair>();
        JSONParser jsonParser = new JSONParser();
        paramss.add(new BasicNameValuePair("tableNumber", InviteSomeoneActivity.this.getIntent().getExtras().getInt("tableNumber") + ""));
        JSONObject json = jsonParser.makeHttpRequest(url_inviteSomeone,
                "GET", paramss);
        Log.e("Hi", 5 + "");

        try {
           list = new ArrayList<String>();
           JSONArray customers = (JSONArray) json.get("customers");
            if (customers != null) {
                int len = customers.length();
                for (int i=0;i<len;i++){
                    list.add(customers.get(i).toString());
                }

            }

            Set<String> hs = new HashSet<>();
            hs.addAll(list);
            list.clear();
            list.addAll(hs);
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals(GlobalVariable.getCustomerUserName())){
                    list.remove(i);
                }
            }


            Log.e("length", list.toString());
            /*for (int i = 0; i < customers.length(); i++) {
                JSONObject json_data = customers.getJSONObject(i);

                Log.e("Array", customers.getString(i) + json_data);


            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
            post=true;
}
        else if(params[0].equals("update")){
            List<NameValuePair> paramss = new ArrayList<NameValuePair>();
            JSONParser jsonParser = new JSONParser();
            for (int i=0;i<invitedCustomers.size();i++){
            paramss.add(new BasicNameValuePair("customer"+i, invitedCustomers.get(i)));
                paramss.add(new BasicNameValuePair("currentCustomer", GlobalVariable.getCustomerUserName()));
                paramss.add(new BasicNameValuePair("table",InviteSomeoneActivity.this.getIntent().getExtras().getInt("tableNumber")+""));}

            JSONObject json = jsonParser.makeHttpRequest(url_inviteSomeone,
                    "GET", paramss);
        post=false;
        }
        return null;
    }
  @Override
    protected void onPostExecute(String s) {
      if(!post){
          new AlertDialog.Builder(InviteSomeoneActivity.this)

                  .setMessage("Invitation successful!")
                  .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                          // continue with delete
                          dialog.dismiss();
                      }
                  })

                  .setIcon(android.R.drawable.ic_dialog_alert)
                  .show();
      }
        dialog.dismiss();
      if(post){
      for (int i = 0; i < list.size(); i++) {
          TableRow r2 = new TableRow(InviteSomeoneActivity.this);
          TextView customer = new TextView(InviteSomeoneActivity.this);

        customer.setText(list.get(i));
          customer.setTextColor(Color.parseColor("#fb1d91db"));
          customer.setTextAlignment(3);
          customer.setTextSize(26);
          RadioButton radio = new RadioButton(InviteSomeoneActivity.this);
        radio.setId(i);
          r2.addView(customer);
          r2.addView(radio);
          tl.addView(r2);

      }
      Button inviteButton = new Button(InviteSomeoneActivity.this);
      inviteButton.setText("Invite");
  //    inviteButton.setTypeface(face);
          inviteButton.setBackgroundColor(Color.parseColor("#fb1d91db"));
          inviteButton.setTextColor(Color.WHITE);
      inviteButton.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        inviteButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                 invitedCustomers = new ArrayList<String>();
                for (int i = 0; i < list.size(); i++) {
                    RadioButton radio = (RadioButton) findViewById(i);
                    if (radio.isChecked()) {
                        invitedCustomers.add(list.get(i));

                    }
                }
                inviteSomeone x= new inviteSomeone();
                command="update";
                x.execute("update");

            }
        });
      TableRow r3 = new TableRow(InviteSomeoneActivity.this);
      r3.addView(inviteButton);
      tl.addView(r3);
    }}
}
}
