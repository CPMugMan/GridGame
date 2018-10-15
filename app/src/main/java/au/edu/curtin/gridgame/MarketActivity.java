package au.edu.curtin.gridgame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MarketActivity extends AppCompatActivity
{
    private GameData gameData;
    private StatusBarFragment fragA;
    private AreaItemListFragment fragB;
    private PlayerItemListFragment fragC;
    private Button leaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        gameData = GameData.get();

        FragmentManager fm = getSupportFragmentManager();
        fragA = (StatusBarFragment)fm.findFragmentById(R.id.sb_container);
        fragB = (AreaItemListFragment)fm.findFragmentById(R.id.marketItemList);
        fragC = (PlayerItemListFragment)fm.findFragmentById(R.id.playerItemList);
        if(fragA == null)
        {
            fragA = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.sb_container,fragA).commit();
        }
        if(fragB == null)
        {
            fragB = new AreaItemListFragment();
            fm.beginTransaction()
                    .add(R.id.marketItemList,fragB).commit();
        }
        if(fragC == null)
        {
            fragC = new PlayerItemListFragment();
            fm.beginTransaction()
                    .add(R.id.playerItemList,fragC).commit();
        }

        viewSetup();

        leaveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MarketActivity.this,NavigationActivity.class);
                startActivity(intent);
            }
        });




    }

    public void viewSetup()
    {
        leaveButton = (Button)findViewById(R.id.leaveButton);
    }

}
