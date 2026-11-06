package com.example.assignment1_new;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.assignment1_new.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private String name;

    public interface OnFragment2Listener {
        void onGoToFragment3(String name);
    }

    private OnFragment2Listener listener;

    public static Fragment2 newInstance(String name) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2Listener) {
            listener = (OnFragment2Listener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textHi.setText("Hi, " + name);

        binding.buttonContinue2.setOnClickListener(v -> {
            String email = binding.editEmail.getText().toString().trim();
            String university = binding.editUniversity.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(getContext(), "Enter email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.radioGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getContext(), "Select gender!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (university.isEmpty()) {
                Toast.makeText(getContext(), "Enter university!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (listener != null) {
                listener.onGoToFragment3(name);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}