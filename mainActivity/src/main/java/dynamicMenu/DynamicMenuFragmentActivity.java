
package dynamicMenu;

import com.hmkcode.android.sign.R;

import android.R.color;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import java.util.Calendar;

import globalVariables.GlobalVariable;
import homePage.MyAlarmReceiver;


public class DynamicMenuFragmentActivity extends FragmentActivity implements ActionBar.TabListener {

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    static ViewPager mViewPager;
    static int tableNumber;

    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.dynamic_menu_main_fragment);
        ActionBar bar = getActionBar();
//for color
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fb1d91db")));
        Bundle b = getIntent().getExtras();
        tableNumber = GlobalVariable.getTableNumber();
        GlobalVariable.setTableNumber(tableNumber);
       /* AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, 5);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);*/

        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        final ActionBar actionBar = getActionBar();

        actionBar.setHomeButtonEnabled(false);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setBackgroundColor(Color.WHITE);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
           /* View tabView = new View(this);
            tabView.setBackgroundColor(Color.WHITE); // set custom color
            tab.setCustomView(tabView);*/

            actionBar.addTab(actionBar.newTab().setText(mAppSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));

        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {


        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {

                case 0:

                    Fragment fragment1 = new DummySectionFragment();
                    Bundle args1 = new Bundle();
                    args1.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    args1.putInt("tableNumber", tableNumber);
                    fragment1.setArguments(args1);
                    return fragment1;
                case 1:

                    Fragment fragment2 = new DummySectionFragment();
                    Bundle args2 = new Bundle();
                    args2.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    args2.putInt("tableNumber", tableNumber);
                    fragment2.setArguments(args2);
                    return fragment2;
                case 2:
                    Fragment fragment3 = new DummySectionFragment();
                    Bundle args3 = new Bundle();
                    args3.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    args3.putInt("tableNumber", tableNumber);
                    fragment3.setArguments(args3);
                    return fragment3;
                case 3:

                    Fragment fragment4 = new DummySectionFragment();
                    Bundle args4 = new Bundle();
                    args4.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    args4.putInt("tableNumber", tableNumber);
                    fragment4.setArguments(args4);
                    return fragment4;
                case 4:

                    Fragment fragment5 = new SummaryPageFragment();
                    Bundle args5 = new Bundle();
                    args5.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    Log.e("Waaay", tableNumber + "");
                    args5.putInt("tableNumber", tableNumber);
                    fragment5.setArguments(args5);
                    return fragment5;

                default:

                    Fragment fragment6 = new DummySectionFragment();
                    Bundle args6 = new Bundle();
                    args6.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    args6.putInt("tableNumber", tableNumber);
                    fragment6.setArguments(args6);
                    return fragment6;

            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                //case 0:

                //return "Starters";

                case 0:

                    return "BREAKFAST";
                case 1:

                    return "LUNCH & DINNER";
                case 2:

                    return "DESSERTS";
                case 3:

                    return "DRINKS";
                case 4:

                    return "SUMMARY PAGE";
                default:

                    return "LUNCH & DINNER";
            }
        }
    }


    public static class DummySectionFragment extends Fragment {


        public static int item1 = 0;
        public static int item2 = 1;
        public static int item3 = 2;
        public static int item4 = 3;
        public static int item5 = 4;
        public static int item6 = 5;
        public static int item7 = 6;

        public static int numItems_breakfast = 5;
        public static int numItems_lunchanddinner = 7;
        public static int numItems_dessert = 3;
        public static int numItems_drinks = 5;

        //need a count for each fragment
        public static int[] count_1 = new int[numItems_breakfast];
        public static int[] count_2 = new int[numItems_lunchanddinner];
        public static int[] count_3 = new int[numItems_dessert];
        public static int[] count_4 = new int[numItems_drinks];
        public static String[] breakfast = new String[numItems_breakfast];
        public static double[] breakfast_price = new double[numItems_breakfast];
        public static String[] breakfast_nutrition = new String[numItems_breakfast];
        public static String[] breakfast_ingredients = new String[numItems_breakfast];
        public static String[] lunchanddinner = new String[numItems_lunchanddinner];
        public static double[] lunchanddinner_price = new double[numItems_lunchanddinner];
        public static String[] lunchanddinner_nutrition = new String[numItems_lunchanddinner];
        public static String[] lunchanddinner_ingredients = new String[numItems_lunchanddinner];
        public static String[] desserts = new String[numItems_dessert];
        public static double[] desserts_price = new double[numItems_dessert];
        public static String[] dessert_nutrition = new String[numItems_dessert];
        public static String[] dessert_ingredients = new String[numItems_dessert];
        public static String[] drinks = new String[numItems_drinks];
        public static double[] drinks_price = new double[numItems_drinks];
        public static String[] drinks_nutrition = new String[numItems_drinks];
        public static String[] breakfast_comments = new String[numItems_breakfast];
        public static String[] lunchanddinner_comments = new String[numItems_lunchanddinner];
        public static String[] desserts_comments = new String[numItems_dessert];
        public static String[] drinks_comments= new String[numItems_drinks];




        public static final String ARG_SECTION_NUMBER = "section_number";


        @SuppressWarnings("deprecation")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            breakfast[item1] = "Steak and Egg";
            breakfast[item2] = "Breakfast Burrito";
            breakfast[item3] = "Pancakes";
            breakfast[item4] = "Oatmeal";
            breakfast[item5] = "3 Cheese Omlette";

            breakfast_price[item1] = 17.99;
            breakfast_price[item2] = 13.49;
            breakfast_price[item3] = 11.49;
            breakfast_price[item4] = 5.49;
            breakfast_price[item5] = 13.99;

            final int[] breakfast_images = new int[]{R.drawable.steak_and_eggs, R.drawable.breakfast_burrito, R.drawable.pancakes, R.drawable.oatmeal, R.drawable.cheeseomlette};

            breakfast_nutrition[item1] = "Amount per 200g \nCalories\t 500 \nTotal Fat\t 35g \nTotal Carbohydrates\t 1g \n\t\t\t Sugars\t 0g \n\t\t\t Fibre\t 1g";
            breakfast_nutrition[item2] = "Amount per 183g \nCalories\t 480 \nTotal Fat\t 29g \nTotal Carbohydrates\t 29g \n\t\t\t Sugars\t 1g \n\t\t\t Fibre\t 2g";
            breakfast_nutrition[item3] = "Amount per 116g \nCalories\t 280 \nTotal Fat\t 9g \nTotal Carbohydrates\t 45g \n\t\t\t Sugars\t 12g \n\t\t\t Fibre\t 1g";
            breakfast_nutrition[item4] = "Amount per 241g \nCalories\t 230 \nTotal Fat\t 5g \nTotal Carbohydrates\t 42g \n\t\t\t Sugars\t 10.8g \n\t\t\t Fibre\t 5g";
            breakfast_nutrition[item5] = "Amount per 112g \nCalories\t 290 \nTotal Fat\t 23g \nTotal Carbohydrates\t 4g \n\t\t\t Sugars\t 1g \n\t\t\t Fibre\t 1g";

            breakfast_ingredients[item1] = "2 New York strip steaks(10 ounces each), with 3 large eggs";
            breakfast_ingredients[item2] = "3 eggs, red onions, red bell pepper, pepper Jack cheese, and black beans";
            breakfast_ingredients[item3] = "4 pancakes served with a side of fresh fruits";
            breakfast_ingredients[item4] = "plain oatmeal served with a side of dried fruits and fresh fruits";
            breakfast_ingredients[item5] = "3 eggs, with parmesan cheese, cheddar cheese and swiss cheese";

            lunchanddinner[item1] = "Grilled Cheese";
            lunchanddinner[item2] = "Crispy Chicken";
            lunchanddinner[item3] = "Chicken Caesar";
            lunchanddinner[item4] = "BLT";
            lunchanddinner[item5] = "Pulled Pork";
            lunchanddinner[item6] = "Chilli Burger";
            lunchanddinner[item7] = "Nachos";

            lunchanddinner_price[item1] = 13.99;
            lunchanddinner_price[item2] = 13.49;
            lunchanddinner_price[item3] = 13.49;
            lunchanddinner_price[item4] = 9.99;
            lunchanddinner_price[item5] = 13.49;
            lunchanddinner_price[item6] = 13.99;
            lunchanddinner_price[item7] = 11.49;

            final int[] lunchanddinner_images = new int[]{R.drawable.grilled_cheese, R.drawable.crispy_chicken, R.drawable.chicken_caesar, R.drawable.blt, R.drawable.pulled_pork, R.drawable.chilli_burger, R.drawable.nachos};

            lunchanddinner_nutrition[item1] = "Amount per 315g \nCalories\t 750 \nTotal Fat\t 36g \nTotal Carbohydrates\t 66g \n\t\t\t Sugars\t 7g \n\t\t\t Fibre\t 4g";
            lunchanddinner_nutrition[item2] = "Amount per 458g \nCalories\t 480 \nTotal Fat\t 8g \nTotal Carbohydrates\t 45g \n\t\t\t Sugars\t 8g \n\t\t\t Fibre\t 0g";
            lunchanddinner_nutrition[item3] = "Amount per 248g \nCalories\t 290 \nTotal Fat\t 16g \nTotal Carbohydrates\t 16g \n\t\t\t Sugars\t 2g \n\t\t\t Fibre\t 3g";
            lunchanddinner_nutrition[item4] = "Amount per 244g \nCalories\t 463 \nTotal Fat\t 19g \nTotal Carbohydrates\t 51g \n\t\t\t Sugars\t 7g \n\t\t\t Fibre\t 5g";
            lunchanddinner_nutrition[item5] = "Amount per 163g \nCalories\t 464 \nTotal Fat\t 18g \nTotal Carbohydrates\t 54g \n\t\t\t Sugars\t 24g \n\t\t\t Fibre\t 3g";
            lunchanddinner_nutrition[item6] = "Amount per 228g \nCalories\t 280 \nTotal Fat\t 13g \nTotal Carbohydrates\t 31g \n\t\t\t Sugars\t 5g \n\t\t\t Fibre\t 3g";
            lunchanddinner_nutrition[item7] = "Amount per 204g \nCalories\t 440 \nTotal Fat\t 18g \nTotal Carbohydrates\t 42g \n\t\t\t Sugars\t 5g \n\t\t\t Fibre\t 4g";

            lunchanddinner_ingredients[item1] = "Mozzarella and cheddar cheese";
            lunchanddinner_ingredients[item2] = "Served with lettuce, tomatoes, mayonnaise, and a side of fries";
            lunchanddinner_ingredients[item3] = "Served with mozzarella cheese, caesar dressing, tomatoes and lettuce ";
            lunchanddinner_ingredients[item4] = "Served with tomatoes, lettuce, mayonnaise";
            lunchanddinner_ingredients[item5] = "Served with bbq sauce and a side of fries";
            lunchanddinner_ingredients[item6] = "Servd with cheddar cheese, onions, jalapeno peppers, black beans, and a side of fries ";
            lunchanddinner_ingredients[item7] = "Served with tomatoes, jalapeno peppers, sour cream, salsa, black olives, and chicken";


            desserts[item1] = "Cheescake";
            desserts[item2] = "Fried Mars Bar";
            desserts[item3] = "Banana Split";

            desserts_price[item1] = 6.99;
            desserts_price[item2] = 6.99;
            desserts_price[item3] = 7.99;

            final int[] dessert_images = new int[]{R.drawable.cheesecake, R.drawable.friedmars, R.drawable.bananasplits,};

            dessert_nutrition[item1] = "Amount per 226g \nCalories\t 340 \nTotal Fat\t 13g \nTotal Carbohydrates\t 47g \n\t\t\t Sugars\t 34g \n\t\t\t Fibre\t 1g";
            dessert_nutrition[item2] = "Amount per 210g \nCalories\t 640 \nTotal Fat\t 28g \nTotal Carbohydrates\t 96g \n\t\t\t Sugars\t 40g \n\t\t\t Fibre\t 1g";
            dessert_nutrition[item3] = "Amount per 112g \nCalories\t 280 \nTotal Fat\t 16g \nTotal Carbohydrates\t 31g \n\t\t\t Sugars\t 27g \n\t\t\t Fibre\t 0g";

            dessert_ingredients[item1] = " ";
            dessert_ingredients[item2] = " ";
            dessert_ingredients[item3] = "3 scoops of vanilla icecream with chocolate syrup";

            drinks[item1] = "Orange Juice";
            drinks[item2] = "Tea";
            drinks[item3] = "Coffee";
            drinks[item4] = "Hot chocolate";
            drinks[item5] = "Mint chocolate";

            final int[] drinks_images = new int[]{R.drawable.orange_juice, R.drawable.tea, R.drawable.coffee, R.drawable.hot_chocolate, R.drawable.mint_chocolate};

            drinks_nutrition[item1] = "Amount per 100g \nCalories\t 45 \nTotal Fat\t 0g \nTotal Carbohydrates\t 19g \n\t\t\t Sugars\t 14g \n\t\t\t Fibre\t 0.2g";
            drinks_nutrition[item2] = "Amount per 100g \nCalories\t 1 \nTotal Fat\t 0g \nTotal Carbohydrates\t 0.2g \n\t\t\t Sugars\t 0g \n\t\t\t Fibre\t 0g";
            drinks_nutrition[item3] = "Amount per 100g \nCalories\t 0 \nTotal Fat\t 0g \nTotal Carbohydrates\t 0g \n\t\t\t Sugars\t 0g \n\t\t\t Fibre\t 0g";
            drinks_nutrition[item4] = "Amount per 100g \nCalories\t 400 \nTotal Fat\t 15g \nTotal Carbohydrates\t 75g \n\t\t\t Sugars\t 60g \n\t\t\t Fibre\t 4g";
            drinks_nutrition[item5] = "Amount per 100g \nCalories\t 320 \nTotal Fat\t 18g \nTotal Carbohydrates\t 55g \n\t\t\t Sugars\t 40g \n\t\t\t Fibre\t 4g";

            drinks_price[item1] = 2.99;
            drinks_price[item2] = 2.49;
            drinks_price[item3] = 2.49;
            drinks_price[item4] = 3.49;
            drinks_price[item5] = 3.49;

            View rootView = inflater.inflate(R.layout.dynamic_menu_fragment_section_dummy, container, false);
            Bundle args = getArguments();

            int temp = args.getInt(ARG_SECTION_NUMBER);

            //if breakfast fragment
            if (temp == 1) {
                // Get the TableLayout
                TableLayout tl = (TableLayout) rootView.findViewById(R.id.maintable);
                ScrollView ll = (ScrollView) rootView.findViewById(R.id.scrollview1);
                tl.setBackgroundColor(Color.WHITE);
                ll.setBackgroundColor(Color.WHITE);
                //ll.setBackground(getResources().getDrawable(R.drawable.menu));
                // Go through each item in the array
                for (int current = 0; current < numItems_breakfast; current++) {
                    final int current_temp = current; //need this for the counter because it has to be final

                    // Create a TableRow and give it an ID
                    TableRow tr = new TableRow(getActivity());
                    tr.setId(1000 + current);

                    // Create a TextView
                    TextView labelTV = new TextView(DummySectionFragment.this.getActivity());
                    labelTV.setId(2000 + current);
                    labelTV.setText(breakfast[current]);
                    labelTV.setTextColor(Color.parseColor("#fb1d91db"));
                    //Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
                         //   "Lobster_1.3.otf");
                   // labelTV.setTypeface(face);
                    labelTV.setTextAlignment(3);
                    labelTV.setTextSize(20);
                    android.widget.TableRow.LayoutParams params = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);


                    //create the more info button
                    Button moreinfo = new Button(getActivity());
                    moreinfo.setId(3000 + current);
                    moreinfo.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_moreinfobutton));
                    moreinfo.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            View popupView = layoutInflater.inflate(R.layout.dynamic_menu_pop_up, null,false);

                            final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                            popupWindow.showAtLocation(popupView, Gravity.CENTER, 10, 10);
                            popupWindow.setHeight(400);
                            popupWindow.setWidth(500);
                            TextView nutritionTextView = (TextView) popupView.findViewById(R.id.popuptext);
                            nutritionTextView.setText(breakfast_nutrition[current_temp]);
                            TextView ingredientsTextView = (TextView) popupView.findViewById(R.id.popuptext2);
                            ingredientsTextView.setText(breakfast_ingredients[current_temp]);
                            ingredientsTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                            ImageView nutritionImageView = (ImageView) popupView.findViewById(R.id.popupimage);
                            nutritionImageView.setImageResource(breakfast_images[current_temp]);
                            Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                            btnDismiss.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    });

                    //Create a TextView for price
                    TextView valueTV = new TextView(getActivity());
                    valueTV.setId(4000 + current);
                    valueTV.setText("$" + breakfast_price[current]);
                    valueTV.setTextAlignment(15);
                    valueTV.setTextSize(20);
                    valueTV.setTextColor(Color.parseColor("#fb1d91db"));

                //    valueTV.setTypeface(face);

                    valueTV.setLayoutParams(params);


                    //create the ordering button capabilities
                    Button add = new Button(getActivity());
                    add.setText("+");
                    add.setTextColor(Color.parseColor("#fb1d91db"));
                    add.setBackgroundColor(Color.WHITE);

                    Button subtract = new Button(getActivity());
                    subtract.setText("-");
                    subtract.setTextColor(Color.parseColor("#fb1d91db"));
                    subtract.setBackgroundColor(Color.WHITE);


                    final TextView counter = new TextView(getActivity());
                    counter.setId(current);
                    counter.setText(String.valueOf(count_1[current]));
                    counter.setGravity(Gravity.CENTER);
                    counter.setTextColor(Color.parseColor("#fb1d91db"));
                    counter.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_blacksquare));

                    add.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            count_1[current_temp]++;
                            counter.setText(String.valueOf(count_1[current_temp]));
                        }
                    });
                    subtract.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            if (count_1[current_temp] > 0) {
                                count_1[current_temp]--;
                                counter.setText(String.valueOf(count_1[current_temp]));
                            }
                        }
                    });


                    // Add the TableRow to the TableLayout
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0.5f);

                    labelTV.setLayoutParams(params1);

                    TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0f);

                    TableRow.LayoutParams paramsx = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT, 0.1f);

                    counter.setLayoutParams(paramsx);
                    subtract.setLayoutParams(paramsx);
                    add.setLayoutParams(paramsx);
                    moreinfo.setLayoutParams(params2);
                    valueTV.setLayoutParams(params2);


                    tr.addView(labelTV);
                    tr.addView(moreinfo);
                    tr.addView(valueTV);
                    tr.addView(subtract);
                    tr.addView(counter);
                    tr.addView(add);


                    TableRow tr2 = new TableRow(getActivity());
                    final EditText comment=new EditText(this.getActivity());
                    Button addComment = new Button(getActivity());
                    addComment.setText("Add Comment");
                    addComment.setTextColor(Color.WHITE);
                    addComment.setBackgroundColor(Color.parseColor("#fb1d91db"));
                    addComment.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            breakfast_comments[current_temp] = comment.getText().toString();
                        }
                    });
                    tr2.addView(comment);
                    tr2.addView(addComment);

                    tl.addView(tr);
                    tl.addView(tr2);
                }
                Button summary = new Button(getActivity());
                summary.setId(5000);
                summary.setText("Proceed to Checkout");
                summary.setBackgroundColor(Color.parseColor("#fb1d91db"));
                summary.setTextColor(Color.WHITE);
             //   Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
               //         "Lobster_1.3.otf");

              //  summary.setTypeface(face);
                summary.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        mViewPager.setCurrentItem(4);
                    }
                });
                TableRow tr = new TableRow(getActivity());
                tr.setId(1000 + 20);
                tr.addView(summary);
                tl.addView(tr);
            }


            //if lunch and dinner fragment
            if (temp == 2) {
                // Get the TableLayout
                TableLayout tl = (TableLayout) rootView.findViewById(R.id.maintable);
                ScrollView ll = (ScrollView) rootView.findViewById(R.id.scrollview1);
                tl.setBackgroundColor(Color.WHITE);
                ll.setBackgroundColor(Color.WHITE);
                for (int current = 0; current < numItems_lunchanddinner; current++) {
                    final int current_temp = current; //need this for the counter because it has to be final

                    // Create a TableRow and give it an ID
                    TableRow tr = new TableRow(getActivity());
                    tr.setId(10000 + current);

                    // Create a TextView
                    TextView labelTV = new TextView(DummySectionFragment.this.getActivity());
                    labelTV.setId(20000 + current);
                    labelTV.setText(lunchanddinner[current]);
                    labelTV.setTextColor(Color.parseColor("#fb1d91db"));

                 //   Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
                   //         "Lobster_1.3.otf");
                  //  labelTV.setTypeface(face);
                    labelTV.setTextAlignment(3);
                    labelTV.setTextSize(20);
                    android.widget.TableRow.LayoutParams params = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);


                    //create the more info button
                    Button moreinfo = new Button(getActivity());
                    moreinfo.setId(30000 + current);
                    moreinfo.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_moreinfobutton));
                    moreinfo.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            View popupView = layoutInflater.inflate(R.layout.dynamic_menu_pop_up, null);

                            final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                            popupWindow.showAtLocation(popupView, Gravity.CENTER, 10, 10);
                            popupWindow.setHeight(400);
                            popupWindow.setWidth(500);
                            TextView nutritionTextView = (TextView) popupView.findViewById(R.id.popuptext);
                            nutritionTextView.setText(lunchanddinner_nutrition[current_temp]);
                            TextView ingredientsTextView = (TextView) popupView.findViewById(R.id.popuptext2);
                            ingredientsTextView.setText(lunchanddinner_ingredients[current_temp]);
                            ingredientsTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                            ImageView nutritionImageView = (ImageView) popupView.findViewById(R.id.popupimage);
                            nutritionImageView.setImageResource(lunchanddinner_images[current_temp]);
                            Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                            btnDismiss.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    });


                    //Create a TextView for price
                    TextView valueTV = new TextView(getActivity());
                    valueTV.setId(40000 + current);
                    valueTV.setText("$" + lunchanddinner_price[current]);
                    valueTV.setTextAlignment(15);
                    valueTV.setTextSize(20);
                    valueTV.setTextColor(Color.parseColor("#fb1d91db"));

                   // valueTV.setTypeface(face);

                    valueTV.setLayoutParams(params);


                    //create the ordering button capabilities
                    Button add = new Button(getActivity());
                    add.setText("+");
                    add.setTextColor(Color.parseColor("#fb1d91db"));
                    add.setBackgroundColor(Color.WHITE);
                    Button subtract = new Button(getActivity());
                    subtract.setText("-");
                    subtract.setTextColor(Color.parseColor("#fb1d91db"));
                    subtract.setBackgroundColor(Color.WHITE);
                    final TextView counter = new TextView(getActivity());
                    counter.setId(50000 + current);
                    counter.setText(String.valueOf(count_2[current]));
                    counter.setGravity(Gravity.CENTER);
                    counter.setTextColor(Color.parseColor("#fb1d91db"));

                    counter.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_blacksquare));

                    add.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            count_2[current_temp]++;
                            counter.setText(String.valueOf(count_2[current_temp]));
                        }
                    });
                    subtract.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            if (count_2[current_temp] > 0) {
                                count_2[current_temp]--;
                                counter.setText(String.valueOf(count_2[current_temp]));
                            }
                        }
                    });

                    // Add the TableRow to the TableLayout
                    // Add the TableRow to the TableLayout
                    TableRow.LayoutParams params3 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0.5f);

                    labelTV.setLayoutParams(params3);

                    TableRow.LayoutParams params4 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0f);
                    TableRow.LayoutParams paramsy = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT, 0.1f);

                    counter.setLayoutParams(paramsy);
                    subtract.setLayoutParams(paramsy);
                    add.setLayoutParams(paramsy);
                    moreinfo.setLayoutParams(params4);
                    valueTV.setLayoutParams(params4);


                    tr.addView(labelTV);
                    tr.addView(moreinfo);
                    tr.addView(valueTV);
                    tr.addView(subtract);
                    tr.addView(counter);
                    tr.addView(add);
                    TableRow tr2 = new TableRow(getActivity());
                    final EditText comment=new EditText(this.getActivity());
                    Button addComment = new Button(getActivity());
                    addComment.setText("Add Comment");
                    addComment.setTextColor(Color.WHITE);
                    addComment.setBackgroundColor(Color.parseColor("#fb1d91db"));
                    addComment.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            lunchanddinner_comments[current_temp] = comment.getText().toString();
                        }
                    });
                    tr2.addView(comment);
                    tr2.addView(addComment);

                    tl.addView(tr);
                    tl.addView(tr2);


                }
                Button summary = new Button(getActivity());
                summary.setId(5001);
                summary.setText("Proceed to Checkout");
                summary.setBackgroundColor(Color.parseColor("#fb1d91db"));
                summary.setTextColor(Color.WHITE);
                //Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
