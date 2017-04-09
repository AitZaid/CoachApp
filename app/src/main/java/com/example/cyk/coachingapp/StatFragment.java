package com.example.cyk.coachingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Button buttonShots;
    private Button buttonShotsOn;
    private Button buttonFouls;
    private Button buttonOffsides;
    private Button buttonYellows;
    private Button buttonReds;

    private TextView textFouls;
    private TextView textShotsOn;
    private TextView textShots;
    private TextView textOffsides;
    private TextView textYellows;
    private TextView textReds;

    int shots = 0;
    int shotsOn = 0;
    int fouls = 0;
    int offsides = 0;
    int yellows = 0;
    int reds = 0;

    public StatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatFragment newInstance(String param1, String param2) {
        StatFragment fragment = new StatFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonFouls = (Button) view.findViewById(R.id.buttonFouls);
        buttonShots = (Button) view.findViewById(R.id.buttonShots);
        buttonShotsOn = (Button) view.findViewById(R.id.buttonShotsOn);
        buttonOffsides = (Button) view.findViewById(R.id.buttonOffsides);
        buttonYellows = (Button) view.findViewById(R.id.buttonYellows);
        buttonReds = (Button) view.findViewById(R.id.buttonReds);

        textFouls = (TextView) view.findViewById(R.id.textFouls);
        textShots = (TextView) view.findViewById(R.id.textShots);
        textShotsOn = (TextView) view.findViewById(R.id.textShotsOn);
        textOffsides = (TextView) view.findViewById(R.id.textOffsides);
        textYellows = (TextView) view.findViewById(R.id.textYellow);
        textReds = (TextView) view.findViewById(R.id.textRed);

        buttonFouls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fouls +=1;
                textFouls.setText(String.valueOf(fouls));
            }
        });

        buttonShots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shots +=1;
                textShots.setText(String.valueOf(shots));
            }
        });

        buttonShotsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotsOn +=1;
                textShotsOn.setText(String.valueOf(shotsOn));
            }
        });

        buttonOffsides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offsides +=1;
                textOffsides.setText(String.valueOf(offsides));
            }
        });

        buttonYellows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yellows +=1;
                textYellows.setText(String.valueOf(yellows));
            }
        });

        buttonReds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reds +=1;
                textReds.setText(String.valueOf(reds));
            }
        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public int[] returnValues(){
        int[] list = new int[6];
        list[0] = shots;
        list[1] = shotsOn;
        list[2] = fouls;
        list[3] = offsides;
        list[4] = yellows;
        list[5] = reds;
        return list;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
