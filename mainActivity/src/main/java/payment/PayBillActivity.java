
package payment;

import com.hmkcode.android.sign.R;
import com.vinaygaba.creditcardview.CreditCardView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PayBillActivity extends FragmentActivity  {


    private static final String TAG = PayBillActivity.class.getName();

    private static String url_menu = "http://52.11.144.56/menu.php";
	
	public void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.payment_pay_bill_screen);
		Button confirmPayment = (Button) findViewById(R.id.confirmPaymentButton);
		confirmPayment.setBackgroundColor(Color.parseColor("#fb1d91db"));
		confirmPayment.setTextColor(Color.WHITE);
		ActionBar bar = getActionBar();
//for color
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fb1d91db")));
		confirmPayment.setOnClickListener(new OnClickListener() {




            @Override
			public void onClick(View v) {

                CreditCardView creditCardView= (CreditCardView)findViewById(R.id.card1);
                String cardNumber=creditCardView.getCardNumber();
                String name=creditCardView.getCardName();
                String date=creditCardView.getExpiryDate();
                Log.d(TAG, cardNumber);

    if(!cardNumber.equals("5559")&&!name.equals("FirstName LastName")&&!date.equals("mm/yy")) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(PayBillActivity.this);
        alertbox.setMessage("Paid Successfully!");


        // show it

				/*alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// the button was clicked

					}
				});*/
        alertbox.show();


        Intent i = new Intent(PayBillActivity.this, ReviewPageActivity.class);

		startActivity(i);
    }
        else{ AlertDialog.Builder alertbox = new AlertDialog.Builder(PayBillActivity.this);
            alertbox.setMessage("Info is missing or incorrect! Please try again.");


            // show it

				alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// the button was clicked

					}
				});
            alertbox.show();}


		}
	});

	}


}
