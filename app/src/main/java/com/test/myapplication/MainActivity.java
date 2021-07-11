package com.test.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.test.myapplication.Compiler.CompilerActivity;
import com.test.myapplication.Forum.ForumActivity;
import com.test.myapplication.Intro.ChatIntroActivity;
import com.test.myapplication.Intro.CompileIntroActivity;
import com.test.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private ViewDataBinding mIntro;

    private Drawer mDrawer;
    private AccountHeader mHeader;
    private MenuItem searchMenuItem;
    private SearchView mSearchView;


    private NavHostFragment mHostFragment;
    private NavController mController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.containerMain);
        if (mHostFragment != null) {
            mController = mHostFragment.getNavController();
        }
       // setSupportActionBar(mBinding.toolbar);
        mBinding.searchTest.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Bundle bundle = new Bundle();
                bundle.putInt("status", 2);
                bundle.putString("filter", newText);
                mController.navigate(R.id.action_global_mainFragment, bundle);
                return false;
            }
        });
        initDrawer();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        MenuItem settingsItem = menu.findItem(R.id.action_setting);
        settingsItem.setOnMenuItemClickListener(menuItem -> {
            mController.navigate(R.id.action_global_settingsFragment);
            return true;
        });
        searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Bundle bundle = new Bundle();
                bundle.putInt("status", 1);
                mController.navigate(R.id.action_global_mainFragment, bundle);
                return true;
            }
        });
        mSearchView = (SearchView) searchMenuItem.getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Bundle bundle = new Bundle();
                bundle.putInt("status", 2);
                bundle.putString("filter", newText);
                mController.navigate(R.id.action_global_mainFragment, bundle);
                return false;
            }
        });

        return true;
    }

    void initDrawer() {
        List<IDrawerItem> items = new ArrayList<>();
        items.add(new PrimaryDrawerItem().withBadge("12").withIdentifier(0).withName("Переменные и типы  данных")
        .withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("5").withIdentifier(1).withName("Логические и условные  операторы").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("5").withIdentifier(2).withName("Операторы цикла").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("9").withIdentifier(3).withName("Объектно-ориентированное  програмирование").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("10").withIdentifier(4).withName("Java Core").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("10").withIdentifier(5).withName("Коллекции").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("14").withIdentifier(6).withName("Потоки ввода/вывода").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("8").withIdentifier(7).withName("Сериализация").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("23").withIdentifier(8).withName("Многопоточность").withIcon(R.drawable.java_icon));
        items.add(new PrimaryDrawerItem().withBadge("10").withIdentifier(9).withName("JDBC").withIcon(R.drawable.java_icon));
        items.add(new DividerDrawerItem());
        items.add(new PrimaryDrawerItem().withIdentifier(items.size()).withName("Избранное").withIcon(R.drawable.favorite));
        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(items.size()).withIcon(R.drawable.setting_icon).withName(
                R.string.settings);
        PrimaryDrawerItem forumItem = new PrimaryDrawerItem().withIdentifier(items.size()).withIcon(R.drawable.chat).withName(
                "Чат-форум");
        PrimaryDrawerItem compileItem = new PrimaryDrawerItem().withIdentifier(items.size()).withIcon(R.drawable.compile).withName(
                "Компилятор");
        items.add(settingsItem);
        items.add(forumItem);
        items.add(compileItem);
        mHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.ctr_bg)
                .addProfiles(
                        new ProfileDrawerItem().withIdentifier(items.size()+1).withName("Java learning")
                )
                .build();
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.drawler_header)
                .withCloseOnClick(true)
                .withMultiSelect(false)
                .withDrawerItems(items)
                .withSliderBackgroundColor(getResources().getColor(R.color.slide))
                .build();

        mDrawer.setOnDrawerItemClickListener((view, position, drawerItem) -> {
            Bundle bundle;
            switch (position) {
                case 0:

                case 1:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category", 1);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;

                case 2:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category", 2);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 3:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category", 3);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 4:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category", 4);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 5:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category", 5);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 6:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category", 6);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 7:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category",7);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 8:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category",8);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 9:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category",9);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 10:
                    bundle = new Bundle();
                    bundle.putInt("status", 3);
                    bundle.putInt("category",10);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;
                case 12:
                    bundle = new Bundle();
                    bundle.putInt("status", 4);
                    mController.navigate(R.id.action_global_mainFragment, bundle);
                    break;

                case 13:
                    mController.navigate(R.id.action_global_settingsFragment);
                    break;
                case 14:
                    Intent intentForum = new Intent(MainActivity.this, ChatIntroActivity.class);
                    startActivity(intentForum);
                    break;
                case 15:
                    Intent intentCompile = new Intent(MainActivity.this, CompileIntroActivity.class);
                    startActivity(intentCompile);
                    break;
            }
            return true;
        });
    }
}
