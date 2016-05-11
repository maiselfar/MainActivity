package payment;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.hmkcode.android.sign.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dynamicMenu.DynamicMenuFragmentActivity.DummySectionFragment;
import globalVariables.GlobalVariable;
import loginSignupPage.JSONParser;

public class SplitItemsActivity extends FragmentActivity {
	JSONParser jsonParser = new JSONParser();
	static String url_splitItem = "http://52.11.144.56/splitItem.php";
	Typeface face;
	static ArrayList<String> list;
    static   ArrayList<JSONArray> list2;
	ArrayList<String> invitedCustomers;
    ArrayList splitedItemsCounter;
    String command ="";
	private static String url_inviteSomeone = "http://52.11.144.56/inviteSomeone.php";
    private static String url_updatePaymentSummary= "http://52.11.144.56/updatePaymentSummary.php";
	TableLayout tl;

	public void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
        splitItems x= new splitItems();
        command="updateSummary";
        x.execute("updateSummary");
		setContentView(R.layout.dynamic_menu_summary_page_fragment);

        ActionBar bar = getActionBar();
//for color
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fb1d91db")));
	}

	public class splitItems extends AsyncTask<String, String, String> {
		private final ProgressDialog dialog = new ProgressDialog(SplitItemsActivity.this);
		private int post = 0;

		@Override
		protected void onPreExecute() {
            if(command.equals("updateSummary")){
			this.dialog.setMessage("Fetching customers' names on the table...");
			this.dialog.show();}
            else if (command.equals("split")){
                this.dialog.setMessage("Splitting items...");
                this.dialog.show();

            }

		}

		@Override
		protected String doInBackground(String... params) {
          if(params[0].equals("updateSummary")) {
                List<NameValuePair> paramss = new ArrayList<NameValuePair>();
                JSONParser jsonParser = new JSONParser();
                paramss.add(new BasicNameValuePair("currentCustomer", GlobalVariable.getCustomerUserName()));
                paramss.add(new BasicNameValuePair("tableNumber", GlobalVariable.getTableNumber() + ""));


                JSONObject json = jsonParser.makeHttpRequest(url_updatePaymentSummary,
                        "GET", paramss);
                //	Log.e("Hi", 5 + json.toString());

                try {
                    list2 = new ArrayList<JSONArray>();
                    //JSONArray orders2=new JSONArray();
                    JSONArray orders = (JSONArray) json.get("orders");
                    if (orders != null) {
                        int len = orders.length();
                        for (int i = 0; i < len; i++) {
                            list2.add((JSONArray) orders.get(i));
                        }

                    }


                    Log.e("length", list2.toString());

            /*for (int i = 0; i < customers.length(); i++) {
                JSONObject json_data = customers.getJSONObject(i);

                Log.e("Array", customers.getString(i) + json_data);


            }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
             paramss = new ArrayList<NameValuePair>();

              paramss.add(new BasicNameValuePair("tableNumber", SplitItemsActivity.this.getIntent().getExtras().getInt("tableNumber") + ""));
             json = jsonParser.makeHttpRequest(url_inviteSomeone,
                      "GET", paramss);
              Log.e("Hi", 5 + "");

              try {
                  list = new ArrayList<String>();
                  JSONArray customers = (JSONArray) json.get("customers");
                  if (customers != null) {
                      int len = customers.length();
                      for (int i = 0; i < len; i++) {
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
              post = 1;


            }

			 else if (params[0].equals("split")) {
				//List<NameValuePair> paramss = new ArrayList<NameValuePair>();
				JSONParser jsonParser = new JSONParser();
              for(int i=0 ;i <invitedCustomers.size();i++){
                  Log.e("invitedCustomers",invitedCustomers.get(i));
              }
              int k=-1;
				for (int i = 0; i < splitedItemsCounter.size(); i++) {
                    List<NameValuePair> paramss = new ArrayList<NameValuePair>();
                    for(int j=k+1;!invitedCustomers.get(i+j).equals("done");j++){
					paramss.add(new BasicNameValuePair("customer" + j, invitedCustomers.get(i+j)));
                    Log.e("first loop", "ok");

                        k=j;
                    }
                    Log.e("second loop", "ok");
                    paramss.add(new BasicNameValuePair("currentCustomer", GlobalVariable.getCustomerUserName()));
                    paramss.add(new BasicNameValuePair("table", SplitItemsActivity.this.getIntent().getExtras().getInt("tableNumber") + ""));
                    try {
                        paramss.add(new BasicNameValuePair("order",list2.get((int)splitedItemsCounter.get(i)).getString(1)));
                        paramss.add(new BasicNameValuePair("price",list2.get((int)splitedItemsCounter.get(i)).getString(2)));
                        paramss.add(new BasicNameValuePair("order_number",list2.get((int)splitedItemsCounter.get(i)).getString(0)));
                   } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONObject json = jsonParser.makeHttpRequest(url_splitItem,
                            "GET", paramss);




                }


				post = 2;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String s) {

           if(post==2){
               new AlertDialog.Builder(SplitItemsActivity.this)

                       .setMessage("Items split successfully!")
                       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               // continue with delete
                               dialog.dismiss();
                           }
                       })

                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .show();
           }
            if(post==1) {

                TableLayout tl = (TableLayout) findViewById(R.id.summarytable);
                //tl.setBackground(getResources().getDrawable(R.drawable.summary_page_white_paper));
                TableRow row = new TableRow(SplitItemsActivity.this);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                TextView SummaryIntro = new TextView(SplitItemsActivity.this);
              //  Typeface face = Typeface.createFromAsset(SplitItemsActivity.this.getAssets(),
                //        "orange juice 2.0.ttf");
            //    SummaryIntro.setTypeface(face);
                SummaryIntro.setId(4444);
                SummaryIntro.setText(
                        "Zak's Dinner \nCheck number 5555\nWaiter Adam\n" +"Table "+ GlobalVariable.getTableNumber() + "\n" + dateFormat.format(date) + "\n\n");
                SummaryIntro.setTextColor(Color.parseColor("#fb1d91db"));
                SummaryIntro.setTextAlignment(3);
                SummaryIntro.setTextSize(26);
                row.addView(SummaryIntro);
                tl.addView(row);
                double subtotal = 0;
                final double tax = 1.13;
                TableRow rowDesc = new TableRow(SplitItemsActivity.this);
                TextView ItemName = new TextView(SplitItemsActivity.this);
              //  TextView Quantity = new TextView(SplitItemsActivity.this);
                TextView Price = new TextView(SplitItemsActivity.this);
                ItemName.setText("Item Name");
               // Quantity.setText("Quantity");
                Price.setText("Price");
                ItemName.setTextColor(Color.parseColor("#fb1d91db"));
               // Quantity.setTextColor(Color.parseColor("#fb1d91db"));
                Price.setTextColor(Color.parseColor("#fb1d91db"));
          //      ItemName.setTypeface(face);
                //Quantity.setTypeface(face);
             //   Price.setTypeface(face);
                ItemName.setTextSize(20);
                //Quantity.setTextSize(20);
                Price.setTextSize(20);
                rowDesc.addView(ItemName);
                //rowDesc.addView(Quantity);
                rowDesc.addView(Price);
                tl.addView(rowDesc);
                int Id = 100000;
                //Log.d(, "Count is" + count_4[0] + count_4[1]);
                for (int i = 0; i < list2.size(); i++) {
                    TableRow tr = new TableRow(SplitItemsActivity.this);
                    TableRow tr2 = new TableRow(SplitItemsActivity.this);
                    double item_price = 0;
                  //  int count = 0;
                    String item = "";
                    String orderNumber="";
                    try {
                        item_price = Double.parseDouble(list2.get(i).getString(2));
                    //    count = Integer.parseInt(list2.get(i).getString(2));
                        item = list2.get(i).getString(1);
                        orderNumber=list2.get(i).getString(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Id += 2;


                    TextView itemName = new TextView(SplitItemsActivity.this);
                    //TextView itemQuantity = new TextView(SplitItemsActivity.this);
                    TextView itemPrice = new TextView(SplitItemsActivity.this);

                    itemName.setText(item);
                    itemName.setTextColor(Color.parseColor("#fb1d91db"));
                    itemName.setTextAlignment(3);
                    itemName.setTextSize(20);
               //     itemName.setTypeface(face);
                    itemName.setId(Id + 1);

                /*    itemQuantity.setText(" @ " + count);
                    itemQuantity.setTextColor(Color.parseColor("#fb1d91db"));
                    itemQuantity.setTextAlignment(3);
                    itemQuantity.setTextSize(20);
                    itemQuantity.setTypeface(face);*/
                    itemPrice.setText("$" + item_price);
                    itemPrice.setTextColor(Color.parseColor("#fb1d91db"));
                    itemPrice.setTextAlignment(3);
                    itemPrice.setTextSize(20);
                //    itemPrice.setTypeface(face);

                    tr.addView(itemName);
                  //  tr.addView(itemQuantity);
                    tr.addView(itemPrice);

                    tl.addView(tr);

                    for (int j = 0; j < list.size(); j++) {
                        TextView customerSplit = new TextView(SplitItemsActivity.this);
                        customerSplit.setText(list.get(j));
                        customerSplit.setTextColor(Color.parseColor("#fb1d91db"));
                        RadioButton radio = new RadioButton(SplitItemsActivity.this);
                        radio.setId(j+(i*10));
                        tr2.addView(customerSplit);
                        tr2.addView(radio);


                    }
                    tl.addView(tr2);

                }
                Button splitButton = new Button(SplitItemsActivity.this);
                splitButton.setText("Split Items");
                splitButton.setBackgroundColor(Color.parseColor("#fb1d91db"));
                splitButton.setTextColor(Color.WHITE);
                splitButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        invitedCustomers = new ArrayList<String>();
                        splitedItemsCounter=new ArrayList();
                        for (int i = 0; i < list2.size(); i++) {
                            for (int j = 0; j < list.size(); j++) {
                                RadioButton radio = (RadioButton) findViewById(j+(i*10));
                                if (radio.isChecked()) {
                                    invitedCustomers.add(list.get(j));

                                }
                            }
                            invitedCustomers.add("done");
                            splitedItemsCounter.add(i);
                        }

                        splitItems x = new splitItems();
                        command="split";
                        x.execute("split");

                    }
                });

                TableRow tr2 = new TableRow(SplitItemsActivity.this);
                tr2.addView(splitButton);
                tl.addView(tr2);

            }
            dialog.dismiss();
		}

	}
}