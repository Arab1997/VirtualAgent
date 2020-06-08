package myway.virtualagent.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import myway.virtualagent.LoginActivity;
import myway.virtualagent.R;
import myway.virtualagent.adapters.Adapter;
import myway.virtualagent.models.products.Results;
import myway.virtualagent.utils.SharedPrefManager;


public class ProfileFragment extends Fragment {

    private List<Results> results;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private static String token;
    private View progressBar;
    ImageView navmenu;


    ImageView close, image_profile;
    TextView debt, feedback, wallet;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        image_profile = view.findViewById(R.id.image_profile);
        feedback = view.findViewById(R.id.feedback);
        wallet = view.findViewById(R.id.wallet);
        debt = view.findViewById(R.id.debt);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(), CommentsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/

            }
        });

        debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(), CommentsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/

            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(), CommentsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/

            }
        });

        return view;


    }

    private void logout() {
        SharedPrefManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.save:
        logout();
        break;
    }
    }*/

}

