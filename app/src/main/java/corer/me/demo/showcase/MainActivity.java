package corer.me.demo.showcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import corer.me.showcase.ShowCaseView;
import corer.me.showcase.animation.AnimationController;
import corer.me.showcase.layout.LayoutController;
import corer.me.showcase.shape.CircleShape;
import corer.me.showcase.shape.RectangleShape;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View guideViewOne = LayoutInflater.from(this).inflate(R.layout.showcase_content_one, null, false);
        final View targetOne = findViewById(R.id.target_one);
        final ShowCaseView showCaseViewOne = new ShowCaseView.Builder(MainActivity.this)
                .setLayoutController(new LayoutController(guideViewOne))
                .setShape(new CircleShape(this))
                .build(targetOne);

        guideViewOne.findViewById(R.id.show_case_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCaseViewOne.hide();
            }
        });

        targetOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCaseViewOne.show(MainActivity.this);
            }
        });


        final View guideViewTwo = LayoutInflater.from(this).inflate(R.layout.showcase_content_one, null, false);
        final View targetTwo = findViewById(R.id.target_two);
        final ShowCaseView showCaseViewTwo = new ShowCaseView.Builder(MainActivity.this)
                .setLayoutController(new LayoutController(guideViewTwo))
                .setShape(new RectangleShape())
                .build(targetTwo);

        guideViewTwo.findViewById(R.id.show_case_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCaseViewTwo.hide();
            }
        });
        targetTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCaseViewTwo.show(MainActivity.this);
            }
        });
    }
}
