package au.edu.curtin.gridgame.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import au.edu.curtin.gridgame.R;

public class OpeningScreenActivity extends AppCompatActivity
{
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        startButton = (Button) findViewById(R.id.playbutton);
        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //startActivity(new Intent(OpeningScreenActivity.this,NavigationActivity.class));
                startActivity(new Intent(OpeningScreenActivity.this, NavigationActivity2.class));
            }
        });


    }
}
