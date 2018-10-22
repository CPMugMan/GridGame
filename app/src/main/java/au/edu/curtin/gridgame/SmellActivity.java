package au.edu.curtin.gridgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SmellActivity extends AppCompatActivity
{
    private Button leaveButton;
    private GameData gameData;
    private List<String> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smell);
        gameData = GameData.get();
        displayList = new ArrayList<String>();
        smellOScope();
        leaveButton = (Button)findViewById(R.id.leaveButton);
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

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter();
        rv.setAdapter(adapter);



    }

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {
        private List<String> data;
        public MyAdapter()
        {
            data = displayList;
        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }

        @Override
        public MyDataVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(SmellActivity.this);
            return new MyDataVHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder vh, int index)
        {
            vh.bind(data.get(index));
        }

    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private TextView desciptionText;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.smell_list,parent,false));
            desciptionText = (TextView)itemView.findViewById(R.id.theListText);
        }

        public void bind(String data)
        {
            desciptionText.setText(data);
        }
    }


    public void smellOScope()
    {
        int minCol;
        int minRow;
        int maxCol;
        int maxRow;
        String name;

        minCol = Math.max(gameData.getPlayer().getColLocation()-2,0);
        minRow = Math.max(gameData.getPlayer().getRowLocation()-2,0);
        maxCol = Math.min(gameData.getPlayer().getColLocation()+2,49);
        maxRow = Math.min(gameData.getPlayer().getRowLocation()+2,49);

        for(int i = minCol; i<= maxCol; i++)
        {
            for(int j= minRow; j<=maxRow;j++)
            {
                Area currA = gameData.getArea(i,j);
                String eastWest;
                String northSouth;
                String x;
                String y;
                if(i < gameData.getPlayer().getColLocation())
                {
                    northSouth = "South";
                }
                else
                {
                    northSouth = "North";
                }
                if(j < gameData.getPlayer().getRowLocation())
                {
                    eastWest = "West";
                }
                else
                {
                    eastWest = "East";
                }
                x = Integer.toString(Math.abs(i-gameData.getPlayer().getColLocation()));
                y = Integer.toString(Math.abs(j-gameData.getPlayer().getRowLocation()));
                for(int k =0; k < currA.getList().size();k++)
                {
                    Item item = currA.getList().get(k);
                    name = currA.getList().get(k).getDescription();
                    displayList.add(name + "            " + x + " " + northSouth + "              " + y + " " + eastWest);

                }


            }
        }
    }


}
