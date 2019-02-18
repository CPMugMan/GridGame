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
    private static final int REQUEST_CODE_WILDERNESS = 0;
    private static final int REQUEST_CODE_MARKET = 1;
    private static final int REQUEST_CODE_OVERVIEW = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        gameData = GameData.get(getApplicationContext());
        gameData.createItems();
        gameData.load();
        gameData.load2();
        gameData.getCurrArea().setExplored(true);

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
        xCoord.setText(Integer.toString(gameData.getPlayer().getColLocation()));
        yCoord.setText(Integer.toString(gameData.getPlayer().getRowLocation()));

        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!gameData.positionCheck(1, 0))
                {
                    cantMove();
                }
                else
                {
                    gameData.movePlayer(1,0);
                    gameData.getCurrArea().setExplored(true);
                    gameData.updatePlayer();
                    updateUI();
                    checkState();

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
                    gameData.getCurrArea().setExplored(true);
                    gameData.updatePlayer();
                    updateUI();
                    checkState();


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
                    gameData.getCurrArea().setExplored(true);
                    gameData.updatePlayer();
                    updateUI();
                    checkState();

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
                    gameData.getCurrArea().setExplored(true);
                    gameData.updatePlayer();
                    updateUI();
                    checkState();
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
                    startActivityForResult(intent,REQUEST_CODE_WILDERNESS);
                }
                else
                {
                    intent = new Intent(NavigationActivity.this,MarketActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_MARKET);
                }


            }
        });

        overviewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NavigationActivity.this,OverviewActivity.class);
                startActivityForResult(intent,REQUEST_CODE_OVERVIEW);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnData)
    {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_WILDERNESS || requestCode == REQUEST_CODE_MARKET || requestCode == REQUEST_CODE_OVERVIEW)
        {
            updateUI();
            fragB.updateUI();
            fragA.updateUI();
        }

    }

    private void checkState()
    {
        if(gameData.getPlayer().getWinCount() == 3)
        {
            showMessage("Congrats You Win!");
            startActivity(new Intent(NavigationActivity.this,OpeningScreenActivity.class));
            gameData.resetGame();

        }
        if(gameData.getPlayer().getHealth() <= 0)
        {
            showMessage("You Lose");
            startActivity(new Intent(NavigationActivity.this,OpeningScreenActivity.class));
            gameData.resetGame();

        }
    }

    private void showMessage(String message)
    {
        builder.setMessage(message);
        builder.show();
    }






}
