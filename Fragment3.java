package com.example.assignment1_new;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.assignment1_new.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private Fragment3Binding binding;
    private String name;

    public interface OnFragment3Listener {
        void onAgreementChanged(boolean isChecked);
    }

    private OnFragment3Listener listener;

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment3Listener) {
            listener = (OnFragment3Listener) context;
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
        binding = Fragment3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtName.setText("Welcome, " + name + "!");

        // الزر بيبدأ معطل ونصه Continue زي ما طلب الواجب
        binding.buttonContinue.setEnabled(false);
        binding.buttonContinue.setText("Continue");

        binding.checkAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // بنبعت الحالة للـ Activity عن طريق الـ Interface
            if (listener != null) {
                listener.onAgreementChanged(isChecked);
            }
        });

        binding.buttonContinue.setOnClickListener(v -> {
            // الواجب بيحكي: No further action is required
        });
    }

    // هاي الدالة بتناديها الـ Activity عشان تغير الزر
    public void updateButtonState(boolean isChecked) {
        if (binding == null) return;
        binding.buttonContinue.setEnabled(isChecked);
        if (isChecked) {
            binding.buttonContinue.setText("Finish");
        } else {
            binding.buttonContinue.setText("Continue");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}