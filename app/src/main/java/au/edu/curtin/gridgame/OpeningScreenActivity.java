package au.edu.curtin.gridgame;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class OpeningScreenActivity extends AppCompatActivity
{
    private ImageButton startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        startButton = (ImageButton) findViewById(R.id.playbutton);
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
