package com.example.task61;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.task61.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        setupProfile();
        return binding.getRoot();
    }

    private void setupProfile() {
        // 设置示例用户信息
        binding.usernameText.setText("John Doe");
        binding.emailText.setText("john.doe@example.com");
        
        // 设置编辑按钮点击事件
        binding.editProfileButton.setOnClickListener(v -> {
            // TODO: 实现编辑个人资料功能
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
