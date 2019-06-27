package au.edu.curtin.gridgame.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import au.edu.curtin.gridgame.Controller.GameData;
import au.edu.curtin.gridgame.Model.Area;
import au.edu.curtin.gridgame.R;

public class NavigationActivity2 extends AppCompatActivity
{
    private GameData gameData;
    private AlertDialog.Builder builder;

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button option;

    private AreaInfoFragment fragA;
    private StatusBarFragment fragB;
    private MyAdapter adapter;

    private static final int REQUEST_CODE_WILDERNESS = 0;
    private static final int REQUEST_CODE_MARKET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation2);
        gameData = GameData.get(getApplicationContext());
        gameData.createItems();
        gameData.load();
        gameData.load2();
        gameData.getCurrArea().setExplored(true);
        buttonSetup();

        builder = new AlertDialog.Builder(NavigationActivity2.this);
        builder.setCancelable(true);

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
        RecyclerView rc = (RecyclerView)findViewById(R.id.map);
        rc.setLayoutManager(new GridLayoutManager(this,GameData.X,GridLayoutManager.HORIZONTAL,false));
        adapter =  new MyAdapter();
        rc.setAdapter(adapter);

        southButton.setOnClickListener(new View.OnClickListener()
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

        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!gameData.positionCheck(-1, 0))
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

        westButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!gameData.positionCheck(0, 1))
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

        eastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!gameData.positionCheck(0, -1))
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

        option.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent;
                if(gameData.getCurrArea().getTown() == false)
                {
                    intent = new Intent(NavigationActivity2.this, WildernessActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_WILDERNESS);
                }
                else
                {
                    intent = new Intent(NavigationActivity2.this,MarketActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_MARKET);
                }


            }
        });


    }

    public void buttonSetup()
    {
        northButton = (Button)findViewById(R.id.upArrow);
        southButton = (Button)findViewById(R.id.downArrow);
        eastButton = (Button) findViewById(R.id.leftArrow);
        westButton = (Button)findViewById(R.id.rightArrow);
        option = (Button)findViewById(R.id.button2);

    }

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            LayoutInflater li = LayoutInflater.from(NavigationActivity2.this);
            return new MyDataVHolder(li, viewGroup);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder myDataVHolder, int i)
        {
            int row = i%GameData.X;
            int col = i/GameData.X;
            myDataVHolder.bind(gameData.getArea(row,col));
        }

        @Override
        public int getItemCount()
        {
            return gameData.X*gameData.Y;
        }
    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private ImageView grass;
        private ImageView wilderness;
        private ImageView town;
        private ImageView person;
        private ImageView star;
        private ImageView explored;
        private Area area;

        MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell,parent,false));
            int size = parent.getMeasuredHeight() / gameData.X + 2;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            grass = (ImageView)itemView.findViewById(R.id.grass);
            wilderness = (ImageView)itemView.findViewById(R.id.wilderness);
            town = (ImageView)itemView.findViewById(R.id.town);
            person = (ImageView)itemView.findViewById(R.id.person);
            star = (ImageView)itemView.findViewById(R.id.starred);
            explored = (ImageView)itemView.findViewById(R.id.explored);

            grass.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    fragA.updateArea(area);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        void bind(Area inArea)
        {
            //grass.setImageResource(inArea.getGrass());
            if(!inArea.getExplored())
            {
                //grass.setImageResource(inArea.getGrass());
                explored.setImageResource(inArea.getExploredP());
                town.setImageResource(0);
                wilderness.setImageResource(0);
                person.setImageResource(0);
                grass.setImageResource(inArea.getGrass());

            }
            else
            {
                explored.setImageResource(0);
                everythingElse(inArea);
            }
            this.area = inArea;

        }

        void everythingElse(Area inArea)
        {
            grass.setImageResource(inArea.getGrass());
            if(inArea.getTown())
            {
                town.setImageResource(inArea.getTownP());

            }
            else
            {
                town.setImageResource(0);
            }
            if(!inArea.getTown())
            {
                wilderness.setImageResource(inArea.getWilderness());
            }
            else
            {
                wilderness.setImageResource(0);
            }
            if(inArea.getX() == gameData.getPlayer().getColLocation() && inArea.getY() == gameData.getPlayer().getRowLocation())
            {
                person.setImageResource(inArea.getPerson());
            }
            else
            {
                person.setImageResource(0);
            }
            if(inArea.getStarred())
            {
                star.setImageResource(inArea.getStar());
            }
            else
            {
                star.setImageResource(0);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnData)
    {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_WILDERNESS || requestCode == REQUEST_CODE_MARKET)
        {
            updateUI();
            fragB.updateUI();
            fragA.updateUI();
        }

    }

    public void updateUI()
    {
        fragA.updateUI();
        fragB.updateUI();
        adapter.notifyDataSetChanged();

    }

    private void checkState()
    {
        if(gameData.getPlayer().getWinCount() == 3)
        {
            showMessage("Congrats You Win!");
            startActivity(new Intent(NavigationActivity2.this,OpeningScreenActivity.class));
            gameData.resetGame();

        }
        if(gameData.getPlayer().getHealth() <= 0)
        {
            showMessage("You Lose");
            startActivity(new Intent(NavigationActivity2.this,OpeningScreenActivity.class));
            gameData.resetGame();

        }
    }

    private void showMessage(String message)
    {
        builder.setMessage(message);
        builder.show();
    }

    public void cantMove()
    {
        builder.setMessage("Cannot Move there");
        builder.show();
    }
}
