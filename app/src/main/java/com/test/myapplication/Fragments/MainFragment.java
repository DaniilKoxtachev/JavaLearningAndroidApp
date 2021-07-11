package com.test.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fxn.stash.Stash;
import com.test.myapplication.Adapters.MainAdapter;
import com.test.myapplication.Models.ItemModel;
import com.test.myapplication.R;
import com.test.myapplication.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private static final int CLEAR_FILTER = 1;
    private static final int FILTER = 2;
    private static final int CATEGORY = 3;
    private static final int FAVS = 4;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater);
        NavController controller = NavHostFragment.findNavController(this);

        List<ItemModel> models = new ArrayList<>();
        String[] res = getResources().getStringArray(R.array.item_section_perem);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(1, i));
        }
        res = getResources().getStringArray(R.array.item_section_oper);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(2, i));
        }
        res = getResources().getStringArray(R.array.item_section_cycle);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(3, i));
        }
        res = getResources().getStringArray(R.array.item_section_OOP);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(4, i));
        }
        res = getResources().getStringArray(R.array.item_section_core);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(5, i));
        }
        res = getResources().getStringArray(R.array.item_section_collection);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(6, i));
        }
        res = getResources().getStringArray(R.array.item_section_potok);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(7, i));
        }
        res = getResources().getStringArray(R.array.item_section_serialization);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(8, i));
        }
        res = getResources().getStringArray(R.array.item_section_multithreading);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(9, i));
        }
        res = getResources().getStringArray(R.array.item_section_JDBC);
        for (int i = 0; i < res.length; i++) {
            models.add(new ItemModel(10, i));
        }
        MainAdapter adapter = new MainAdapter(models, model -> {
            Bundle bundle = new Bundle();
            bundle.putInt("category", model.section);
            bundle.putInt("position", model.positionInList);
            controller.navigate(R.id.action_global_detailsFragment, bundle);
        });
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycler.setAdapter(adapter);

        if (getArguments() != null) {
            int status = getArguments().getInt("status");
            switch (status) {
                case FILTER:
                    if (getArguments().getString("filter") != null) {
                        adapter.filter(getArguments().getString("filter"),
                                getResources().getStringArray(R.array.item_section_perem),
                                getResources().getStringArray(R.array.item_section_oper),
                                getResources().getStringArray(R.array.item_section_cycle),
                                getResources().getStringArray(R.array.item_section_OOP),
                                getResources().getStringArray(R.array.item_section_core),
                                getResources().getStringArray(R.array.item_section_collection),
                                getResources().getStringArray(R.array.item_section_potok),
                                getResources().getStringArray(R.array.item_section_serialization),
                                getResources().getStringArray(R.array.item_section_multithreading),
                                getResources().getStringArray(R.array.item_section_JDBC));
                    }
                    break;

                case CLEAR_FILTER:
                    adapter.clearFilter();
                    break;

                case CATEGORY:
                    models = new ArrayList<>();
                    int category = getArguments().getInt("category");
                    String[] resData;
                    if (category == 1) {
                        resData = getResources().getStringArray(R.array.item_section_perem);
                    } else if(category == 2){
                        resData = getResources().getStringArray(R.array.item_section_oper);
                    }else if (category == 3){
                        resData = getResources().getStringArray(R.array.item_section_cycle);
                    }else if (category == 4) {
                        resData = getResources().getStringArray(R.array.item_section_OOP);
                    }else if(category == 5) {
                        resData = getResources().getStringArray(R.array.item_section_core);
                    }else if(category == 6) {
                        resData = getResources().getStringArray(R.array.item_section_collection);
                    }else if (category == 7) {
                        resData = getResources().getStringArray(R.array.item_section_potok);
                    } else if (category == 8){
                        resData = getResources().getStringArray(R.array.item_section_serialization);
                    } else if (category == 9) {
                        resData = getResources().getStringArray(R.array.item_section_multithreading);
                    } else {
                        resData = getResources().getStringArray(R.array.item_section_JDBC);
                    }

                    for (int i = 0; i < resData.length; i++) {
                        models.add(new ItemModel(category, i));
                    }
                    adapter.updateData(models);
                    break;

                case FAVS:
                    models = Stash.getArrayList("favs", ItemModel.class);
                    adapter.updateData(models);
                    break;
            }
        }


        return binding.getRoot();
    }
}
