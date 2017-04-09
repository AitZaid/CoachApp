package com.example.cyk.coachingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private EditText name;

    private Button mButtonChrono;
    private Button mButtonScore1;
    private Button mButtonScore2;

    private TextView mTextScore1;
    private TextView mTextScore2;

    private boolean isRunning = false;

    private int score1 = 0;
    private int score2 = 0;

    private Chronometer mChrono;

    private long timeWhenStopped = 0;

    public MatchFragment() {
        // Required empty public constructor
    }


    public static MatchFragment newInstance(String param1, String param2) {
        MatchFragment fragment = new MatchFragment();
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
        return inflater.inflate(R.layout.fragment_match, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChrono = (Chronometer) view.findViewById(R.id.chrono);
        name = (EditText) view.findViewById(R.id.editText);
        mButtonChrono = (Button) view.findViewById(R.id.buttonTimer);
        mButtonChrono.setText("Start");
        mButtonChrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    mChrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    mChrono.start();
                    isRunning = true;
                    mButtonChrono.setText("Pause");
                }
                else {
                    timeWhenStopped = mChrono.getBase() - SystemClock.elapsedRealtime();
                    mChrono.stop();
                    isRunning = false;
                    mButtonChrono.setText("Start");
                }

            }
        });

        mTextScore1 = (TextView) view.findViewById(R.id.textScore1);
        mTextScore2 = (TextView) view.findViewById(R.id.textScore2);

        mTextScore1.setText(String.valueOf(score1));
        mTextScore2.setText(String.valueOf(score2));

        mButtonScore1 = (Button) view.findViewById(R.id.buttonScore1);
        mButtonScore2 = (Button) view.findViewById(R.id.buttonScore2);

        mButtonScore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score1 +=1;
                mTextScore1.setText(String.valueOf(score1));
            }
        });

        mButtonScore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score2 +=1;
                mTextScore2.setText(String.valueOf(score2));
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

    public Object[] returnValues(){
        Object[] list = new Object[6];
        list[0] = score1;
        list[1] = score2;
        list[2] = name.getText();
        list[3] = mChrono.getText();
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
