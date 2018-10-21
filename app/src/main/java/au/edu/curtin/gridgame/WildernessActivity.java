package au.edu.curtin.gridgame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class WildernessActivity extends AppCompatActivity
{
    private GameData gameData;
    private StatusBarFragment fragA;
    private Button leaveButton;
    private AlertDialog.Builder builder;
    private MarketAdapter adapter;
    private PlayerAdapter adapter1;
    private static final int REQUEST_CODE_SMELLY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilderness);
        gameData = GameData.get();
        leaveButton = (Button)findViewById(R.id.leaveButton);

        builder = new AlertDialog.Builder(WildernessActivity.this);
        builder.setCancelable(true);

        FragmentManager fm = getSupportFragmentManager();
        fragA = (StatusBarFragment)fm.findFragmentById(R.id.sb_container);
        if(fragA == null)
        {
            fragA = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.sb_container,fragA).commit();
        }

        RecyclerView market = (RecyclerView)findViewById(R.id.marketList);
        market.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MarketAdapter();
        market.setAdapter(adapter);

        RecyclerView playerList = (RecyclerView)findViewById(R.id.playerList);
        playerList.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new PlayerAdapter();
        playerList.setAdapter(adapter1);


        leaveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });




    }


    private class MarketAdapter extends RecyclerView.Adapter<MarketVHolder>
    {
        private List<Item> mItems;

        public MarketAdapter()
        {
            mItems = gameData.getCurrArea().getList();
        }

        @Override
        public int getItemCount()
        {
            return mItems.size();
        }

        @Override
        public MarketVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(WildernessActivity.this);
            return new MarketVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(MarketVHolder vh, int index)
        {
            vh.bind(mItems.get(index));

        }
    }

    private class MarketVHolder extends RecyclerView.ViewHolder
    {
        private TextView itemName;
        private TextView itemValue;
        private TextView itemMass;
        private Button buy;
        private Item currItem;


        public MarketVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_mydata,parent,false));
            itemName = (TextView)itemView.findViewById(R.id.itemName);
            itemValue = (TextView)itemView.findViewById(R.id.value);
            itemMass = (TextView)itemView.findViewById(R.id.mass);
            buy = (Button)itemView.findViewById(R.id.buy);
            buy.setText("Pickup");
            buy.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                      if(currItem.getType().equals("Health"))
                        {
                            gameData.getPlayer().setHealth(gameData.getPlayer().getHealth() + currItem.getMassorHealth());
                        }
                        else
                        {
                            gameData.getPlayer().addEquipment((Equipment)currItem);
                            gameData.getPlayer().setEquipmentMass(gameData.getPlayer().getEquipmentMass() + currItem.getMassorHealth());
                        }
                        gameData.getCurrArea().getList().remove(currItem);
                        fragA.updateUI();
                        adapter.notifyDataSetChanged();
                        adapter1.notifyDataSetChanged();


                }
            });
        }

        public void bind(Item inItem)
        {
            itemName.setText(inItem.getDescription());
            itemValue.setText(Integer.toString(inItem.getValue()));
            itemMass.setText(Double.toString(inItem.getMassorHealth()));
            currItem = inItem;

        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerVHolder>
    {
        private List<Item> pdata;

        public PlayerAdapter()
        {
            pdata = gameData.getPlayer().getList();
        }

        @Override
        public int getItemCount()
        {
            return pdata.size();
        }

        @Override
        public PlayerVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(WildernessActivity.this);
            return new PlayerVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(PlayerVHolder vh, int index)
        {
            vh.bind(pdata.get(index));
        }
    }

    private class PlayerVHolder extends RecyclerView.ViewHolder
    {
        private TextView itemName2;
        private TextView itemValue2;
        private TextView itemMass2;
        private Button buy2;
        private Item currItem2;
        private Button use;

        public PlayerVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_mydata2,parent,false));
            itemName2 = (TextView)itemView.findViewById(R.id.itemName);
            itemValue2 = (TextView)itemView.findViewById(R.id.value);
            itemMass2 = (TextView)itemView.findViewById(R.id.mass);
            use  = (Button)itemView.findViewById(R.id.use);
            buy2 = (Button)itemView.findViewById(R.id.buy);
            buy2.setText("Drop");

            buy2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    gameData.getPlayer().setEquipmentMass(gameData.getPlayer().getEquipmentMass() - currItem2.getMassorHealth());
                    gameData.getCurrArea().getList().add(currItem2);
                    gameData.getPlayer().getList().remove(currItem2);
                    fragA.updateUI();
                    adapter.notifyDataSetChanged();
                    adapter1.notifyDataSetChanged();

                }
            });

            use.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(currItem2.getDescription().equals("Portable Smell-O-Scope"))
                    {
                        Intent intent = new Intent(WildernessActivity.this,SmellActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_SMELLY);
                        showMessage("Smell-O-Scope Used");
                    }
                    else if(currItem2.getDescription().equals("Improbability Drive"))
                    {
                        gameData.improbDrive();
                        showMessage("Improbability Drive Used");
                        Intent intent = new Intent();
                        setResult(RESULT_OK,intent);
                        finish();


                    }
                    else if(currItem2.getDescription().equals("Ben Kenobi"))
                    {
                        showMessage("Ben Kenobi Used");
                        gameData.benKen();
                        fragA.updateUI();
                        adapter.notifyDataSetChanged();
                        adapter1.notifyDataSetChanged();

                    }
                    else
                    {
                        showMessage("Cannot use that Item");
                    }


                }
            });

        }

        public void bind(Item inItem)
        {
            itemName2.setText(inItem.getDescription());
            itemValue2.setText(Integer.toString(inItem.getValue()));
            itemMass2.setText(Double.toString(inItem.getMassorHealth()));
            currItem2 = inItem;

        }

    }

    private void showMessage(String message)
    {
        builder.setMessage(message);
        builder.show();
    }



}

