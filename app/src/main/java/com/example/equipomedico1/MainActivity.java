package com.example.equipomedico1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.equipomedico1.API.ClienteRetrofit;
import com.example.equipomedico1.API.InterfazBuscarAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.example.equipomedico1.Adaptador.AdapatadorEquipoMedico;
import com.example.equipomedico1.Modelo.EquipoMedico;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    InterfazBuscarAPI miAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapatadorEquipoMedico adapatadorEquipoMedico;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miAPI = getAPI();




        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);


        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));



        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);
        materialSearchBar.setCardViewElevation(10);

        addSuggestList();



        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List<String> suggest = new ArrayList<>();
                    for(String search_term:suggestList)
                        if(search_term.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                            suggest.add(search_term);
                    materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled) {
                    getAllEquipo();
                }
            }
            

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        getAllEquipo();

    }



    private InterfazBuscarAPI getAPI() {
        return ClienteRetrofit.getInstancia().create(InterfazBuscarAPI.class);
    }




    private void startSearch(String query) {
        compositeDisposable.add(miAPI.buscarEquipoMedico(query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<EquipoMedico>>() {
            @Override
            public void accept(List<EquipoMedico> equipoMedico) throws Exception {
                adapatadorEquipoMedico = new AdapatadorEquipoMedico(equipoMedico);
                recyclerView.setAdapter(adapatadorEquipoMedico);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(MainActivity.this, "No se encuentra."+throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        }));
    }





    private void getAllEquipo() {
        compositeDisposable.add(miAPI.obtenerEquipoMedico()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<EquipoMedico>>() {
                    @Override
                    public void accept(List<EquipoMedico> equipoMedicos) throws Exception {
                        adapatadorEquipoMedico = new AdapatadorEquipoMedico(equipoMedicos);
                        recyclerView.setAdapter(adapatadorEquipoMedico);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(MainActivity.this, "No se encuentra..."+throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                }));
    }



    private void addSuggestList() {

        suggestList.add("equipo 1");
        suggestList.add("equipo 2");
        suggestList.add("equipo 3");

        materialSearchBar.setLastSuggestions(suggestList);


    }




}