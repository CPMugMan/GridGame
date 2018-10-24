package au.edu.curtin.gridgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StatusBarFragment extends Fragment
{
    private TextView cash;
    private TextView health;
    private TextView equipmentMass;
    private Button restart;
    private GameData data;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        data = GameData.get(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.status_bar_fragment,ui,false);

        cash = (TextView)view.findViewById(R.id.CashValue);
        health = (TextView)view.findViewById(R.id.HealthText);
        equipmentMass = (TextView)view.findViewById(R.id.EquipValue);
        restart = (Button)view.findViewById(R.id.RestartButton);
        restart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(),OpeningScreenActivity.class));
                data.resetGame();

            }
        });

        updateUI();

        return view;
    }

    public void updateUI()
    {
        cash.setText("C :" +(Integer.toString(data.getPlayer().getCash())));
        health.setText("H : " +(Double.toString(data.getPlayer().getHealth())) );
        equipmentMass.setText("M : " +(Double.toString(data.getPlayer().getEquipmentMass())));
    }




}
