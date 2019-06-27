package au.edu.curtin.gridgame.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import au.edu.curtin.gridgame.Controller.GameData;
import au.edu.curtin.gridgame.Model.Area;
import au.edu.curtin.gridgame.R;

public class AreaInfoFragment extends Fragment
{
    private TextView areaType;
    private EditText descriptionText;
    private Switch starSwitch;
    private GameData data;
    private Area inArea;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        data = GameData.get(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.area_info_fragment, ui, false);
        areaType =(TextView)view.findViewById(R.id.areaType2);
        descriptionText =(EditText)view.findViewById(R.id.descriptionText2);
        starSwitch =(Switch)view.findViewById(R.id.starredSwitch2);
        starSwitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                inArea.setStarred(starSwitch.isChecked());
                data.updateArea(inArea);
            }
        });
        descriptionText.addTextChangedListener(new TextWatcher() //Used to update the Description field after user enters in a description in edittext
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
                inArea.setDescription(descriptionText.getText().toString());
                data.updateArea(inArea);

            }
        });

        updateUI();

        return view;


    }

    //Updates AreaInfo Fragment inside the Navigation Activity
    public void updateUI()
    {
        inArea = data.getCurrArea();

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


    //Updates AreaInfo Fragment inside Overview Activity: Area passed in is the area that user taps on in the Overview Activity
    public void updateArea(Area inArea)
    {
        this.inArea = inArea;

        if(!inArea.getExplored())
        {
            areaType.setText("Unexplored");
        }
        else if(inArea.getTown() == false)
        {
            areaType.setText("Wilderness");
        }
        else
        {
            areaType.setText("Town");
        }

        descriptionText.setText(inArea.getDescription());

        starSwitch.setChecked(inArea.getStarred());

    }
}