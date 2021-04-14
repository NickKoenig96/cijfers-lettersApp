package com.example.lettersnumbersapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LetterFragment extends Fragment {

    com.example.lettersnumbersapp.viewModel viewModel;

    public LetterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(com.example.lettersnumbersapp.viewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(com.example.lettersnumbersapp.viewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.letter_fragment, container, false);

        v.findViewById(R.id.btn_vowel).setOnClickListener(view -> viewModel.pickVowel());
        v.findViewById(R.id.btn_consonant).setOnClickListener(view -> viewModel.pickConsonant());

        return v;
    }
}
