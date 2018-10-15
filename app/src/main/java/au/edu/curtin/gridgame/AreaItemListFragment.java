package au.edu.curtin.gridgame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class AreaItemListFragment extends Fragment
{
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
        View view = inflater.inflate(R.layout.area_item_list_fragment,ui,false);

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.theList);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyAdapter adapter = new MyAdapter();

        rv.setAdapter(adapter);



        return view;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {
        private List<Item> aData;

        public MyAdapter()
        {
            aData = data.getCurrArea().getList();
        }

        @Override
        public int getItemCount()
        {
            return aData.size();
        }

        @Override
        public MyDataVHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new MyDataVHolder(li,parent);
        }

        @Override
        public void onBindViewHolder(MyDataVHolder vh, int index)
        {
            vh.bind(aData.get(index));
        }


    }

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        private TextView itemName;
        private TextView value;
        private TextView mass;
        private Button buy;

        public MyDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_mydata,parent,false));
            itemName = (TextView)itemView.findViewById(R.id.itemName);
            value = (TextView)itemView.findViewById(R.id.value);
            mass = (TextView)itemView.findViewById(R.id.mass);
            buy = (Button)itemView.findViewById(R.id.buy);
        }

        public void bind(Item inItem)
        {
            itemName.setText(inItem.getDescription());
            value.setText("          Value :   " +inItem.getValue());

            mass.setText(inItem.getType() + " :  "  + Double.toString(inItem.getMassorHealth()) + "     ");
        }
    }
}
