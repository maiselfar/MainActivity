
package payment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hmkcode.android.sign.R;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import dynamicMenu.DynamicMenuFragmentActivity;
import globalVariables.GlobalVariable;
import loginSignupPage.JSONParser;

public class PaymentActivity extends FragmentActivity  {


	Typeface face;
	static   ArrayList<JSONArray> list;

	private static String url_updatePaymentSummary= "http://52.11.144.56/updatePaymentSummary.php";
	TableLayout tl;
	private int tableNumber;

	public void onCreate(Bundle savedInstanceState) {
		
		tableNumber=this.getIntent().getExtras().getInt("tableNumber");

		super.onCreate(savedInstanceState);


        updatePaymentSummary x= new updatePaymentSummary();
        x.execute();
		//setContentView(R.layout.payment_page_fragment);
		setContentView(R.layout.dynamic_menu_summary_page_fragment);
        ActionBar bar = getActionBar();
//for color
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fb1d91db")));

	
		//LinearLayout l = (LinearLayout) rootView.findViewById(R.id.scrollview2);
		//l.setBackground(R.drawable.summary_page_white_paper);

	}

        public class updatePaymentSummary extends AsyncTask<String, String, String> {
		private final ProgressDialog dialog = new ProgressDialog(PaymentActivity.this);
		private boolean post=false;
		@Override
		protected void onPreExecute() {
			this.dialog.setMessage("Updating Bill....");
			this.dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

				List<NameValuePair> paramss = new ArrayList<NameValuePair>();
				JSONParser jsonParser = new JSONParser();
            paramss.add(new BasicNameValuePair("currentCustomer", GlobalVariable.getCustomerUserName()));
				paramss.add(new BasicNameValuePair("tableNumber", PaymentActivity.this.getIntent().getExtras().getInt("tableNumber") + ""));
Log.e("customer name", GlobalVariable.getCustomerUserName());
            Log.e("table Number",GlobalVariable.getTableNumber()+"");

                JSONObject json = jsonParser.makeHttpRequest(url_updatePaymentSummary,
						"GET", paramss);
//				Log.e("Hi", 5 + json.toString());

				try {
					list = new ArrayList<JSONArray>();
                    //JSONArray orders2=new JSONArray();
					JSONArray orders = (JSONArray) json.get("orders");
					if (orders != null) {
						int len = orders.length();
						for (int i=0;i<len;i++){
							list.add((JSONArray)orders.get(i));
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



			return null;
		}
		@Override
		protected void onPostExecute(String s) {
			dialog.dismiss();
           /* final String[] breakfast = DynamicMenuFragmentActivity.DummySectionFragment.breakfast;
            final double[] breakfast_price = DynamicMenuFragmentActivity.DummySectionFragment.breakfast_price;
            final String[] lunchanddinner = DynamicMenuFragmentActivity.DummySectionFragment.lunchanddinner;
            final double[] lunchanddinner_price = DynamicMenuFragmentActivity.DummySectionFragment.lunchanddinner_price;
            final String[] desserts = DynamicMenuFragmentActivity.DummySectionFragment.desserts;
            final double[] desserts_price = DynamicMenuFragmentActivity.DummySectionFragment.desserts_price;
            final String[] drinks = DynamicMenuFragmentActivity.DummySectionFragment.drinks;
            final double[] drinks_price = DynamicMenuFragmentActivity.DummySectionFragment.drinks_price;
            final int[] count_1 = DynamicMenuFragmentActivity.DummySectionFragment.count_1;
            final int[] count_2 = DynamicMenuFragmentActivity.DummySectionFragment.count_2;
            final int[] count_3 = DynamicMenuFragmentActivity.DummySectionFragment.count_3;
            final int[] count_4 = DynamicMenuFragmentActivity.DummySectionFragment.count_4;*/

         final   TableLayout tl = (TableLayout) findViewById(R.id.summarytable);
            LinearLayout ll= (LinearLayout) findViewById(R.id.view3);


            //tl.setBackground(getResources().getDrawable(R.drawable.summary_page_white_paper));
            TableRow row = new TableRow(PaymentActivity.this);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            TextView SummaryIntro = new TextView(PaymentActivity.this);
          //  Typeface face = Typeface.createFromAsset(PaymentActivity.this.getAssets(),
                //    "orange juice 2.0.ttf");
          //  SummaryIntro.setTypeface(face);
            SummaryIntro.setId(4444);
            SummaryIntro.setText(
                    "Zak's Dinner \nCheck number 5555\nWaiter Adam\n" + "Table "+ getIntent().getExtras().getInt("tableNumber")+"\n" + dateFormat.format(date) + "\n\n");
            SummaryIntro.setTextColor(Color.parseColor("#fb1d91db"));
            SummaryIntro.setTextAlignment(3);
            SummaryIntro.setTextSize(26);
            row.addView(SummaryIntro);
            tl.addView(row);
            double subtotal = 0;
            final double tax = 1.13;
            TableRow rowDesc = new TableRow(PaymentActivity.this);
            TextView ItemName = new TextView(PaymentActivity.this);
          //  TextView Quantity = new TextView(PaymentActivity.this);
            TextView Price = new TextView(PaymentActivity.this);
            ItemName.setText("Item Name");
           // Quantity.setText("Quantity");
            Price.setText("Price");
        //    ItemName.setTypeface(face);
            //Quantity.setTypeface(face);
           // Price.setTypeface(face);
            ItemName.setTextColor(Color.parseColor("#fb1d91db"));
            //Quantity.setTextColor(Color.parseColor("#fb1d91db"));
            Price.setTextColor(Color.parseColor("#fb1d91db"));
            ItemName.setTextSize(20);
            //Quantity.setTextSize(20);
            Price.setTextSize(20);
            rowDesc.addView(ItemName);
            //rowDesc.addView(Quantity);
            rowDesc.addView(Price);
            tl.addView(rowDesc);
            //Log.d(, "Count is" + count_4[0] + count_4[1]);
            for (int i = 0; i < list.size(); i++) {
                TableRow tr = new TableRow(PaymentActivity.this);
                double item_price= 0;
              //  int count=0;
                String item="";
                try {
                    item_price = Double.parseDouble(list.get(i).getString(2));
                //    count = Integer.parseInt(list.get(i).getString(2));
                    item=list.get(i).getString(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                subtotal += (item_price) ;
                    TextView itemName = new TextView(PaymentActivity.this);
                    TextView itemQuantity = new TextView(PaymentActivity.this);
                    TextView itemPrice = new TextView(PaymentActivity.this);

                    itemName.setText(item);
                    itemName.setTextColor(Color.parseColor("#fb1d91db"));
                    itemName.setTextAlignment(3);
                    itemName.setTextSize(20);
                 //   itemName.setTypeface(face);
                  //  itemQuantity.setText(" @ " + count);

               //     itemQuantity.setTypeface(face);
                    itemPrice.setText("$" + item_price);
                    itemPrice.setTextColor(Color.parseColor("#fb1d91db"));
                    itemPrice.setTextAlignment(3);
                    itemPrice.setTextSize(20);
                 //   itemPrice.setTypeface(face);

                    tr.addView(itemName);
                 //   tr.addView(itemQuantity);
                    tr.addView(itemPrice);
                    tl.addView(tr);
                }


            TableRow tr = new TableRow(PaymentActivity.this);
            TextView itemSubtotal = new TextView(PaymentActivity.this);
            itemSubtotal.setId(5555);
            itemSubtotal.setText("Subtotal:");
            itemSubtotal.setTextColor(Color.parseColor("#fb1d91db"));
            itemSubtotal.setTextAlignment(3);
            itemSubtotal.setTextSize(20);
          //  itemSubtotal.setTypeface(face);
            TextView itemSubtotal2 = new TextView(PaymentActivity.this);
            itemSubtotal2.setId(5555);
          //  itemSubtotal2.setTypeface(face);
            double val = subtotal;
            val = val * 100;
            val = Math.round(val);
            val = val / 100;
            itemSubtotal2.setText("$" + val);
            itemSubtotal2.setTextColor(Color.parseColor("#fb1d91db"));
            itemSubtotal2.setTextAlignment(3);
            itemSubtotal2.setTextSize(20);
            tr.addView(itemSubtotal);
            tr.addView(itemSubtotal2);
            TableRow tr2 = new TableRow(PaymentActivity.this);
            TextView itemTaxes = new TextView(PaymentActivity.this);
          //  itemTaxes.setTypeface(face);
            itemTaxes.setText("Taxes:");
            itemTaxes.setTextColor(Color.parseColor("#fb1d91db"));
            itemTaxes.setTextAlignment(3);
            itemTaxes.setTextSize(20);
            tr2.addView(itemTaxes);
            TextView itemTaxes2 = new TextView(PaymentActivity.this);
            itemTaxes2.setId(5556);
          //  itemTaxes2.setTypeface(face);
            val = subtotal * 0.13;
            val = val * 100;
            val = Math.round(val);
            val = val / 100;
            itemTaxes2.setText("$" + val);
            itemTaxes2.setTextColor(Color.parseColor("#fb1d91db"));
            itemTaxes2.setTextAlignment(3);
            itemTaxes2.setTextSize(20);

            tr2.addView(itemTaxes2);
            TableRow tr3 = new TableRow(PaymentActivity.this);
            TextView itemTotalDue = new TextView(PaymentActivity.this);
            itemTotalDue.setId(5557);
            itemTotalDue.setText("Total Due:");
            itemTotalDue.setTextColor(Color.parseColor("#fb1d91db"));
            itemTotalDue.setTextAlignment(3);
            itemTotalDue.setTextSize(20);
          //  itemTotalDue.setTypeface(face);
            tr3.addView(itemTotalDue);
            TextView itemTotalDue2 = new TextView(PaymentActivity.this);
            itemTotalDue2.setId(5557);
            val = subtotal * tax;
            val = val * 100;
            val = Math.round(val);
            val = val / 100;
            itemTotalDue2.setText("$" + val);
            itemTotalDue2.setTextColor(Color.parseColor("#fb1d91db"));
            itemTotalDue2.setTextAlignment(3);
            itemTotalDue2.setTextSize(20);
       //     itemTotalDue2.setTypeface(face);
            tr3.addView(itemTotalDue2);
            tl.addView(tr);
            tl.addView(tr2);
            tl.addView(tr3);
            TableRow tr4 = new TableRow(PaymentActivity.this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT  ,
                    ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);
            params.setMargins(10,10,10,10);
           params.gravity= Gravity.CENTER;
            Button payBill = new Button(PaymentActivity.this);
            payBill.setId(999);
            payBill.setText("Pay Bill");
            payBill.setLayoutParams(params);
        //    payBill.setTypeface(face);
            payBill.setBackgroundColor(Color.parseColor("#fb1d91db"));
            payBill.setTextColor(Color.WHITE);

          //  payBill.getLayoutParams().width=50;
           // payBill.getLayoutParams().height=300;
            payBill.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent i = new Intent(PaymentActivity.this, PayBillActivity.class);

                    startActivity(i);
                   // finish();


                }
            });
            Button splitItems = new Button(PaymentActivity.this);
            splitItems.setId(998);
            splitItems.setText("Split Items");
            splitItems.setLayoutParams(params);
        //    splitItems.setTypeface(face);
            splitItems.setBackgroundColor(Color.parseColor("#fb1d91db"));
            splitItems.setTextColor(Color.WHITE);
           // splitItems.getLayoutParams().width=50;
            //splitItems.getLayoutParams().height=300;
            splitItems.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent i = new Intent(PaymentActivity.this, SplitItemsActivity.class);
                    i.putExtra("tableNumber", getIntent().getExtras().getInt("tableNumber"));
                    startActivity(i);
                  //  finish();



                }
            });
            Button inviteSomeone = new Button(PaymentActivity.this);
            inviteSomeone.setId(997);
            inviteSomeone.setText("Pay For Someone Else");
            inviteSomeone.setLayoutParams(params);
           // inviteSomeone.getLayoutParams().width=50;
           //inviteSomeone.getLayoutParams().height=300;
          //  inviteSomeone.setTypeface(face);
            inviteSomeone.setBackgroundColor(Color.parseColor("#fb1d91db"));
            inviteSomeone.setTextColor(Color.WHITE);

            inviteSomeone.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent i = new Intent(PaymentActivity.this, InviteSomeoneActivity.class);

                    i.putExtra("tableNumber", getIntent().getExtras().getInt("tableNumber"));
                    startActivity(i);
                    // finish();


                }
            });
            Button refresh = new Button(PaymentActivity.this);

           refresh.setText("Refresh");
            refresh.setLayoutParams(params);
            // inviteSomeone.getLayoutParams().width=50;
            //inviteSomeone.getLayoutParams().height=300;
            //  inviteSomeone.setTypeface(face);
            refresh.setBackgroundColor(Color.parseColor("#fb1d91db"));
            refresh.setTextColor(Color.WHITE);

          refresh.setOnClickListener(new OnClickListener() {

              @Override
              public void onClick(View v) {

                  tl.invalidate();
                  tl.refreshDrawableState();
                  recreate();


              }
          });
            ll.addView(payBill);
            ll.addView(splitItems);
            ll.addView(inviteSomeone);
            ll.addView(refresh);


        }
	}


}
