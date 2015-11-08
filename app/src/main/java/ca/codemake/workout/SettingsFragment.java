package ca.codemake.workout;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.NutritionFact;

public class SettingsFragment extends Fragment {
    private boolean editMode = false;

/*    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    public SettingsFragment() {
        // Required empty public constructor
    }*/

    private FloatingActionButton fab;

    WorkoutDbHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        db = WorkoutDbHelper.getInstance(getActivity());

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        createViews(rootView);

        return rootView;
    }

    private void createViews(View rootView) {
        String[] categories = new String[]{"Calories", "Meals", "Nutrition Facts", "Macronutrients"};

        CardView cardView;
        CheckBox checkBox;
        TextView textView;
        EditText editText;
        ImageButton button;
        TableLayout tableLayout;

        LinearLayout container_view = (LinearLayout) rootView.findViewById(R.id.container_view);
        container_view.removeAllViews();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(15, 20, 15, 0);

//        cardView = new CardView(new ContextThemeWrapper(getActivity(), R.style.Setting_CardView));
//        LinearLayout.LayoutParams cardParm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        cardParm.setMargins(15, 15, 15, 15);
////        cardView.setLayoutParams(cardParm);
//
//        RelativeLayout inner_cardview = new RelativeLayout(new ContextThemeWrapper(getActivity(), R.style.Setting_CardViewContainer));
//
//        textView = new TextView(new ContextThemeWrapper(getActivity(), R.style.Setting_Title));
//        textView.setId(1);
//        textView.setText("TextView Title");
//
//        RelativeLayout.LayoutParams titleParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        titleParam.addRule(RelativeLayout.ALIGN_PARENT_START);
//        titleParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        titleParam.addRule(RelativeLayout.ALIGN_PARENT_END);
//
//
//        button = new Button(new ContextThemeWrapper(getActivity(), R.style.Setting_Button));
//        button.setId(2);
//        button.setBackgroundResource(android.R.drawable.ic_input_add);
//        button.setMinWidth(0);
//        button.setMinHeight(0);
//        button.setGravity(Gravity.CENTER_VERTICAL);
//        button.setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
//
//        RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        buttonParam.addRule(RelativeLayout.ALIGN_PARENT_END);
//        buttonParam.addRule(RelativeLayout.ALIGN_BASELINE, textView.getId());
//        buttonParam.addRule(RelativeLayout.ALIGN_TOP, textView.getId());
//        buttonParam.addRule(RelativeLayout.CENTER_VERTICAL);
//
////        buttonParam.setMargins(15, 15, 15, 15);
//
////        buttonParam.
//
//        inner_cardview.addView(textView, titleParam);
//        inner_cardview.addView(button, buttonParam);
//
//        cardView.addView(inner_cardview);

        /*
        *   -----------------------------------------------------------------------------------------------------
        */
        int id = 0;
        Cursor cursor = null;

        for (int i = 0; i < categories.length; i++) {

            if (i == 0) {
                cardView = (CardView) getActivity().getLayoutInflater().inflate(R.layout.card_view_template, null, false);
//                cardView.setId(id++);

                textView = (TextView) cardView.findViewById(R.id.title);
//                textView.setId(id++);
                textView.setText(categories[i]);

                button = (ImageButton) cardView.findViewById(R.id.button);
//                textView.setId(id++);
                button.setVisibility(View.GONE);

                tableLayout = (TableLayout) cardView.findViewById(R.id.table_layout);
                tableLayout.setVisibility(View.GONE);

                editText = new EditText(getContext());
                editText.setId(id++);
                editText.setHint("Total number of calories per day");

                LinearLayout linearLayout = (LinearLayout) tableLayout.getParent();
                linearLayout.addView(editText);

                container_view.addView(cardView, layoutParams);
            } else {
                cardView = (CardView) getActivity().getLayoutInflater().inflate(R.layout.card_view_template, null, false);
                cardView.setId(id++);

                textView = (TextView) cardView.findViewById(R.id.title);
                textView.setId(id++);
                textView.setText(categories[i]);

                button = (ImageButton) cardView.findViewById(R.id.button);
                button.setId(id++);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        onButtonClick(v);
                    }
                });

                tableLayout = (TableLayout) cardView.findViewById(R.id.table_layout);
                tableLayout.setId(id++);

                TableRow row = null;

                if (i == 1) {
                    cursor = db.getAllMeals();
                } else if (i == 2) {
                    cursor = db.getAllNutritionFacts();
                }

                if ((cursor != null) && (cursor.moveToFirst())) {
                    do {
                        Log.d("Tag", cursor.getString(0));

                        checkBox = new CheckBox(getContext());
                        checkBox.setText(cursor.getString(0));
                        if (i == 1) {
                            checkBox.setChecked(cursor.getInt(1) == 1);
                        } else if (i == 2) {
                            checkBox.setChecked(cursor.getInt(3) == 1);
                        }

                        row = (TableRow) tableLayout.getChildAt((tableLayout.getChildCount() - 1));

                        if (((row != null) && (row.getChildCount() < 2)) || ((tableLayout.getChildCount() != 0) && ((row != null) && (row.getChildCount() < 2)))) {
                            row.addView(checkBox);
                        } else {
                            row = new TableRow(getContext());
                            row.addView(checkBox);
                            tableLayout.addView(row);
                        }
                    } while (cursor.moveToNext());
                }

                container_view.addView(cardView, layoutParams);
            }

        }
        /*
        *   -----------------------------------------------------------------------------------------------------
        */
