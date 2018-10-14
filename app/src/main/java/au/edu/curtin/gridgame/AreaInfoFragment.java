package au.edu.curtin.gridgame;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class AreaInfoFragment extends Fragment
{
    private TextView areaType;
    private EditText descriptionText;
    private Switch starSwitch;
    private GameData data;

    private TextWatcher filterTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            data.getCurrArea().setDescription(descriptionText.getText().toString());

        }
    };

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        data = GameData.get();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.area_info_fragment, ui, false);
        areaType =(TextView)view.findViewById(R.id.areaType2);
        descriptionText =(EditText)view.findViewById(R.id.descriptionText2);
        descriptionText.addTextChangedListener(filterTextWatcher);
        starSwitch =(Switch)view.findViewById(R.id.starredSwitch2);
        starSwitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                data.getCurrArea().setStarred(starSwitch.isChecked());
            }
        });

        updateUI();

        return view;


    }

    public void updateUI()
    {
        if(data.getCurrArea().getTown() == false)
        {
            areaType.setText("Wilderness");
        }
        else
        {
            areaType.setText("Town");
        }

        descriptionText.setText(data.getCurrArea().getDescription());

        starSwitch.setChecked(data.getCurrArea().getStarred());


    }
}