//                        "Lobster_1.3.otf");

               //summary.setTypeface(face);
                summary.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        mViewPager.setCurrentItem(4);
                    }
                });
                TableRow tr = new TableRow(getActivity());
                tr.setId(1000 + 21);
                tr.addView(summary);
                tl.addView(tr);
            }

            //if Dessert fragment
            if (temp == 3) {
                // Get the TableLayout
                TableLayout tl = (TableLayout) rootView.findViewById(R.id.maintable);
                ScrollView ll = (ScrollView) rootView.findViewById(R.id.scrollview1);
                tl.setBackgroundColor(Color.WHITE);
                ll.setBackgroundColor(Color.WHITE);

                for (int current = 0; current < numItems_dessert; current++) {
                    final int current_temp = current; //need this for the counter because it has to be final

                    // Create a TableRow and give it an ID
                    TableRow tr = new TableRow(getActivity());
                    tr.setId(100000 + current);

                    // Create a TextView for name
                    TextView labelTV = new TextView(DummySectionFragment.this.getActivity());
                    labelTV.setId(200000 + current);
                    labelTV.setText(desserts[current]);
                    labelTV.setTextColor(Color.parseColor("#fb1d91db"));

                   // Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
                     //       "Lobster_1.3.otf");
                   // labelTV.setTypeface(face);
                    labelTV.setTextAlignment(3);
                    labelTV.setTextSize(20);
                    android.widget.TableRow.LayoutParams params = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);


                    //create the more info button
                    Button moreinfo = new Button(getActivity());
                    moreinfo.setId(300000 + current);
                    moreinfo.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_moreinfobutton));
                    moreinfo.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            View popupView = layoutInflater.inflate(R.layout.dynamic_menu_pop_up, null);

                            final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                            popupWindow.showAtLocation(popupView, Gravity.CENTER, 10, 10);
                            popupWindow.setHeight(400);
                            popupWindow.setWidth(500);
                            TextView nutritionTextView = (TextView) popupView.findViewById(R.id.popuptext);
                            nutritionTextView.setText(dessert_nutrition[current_temp]);
                            TextView ingredientsTextView = (TextView) popupView.findViewById(R.id.popuptext2);
                            ingredientsTextView.setText(dessert_ingredients[current_temp]);
                            ingredientsTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                            ImageView nutritionImageView = (ImageView) popupView.findViewById(R.id.popupimage);
                            nutritionImageView.setImageResource(dessert_images[current_temp]);
                            Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                            btnDismiss.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    });


                    //Create a TextView for price
                    TextView valueTV = new TextView(getActivity());
                    valueTV.setId(400000 + current);
                    valueTV.setText("$" + desserts_price[current]);
                    valueTV.setTextAlignment(15);
                    valueTV.setTextSize(20);
                    valueTV.setTextColor(Color.parseColor("#fb1d91db"));
                    valueTV.setLayoutParams(params);
                    //valueTV.setTypeface(face);


                    //create the ordering button capabilities
                    Button add = new Button(getActivity());
                    add.setText("+");

                    add.setTextColor(Color.parseColor("#fb1d91db"));
                    add.setBackgroundColor(Color.WHITE);
                    Button subtract = new Button(getActivity());
                    subtract.setText("-");

                    subtract.setTextColor(Color.parseColor("#fb1d91db"));
                   subtract.setBackgroundColor(Color.WHITE);
                    final TextView counter = new TextView(getActivity());
                    counter.setId(500000 + current);
                    counter.setText(String.valueOf(count_3[current]));
                    counter.setGravity(Gravity.CENTER);
                    counter.setTextColor(Color.parseColor("#fb1d91db"));
                    counter.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_blacksquare));
                    add.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            count_3[current_temp]++;
                            counter.setText(String.valueOf(count_3[current_temp]));
                        }
                    });
                    subtract.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            if (count_3[current_temp] > 0) {
                                count_3[current_temp]--;
                                counter.setText(String.valueOf(count_3[current_temp]));
                            }
                        }
                    });

                    // Add the TableRow to the TableLayout

                    TableRow.LayoutParams params5 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0.5f);

                    labelTV.setLayoutParams(params5);

                    TableRow.LayoutParams params6 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0f);

                    TableRow.LayoutParams paramsz = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT, 0.1f);

                    counter.setLayoutParams(paramsz);
                    subtract.setLayoutParams(paramsz);
                    add.setLayoutParams(paramsz);
                    moreinfo.setLayoutParams(params6);
                    valueTV.setLayoutParams(params6);


                    tr.addView(labelTV);
                    tr.addView(moreinfo);
                    tr.addView(valueTV);
                    tr.addView(subtract);
                    tr.addView(counter);
                    tr.addView(add);

                    TableRow tr2 = new TableRow(getActivity());
                    final EditText comment=new EditText(this.getActivity());
                    Button addComment = new Button(getActivity());
                    addComment.setText("Add Comment");
                    addComment.setTextColor(Color.WHITE);
                    addComment.setBackgroundColor(Color.parseColor("#fb1d91db"));
                    addComment.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                           desserts_comments[current_temp] = comment.getText().toString();
                        }
                    });
                    tr2.addView(comment);
                    tr2.addView(addComment);
                    tl.addView(tr);
                    tl.addView(tr2);

                }
                Button summary = new Button(getActivity());
                summary.setId(5002);
                summary.setText("Proceed to Checkout");
               // Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
                 //       "Lobster_1.3.otf");
                summary.setBackgroundColor(Color.parseColor("#fb1d91db"));
                summary.setTextColor(Color.WHITE);
              //  summary.setTypeface(face);
                summary.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        mViewPager.setCurrentItem(4);
                    }
                });
                TableRow tr = new TableRow(getActivity());
                tr.setId(1000 + 22);
                tr.addView(summary);
                tl.addView(tr);
            }


            //if drinks fragment
            if (temp == 4) {

                // Get the TableLayout
                TableLayout tl = (TableLayout) rootView.findViewById(R.id.maintable);
                ScrollView ll = (ScrollView) rootView.findViewById(R.id.scrollview1);
                tl.setBackgroundColor(Color.WHITE);
                ll.setBackgroundColor(Color.WHITE);
                for (int current = 0; current < numItems_drinks; current++) {
                    final int current_temp = current; //need this for the counter because it has to be final

                    // Create a TableRow and give it an ID
                    TableRow tr = new TableRow(getActivity());
                    tr.setId(100 + current);


                    TextView labelTV = new TextView(DummySectionFragment.this.getActivity());


                    labelTV.setId(200 + current);
                    labelTV.setText(drinks[current]);
                    labelTV.setTextColor(Color.parseColor("#fb1d91db"));
               //     Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
                 //           "Lobster_1.3.otf");
                //    labelTV.setTypeface(face);
                    labelTV.setTextAlignment(3);
                    labelTV.setTextSize(20);
                    android.widget.TableRow.LayoutParams params = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);


                    //create the more info button
                    final Button moreinfo = new Button(getActivity());
                    moreinfo.setId(300 + current);
                    moreinfo.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_moreinfobutton));
                    moreinfo.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            View popupView = layoutInflater.inflate(R.layout.dynamic_menu_pop_up, null);

                            final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                            popupWindow.showAtLocation(popupView, Gravity.CENTER, 10, 10);
                            popupWindow.setHeight(400);
                            popupWindow.setWidth(500);
                            TextView nutritionTextView = (TextView) popupView.findViewById(R.id.popuptext);
                            nutritionTextView.setText(drinks_nutrition[current_temp]);
                            ImageView nutritionImageView = (ImageView) popupView.findViewById(R.id.popupimage);
                            nutritionImageView.setImageResource(drinks_images[current_temp]);
                            Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                            btnDismiss.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    });


                    //Create a TextView for price
                    TextView valueTV = new TextView(getActivity());
                    valueTV.setId(400 + current);
                    valueTV.setText("$" + drinks_price[current]);
                    valueTV.setTextAlignment(15);
                    valueTV.setTextSize(20);
                    valueTV.setTextColor(Color.parseColor("#fb1d91db"));
                    valueTV.setLayoutParams(params);
               //     valueTV.setTypeface(face);

                    //create the ordering button capabilities
                    Button add = new Button(getActivity());
                    add.setText("+");
                    add.setTextColor(Color.parseColor("#fb1d91db"));
                    add.setBackgroundColor(Color.WHITE);
                    Button subtract = new Button(getActivity());
                    subtract.setText("-");
                    subtract.setTextColor(Color.parseColor("#fb1d91db"));
                    subtract.setBackgroundColor(Color.WHITE);
                    final TextView counter = new TextView(getActivity());
                    counter.setId(current);
                    counter.setText(String.valueOf(count_4[current]));
                    counter.setGravity(Gravity.CENTER);
                    counter.setTextColor(Color.parseColor("#fb1d91db"));
                    counter.setBackgroundDrawable(getResources().getDrawable(R.drawable.rsz_blacksquare));

                    add.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            count_4[current_temp]++;
                            counter.setText(String.valueOf(count_4[current_temp]));
                        }
                    });
                    subtract.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            if (count_4[current_temp] > 0) {
                                count_4[current_temp]--;
                                counter.setText(String.valueOf(count_4[current_temp]));
                            }
                        }
                    });

                    // Add the TableRow to the TableLayout
                    TableRow.LayoutParams params7 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0.5f);

                    labelTV.setLayoutParams(params7);

                    TableRow.LayoutParams params8 = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.MATCH_PARENT, 0f);

                    TableRow.LayoutParams paramsw = new TableRow.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT, 0.1f);

                    counter.setLayoutParams(paramsw);
                    subtract.setLayoutParams(paramsw);
                    add.setLayoutParams(paramsw);
                    moreinfo.setLayoutParams(params8);
                    valueTV.setLayoutParams(params8);


                    tr.addView(labelTV);
                    tr.addView(moreinfo);
                    tr.addView(valueTV);
                    tr.addView(subtract);
                    tr.addView(counter);
                    tr.addView(add);

                    TableRow tr2 = new TableRow(getActivity());
                    final EditText comment=new EditText(this.getActivity());
                    Button addComment = new Button(getActivity());
                    addComment.setText("Add Comment");
                    addComment.setTextColor(Color.WHITE);
                    addComment.setBackgroundColor(Color.parseColor("#fb1d91db"));
                    addComment.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            drinks_comments[current_temp] = comment.getText().toString();
                        }
                    });
                    tr2.addView(comment);
                    tr2.addView(addComment);
                    tl.addView(tr);
                    tl.addView(tr2);
                }
                Button summary = new Button(getActivity());
                summary.setId(5003);
                summary.setText("Proceed to Checkout");
           //     Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),
             //           "Lobster_1.3.otf");
                summary.setBackgroundColor(Color.parseColor("#fb1d91db"));
                summary.setTextColor(Color.WHITE);
             //   summary.setTypeface(face);
                summary.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        mViewPager.setCurrentItem(4);
                    }
                });
                TableRow tr = new TableRow(getActivity());
                tr.setId(1000 + 23);
                tr.addView(summary);
                tl.addView(tr);
            }


            return rootView;

        }
    }

}