/*        cardView = (CardView) getActivity().getLayoutInflater().inflate(R.layout.card_view_template, null, false);
        cardView.setId(id++);

        textView = (TextView) cardView.findViewById(R.id.title);
        textView.setId(id++);
        textView.setText("Nutrition Facts");

        button = (ImageButton) cardView.findViewById(R.id.button);
        button.setId(id++);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick(v);
            }
        });

        tableLayout = (TableLayout) cardView.findViewById(R.id.table_layout);
        tableLayout.setId(id);

        cursor = db.getAllNutritionFacts();
        if(cursor.moveToFirst()) {
            do {
                Log.d("Tag", cursor.getString(0));

                checkBox = new CheckBox(getContext());
                checkBox.setText(cursor.getString(0));
                checkBox.setChecked(cursor.getInt(3) == 1 ? true : false);

                row = (TableRow) tableLayout.getChildAt((tableLayout.getChildCount() - 1));

                if (((row != null) && (row.getChildCount() < 2)) || ((tableLayout.getChildCount() != 0) && ((row != null) && (row.getChildCount() < 2)))) {
                    row.addView(checkBox);
                } else {
                    row = new TableRow(getContext());
                    row.addView(checkBox);
                    tableLayout.addView(row);
                }
            } while (cursor.moveToNext());
        }
        container_view.addView(cardView, layoutParams);*/

/*        // ------------------------

        cardView = (CardView) getActivity().getLayoutInflater().inflate(R.layout.card_view_template, null, false);
        cardView.setId(4);

        textView = (TextView) cardView.findViewById(R.id.title);
        textView.setId(5);
        textView.setText("Changed id to " + textView.getId());

        button = (ImageButton) cardView.findViewById(R.id.button);
        button.setId(6);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick(v);
            }
        });

        tableLayout = (TableLayout) cardView.findViewById(R.id.table_layout);
        tableLayout.setId(7);

        container_view.addView(cardView, layoutParams);

        // ------------------------

        cardView = (CardView) getActivity().getLayoutInflater().inflate(R.layout.card_view_template, null, false);
        cardView.setId(8);

        textView = (TextView) cardView.findViewById(R.id.title);
        textView.setId(9);
        textView.setText("Changed id to " + textView.getId());

        button = (ImageButton) cardView.findViewById(R.id.button);
        button.setId(10);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick(v);
            }
        });

        tableLayout = (TableLayout) cardView.findViewById(R.id.table_layout);
        tableLayout.setId(11);

        container_view.addView(cardView, layoutParams);*/

//        container_view.addView(cardView, cardParm);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        if (editMode)
            inflater.inflate(R.menu.menu_setting_fragment_context_options, menu);
        else
            inflater.inflate(R.menu.menu_setting_fragment_options, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit:
                editMode = true;
                getActivity().invalidateOptionsMenu();
                return true;
            case R.id.action_save:
                editMode = false;
                getActivity().invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onButtonClick(final View v) {
        SettingPickerFragment settingPickerFragment = new SettingPickerFragment();
        settingPickerFragment.setSettingPickerInterface(new SettingPickerFragment.SettingPickerInterface() {
            public void positiveClick(NutritionFact nutritionFact) {

                ViewGroup viewGroup = (ViewGroup) v.getParent().getParent();
                TableLayout tableLayout = (TableLayout) viewGroup.findViewById(((v.getId()) + 1));

                if (tableLayout != null) {
                    CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setText(nutritionFact.title);

                    TableRow row = null;
                    row = (TableRow) tableLayout.getChildAt((tableLayout.getChildCount() - 1));

                    if (((row != null) && (row.getChildCount() < 2)) || ((tableLayout.getChildCount() != 0) && ((row != null) && (row.getChildCount() < 2)))) {
                        row.addView(checkBox);
                    } else {
                        row = new TableRow(getContext());
                        row.addView(checkBox);
                        tableLayout.addView(row);
                    }
                }
            }
        });
        settingPickerFragment.show(getFragmentManager(), "settingPicker");
    }
}
