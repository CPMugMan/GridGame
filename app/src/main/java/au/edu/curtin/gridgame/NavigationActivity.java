package au.edu.curtin.gridgame;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NavigationActivity extends AppCompatActivity
{
    private GameData gameData;
    private AlertDialog.Builder builder;

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button optionButton;
    private Button overviewButton;
    private TextView xCoord;
    private TextView yCoord;
    private AreaInfoFragment fragA;
    private StatusBarFragment fragB;
    //private static final int REQUEST_CODE_WILDERNESS = 0;
    //private static final int REQUEST_CODE_MARKET = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        gameData = GameData.get();

        FragmentManager fm = getSupportFragmentManager();
        fragA = (AreaInfoFragment)fm.findFragmentById(R.id.af_container);
        fragB = (StatusBarFragment)fm.findFragmentById(R.id.sb_container);
        if(fragA == null)
        {
            fragA = new AreaInfoFragment();
            fm.beginTransaction()
                    .add(R.id.af_container, fragA).commit();
        }
        if(fragB == null)
        {
            fragB = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.sb_container,fragB).commit();
        }

        builder = new AlertDialog.Builder(NavigationActivity.this);
        builder.setCancelable(true);

        initialButton();

        //testMethod();

        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(gameData.positionCheck(1,0) == false)
                {
                    cantMove();
                }
                else
                {
                    gameData.movePlayer(1,0);
                    updateUI();

                }
            }
        });

        southButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(gameData.positionCheck(-1,0) == false)
                {
                    cantMove();
                }
                else
                {
                    gameData.movePlayer(-1,0);
                    updateUI();


                }
            }
        });

        eastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(gameData.positionCheck(0,1) == false)
                {
                    cantMove();
                }
                else
                {
                    gameData.movePlayer(0,1);
                    updateUI();

                }

            }
        });

        westButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(gameData.positionCheck(0,-1) == false)
                {
                    cantMove();
                }
                else
                {
                    gameData.movePlayer(0,-1);
                    updateUI();
                }
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent;
                if(gameData.getCurrArea().getTown() == false)
                {
                    intent = new Intent(NavigationActivity.this,WildernessActivity.class);
                    startActivity(intent);
                }
                else
                {
                    intent = new Intent(NavigationActivity.this,MarketActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    public void initialButton()
    {
        northButton = (Button)findViewById(R.id.northButton);
        southButton = (Button)findViewById(R.id.southButton);
        eastButton = (Button)findViewById(R.id.eastButton);
        westButton = (Button)findViewById(R.id.westButton);
        optionButton = (Button)findViewById(R.id.optionButton);
        overviewButton = (Button)findViewById(R.id.overviewButton);
        xCoord = (TextView)findViewById(R.id.xCoord);
        yCoord = (TextView)findViewById(R.id.yCoord);


    }

    public void updateUI()
    {
        xCoord.setText(Integer.toString(gameData.getPlayer().getColLocation()));
        yCoord.setText(Integer.toString(gameData.getPlayer().getRowLocation()));
        fragA.updateUI();
        fragB.updateUI();

    }

    public void cantMove()
    {
        builder.setMessage("Cannot Move there");
        builder.show();
    }

    public void testMethod()
    {
        Equipment equipment1 = new Equipment("(づ￣ ³￣)づ",10,10.0);
        Equipment equipment2 = new Equipment("iPhone",15,4.0);
        Equipment equipment3 = new Equipment("Dell XPS 15",40,2.0);
        Equipment equipment4 = new Equipment("Macbook Pro",70,1.0);
        Equipment equipment5 = new Equipment("Chromebook",30,5.0);
        gameData.getPlayer().addEquipment(equipment1);
        gameData.getPlayer().addEquipment(equipment2);
        gameData.getPlayer().addEquipment(equipment3);
        gameData.getPlayer().addEquipment(equipment4);
        gameData.getPlayer().addEquipment(equipment5);

    }





}
