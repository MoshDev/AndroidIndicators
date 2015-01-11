package com.moshx.androidindicators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ersan on 1/2/15.
 */
public class TestFragment extends Fragment {

    int pos;

    public static TestFragment newInstance(int pos) {
        Bundle bundle = new Bundle(1);
        bundle.putInt("pos", pos);
        TestFragment instance = new TestFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        textView.setText("Position: " + pos);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
}
