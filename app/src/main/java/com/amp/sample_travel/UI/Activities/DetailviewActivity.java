package com.amp.sample_travel.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.amp.sample_travel.Models.LocationData;
import com.amp.sample_travel.R;
import com.bumptech.glide.Glide;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailviewActivity extends AppCompatActivity {

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.description_tv)
    TextView description_tv;

    @BindView(R.id.time_tv)
    TextView time_tv;

    @BindView(R.id.rate_tv)
    TextView rate_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);
        ButterKnife.bind(this);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LocationData locationData = (LocationData) getIntent().getSerializableExtra("data");
        time_tv.setText(locationData.getDate());
        setTitle(locationData.getPlace());
        description_tv.setText(locationData.getDescription());
        Glide.with(this).load(locationData.getUrl()).into(backdrop);
        rate_tv.setText(MessageFormat.format("{0}{1}", getString(R.string.Rs), locationData.getRate()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
