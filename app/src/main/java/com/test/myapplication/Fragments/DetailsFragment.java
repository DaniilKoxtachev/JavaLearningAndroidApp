package com.test.myapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fxn.stash.Stash;
import com.test.myapplication.MainActivity;
import com.test.myapplication.Models.ItemModel;
import com.test.myapplication.R;
import com.test.myapplication.databinding.FragmentDetailsBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private List<ItemModel> favItems;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailsBinding binding = FragmentDetailsBinding.inflate(inflater);
        if (getArguments() != null) {
            String[] resTitles;
            String[] resContent;

            binding.buttCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            favItems = Stash.getArrayList("favs", ItemModel.class);
            ItemModel favModel = new ItemModel(getArguments().getInt("category"),
                    getArguments().getInt("position"));

            if (favItems.contains(favModel)) {
                binding.favImg.setImageResource(R.drawable.star);
            } else {
                binding.favImg.setImageResource(R.drawable.star_empty);
            }

            binding.favImg.setOnClickListener(view -> {

                if (favItems.contains(favModel)) {
                    favItems.remove(favModel);
                    Stash.put("favs", favItems);
                    binding.favImg.setImageResource(R.drawable.star_empty);
                } else {
                    favItems.add(favModel);
                    Stash.put("favs", favItems);
                    binding.favImg.setImageResource(R.drawable.star);
                }
            });

            if (getArguments().getInt("category") == 1) {
                resTitles = getResources().getStringArray(R.array.item_section_perem);
                resContent = getResources().getStringArray(R.array.item_subsection_perem);
                 if (getArguments().getInt("position") == 1){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 2){
                    binding.buttCode.setVisibility(View.GONE);
                }
            }
            else if(getArguments().getInt("category") == 2) {
                resTitles = getResources().getStringArray(R.array.item_section_oper);
                resContent = getResources().getStringArray(R.array.item_subsection_oper);
            } else if (getArguments().getInt("category") == 3){
                resTitles = getResources().getStringArray(R.array.item_section_cycle);
                resContent = getResources().getStringArray(R.array.item_subsection_cycle);
                 if (getArguments().getInt("position") == 0){
                    binding.buttCode.setVisibility(View.GONE);
                }

            } else if(getArguments().getInt("category") == 4) {
                resTitles = getResources().getStringArray(R.array.item_section_OOP);
                resContent = getResources().getStringArray(R.array.item_subsection_OOP);
                 if (getArguments().getInt("position") == 0){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 1){
                     binding.buttCode.setVisibility(View.GONE);
                 }else if (getArguments().getInt("position") == 6){
                     binding.buttCode.setVisibility(View.GONE);
                 }else if (getArguments().getInt("position") == 7){
                     binding.buttCode.setVisibility(View.GONE);
                 }else if (getArguments().getInt("position") == 8){
                     binding.buttCode.setVisibility(View.GONE);
                 }
            } else if(getArguments().getInt("category") == 5) {
                resTitles = getResources().getStringArray(R.array.item_section_core);
                resContent = getResources().getStringArray(R.array.item_subsection_core);
                if (getArguments().getInt("position") == 0){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 6){
                    binding.buttCode.setVisibility(View.GONE);
                }
            } else if (getArguments().getInt("category") == 6){
                resTitles = getResources().getStringArray(R.array.item_section_collection);
                resContent = getResources().getStringArray(R.array.item_subsection_collection);
                if (getArguments().getInt("position") == 0){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 2){
                    binding.buttCode.setVisibility(View.GONE);
                }
            }else if (getArguments().getInt("category") == 7){
                resTitles = getResources().getStringArray(R.array.item_section_potok);
                resContent = getResources().getStringArray(R.array.item_subsection_potok);
                binding.buttCode.setVisibility(View.GONE);
            } else  if (getArguments().getInt("category") == 8){
                resTitles = getResources().getStringArray(R.array.item_section_serialization);
                resContent = getResources().getStringArray(R.array.item_subsection_serialization);
                 if (getArguments().getInt("position") == 7){
                    binding.buttCode.setVisibility(View.GONE);
                }
            } else if (getArguments().getInt("category") == 9){
                resTitles = getResources().getStringArray(R.array.item_section_multithreading);
                resContent = getResources().getStringArray(R.array.item_subsection_multithreading);
                binding.buttCode.setVisibility(View.GONE);
            }else {
                resTitles = getResources().getStringArray(R.array.item_section_JDBC);
                resContent = getResources().getStringArray(R.array.item_subsection_JDBC);
                if(getArguments().getInt("position") == 0){
                    binding.buttCode.setVisibility(View.GONE);
                } else if (getArguments().getInt("position") == 1){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 3){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 5){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 7){
                    binding.buttCode.setVisibility(View.GONE);
                }else if (getArguments().getInt("position") == 8){
                    binding.buttCode.setVisibility(View.GONE);
                }

            }


            binding.titleTxt.setText(resTitles[getArguments().getInt("position")]);
            binding.descTxt.setText(Html.fromHtml(resContent[getArguments().getInt("position")]));
            binding.descTxt.setTextSize(Stash.getInt("textSize", 16));
            binding.buttCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(getArguments().getInt("category") == 1){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_perem_subsecttion);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    } else  if (getArguments().getInt("category") == 2){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_oper_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    } else if(getArguments().getInt("category") == 3){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_cycle_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    }else if (getArguments().getInt("category") == 4){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_OOP_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    }else if (getArguments().getInt("category") == 5){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_core_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));

                    }else if (getArguments().getInt("category") == 6){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_collection_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    }else if (getArguments().getInt("category") == 7){

                    }else if (getArguments().getInt("category") == 8){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_serialization_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    }else if (getArguments().getInt("category") == 9){

                    }else if(getArguments().getInt("category") == 10){
                        String[] resCode;
                        resCode = getResources().getStringArray(R.array.code_array_jdbc_subsection);
                        binding.descTxt.setText(Html.fromHtml(resCode[getArguments().getInt("position")]));
                    }
                    binding.buttCode.setVisibility(View.GONE);
                }
            });
        }

        return binding.getRoot();
    }
}
