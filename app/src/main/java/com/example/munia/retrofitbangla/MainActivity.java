package com.example.munia.retrofitbangla;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.munia.retrofitbangla.network.ApiInterface;
import com.example.munia.retrofitbangla.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView ipAddressTextView;
    private TextView cityTextView;
    private TextView countryTextView;
    private ProgressBar progressBar;
    private Button showIpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddressTextView = findViewById(R.id.ip_address_textView);
        cityTextView = findViewById(R.id.city_textView);
        countryTextView = findViewById(R.id.country_textView);
        progressBar = findViewById(R.id.progressBar);
        showIpButton = findViewById(R.id.show_ip_button);

        showIpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello munia, I love you!", Toast.LENGTH_LONG).show();
            }
        });

        showMyIp();
    }
    public void showMyIp() {

        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Call<ServerResponse> call = apiInterface.getMyIp();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                progressBar.setVisibility(View.GONE);

                ServerResponse serverResponse = response.body();

                if (response.code()==200 && serverResponse!=null) {

                    ipAddressTextView.setText(serverResponse.getIp());
                    cityTextView.setText(serverResponse.getCity());
                    countryTextView.setText(serverResponse.getCountry());
                } else {

                    ipAddressTextView.setText(response.message());
                    cityTextView.setText("");
                    countryTextView.setText("");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                ipAddressTextView.setText(t.getMessage());
                cityTextView.setText("");
                countryTextView.setText("");
            }
        });
    }
}
