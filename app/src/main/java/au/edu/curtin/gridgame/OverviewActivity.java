package au.edu.curtin.gridgame;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class OverviewActivity extends AppCompatActivity
{
    private MyAdapter adapter;
    private GameData data;
    private Button leave;
    private AreaInfoFragment fragA;
    private StatusBarFragment fragB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        data = GameData.get();
        leave = (Button)findViewById(R.id.button);
        RecyclerView rc = (RecyclerView)findViewById(R.id.map);
        rc.setLayoutManager(new GridLayoutManager(this,GameData.X,GridLayoutManager.HORIZONTAL,false));
        adapter= new MyAdapter();
        rc.setAdapter(adapter);

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

        leave.setOnClickListener(new View.OnClickListener()
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

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            LayoutInflater li = LayoutInflater.from(OverviewActivity.this);
            return new MyDataVHolder(li, viewGroup);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder myDataVHolder, int i)
        {
            int row = i%GameData.X;
            int col = i/GameData.X;
            myDataVHolder.bind(data.getArea(row,col));
        }

        @Override
        public int getItemCount()
        {
            return data.X*data.Y;
        }
    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private ImageView grass;
        private ImageView wilderness;
        private ImageView town;
        private ImageView person;
        private ImageView star;
        private Area area;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell,parent,false));
            int size = parent.getMeasuredHeight() / data.X + 2;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            grass = (ImageView)itemView.findViewById(R.id.grass);
            wilderness = (ImageView)itemView.findViewById(R.id.wilderness);
            town = (ImageView)itemView.findViewById(R.id.town);
            person = (ImageView)itemView.findViewById(R.id.person);
            star = (ImageView)itemView.findViewById(R.id.starred);

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

        public void bind(Area inArea)
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
            if(inArea.getTown() == false)
            {
                wilderness.setImageResource(inArea.getWilderness());
            }
            else
            {
                wilderness.setImageResource(0);
            }
            if(inArea.getX() == data.getPlayer().getColLocation() && inArea.getY() == data.getPlayer().getRowLocation())
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
            this.area = inArea;

        }

    }
}
