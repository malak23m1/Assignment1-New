package com.example.assignment1_new;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.assignment1_new.databinding.Fragment1Binding;

public class Fragment1 extends Fragment {
    private Fragment1Binding binding;
    public interface OnFragment1Listener { void onGoToFragment2(String name); }
    private OnFragment1Listener mListener;
    public Fragment1() {}
    public static Fragment1 newInstance() { return new Fragment1(); }
    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment1Listener) mListener = (OnFragment1Listener) context;
        else throw new ClassCastException(context.toString() + " must implement OnFragment1Listener");
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        binding.buttonContinue.setOnClickListener(v -> {
            String name = binding.editName.getText().toString().trim();
            if (name.isEmpty()) { Toast.makeText(getContext(), "Enter name!", Toast.LENGTH_SHORT).show(); return; }
            if (mListener != null) mListener.onGoToFragment2(name);
        });
        return binding.getRoot();
    }
    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}