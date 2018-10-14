package au.edu.curtin.gridgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class AreaInfoFragment extends Fragment
{
    private TextView areaType;
    private EditText descriptionText;
    private Switch starSwitch;
    private GameData data;

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
        starSwitch =(Switch)view.findViewById(R.id.starredSwitch2);

        updateUI();

        return view;


    }

    public void updateUI()
    {
        if(data.getArea(data.getColPosition(),data.getRowPosition()).getTown() == false)
        {
            areaType.setText("Wilderness");
        }
        else
        {
            areaType.setText("Town");
        }

    }
}