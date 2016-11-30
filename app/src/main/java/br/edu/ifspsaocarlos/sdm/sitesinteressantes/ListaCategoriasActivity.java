package br.edu.ifspsaocarlos.sdm.sitesinteressantes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifspsaocarlos.sdm.sitesinteressantes.model.Site;

public class ListaCategoriasActivity extends ListActivity {

    private static final int INTENT_NOVA_CATEGORIA = 2;

    private static final int INTENT_LISTA_SITES = 3;

    private Map<String, List<Site>> categoriaSites;

    private String chaveCategoriaAtual = "Geral";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriaSites = new HashMap<>();
        categoriaSites.put("Geral", new ArrayList<Site>());
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>(categoriaSites.keySet()));
        setListAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_categorias, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nova_categoria) {
            Intent intentNovo = new Intent(this, NovaCategoriaActivity.class);
            startActivityForResult(intentNovo, INTENT_NOVA_CATEGORIA);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_NOVA_CATEGORIA) {
            switch (resultCode) {
                case RESULT_OK:
                    categoriaSites.put(data.getStringExtra("categoria"), new ArrayList<Site>());
                    ListAdapter adaptador = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1, new ArrayList<String>(categoriaSites.keySet()));
                    setListAdapter(adaptador);
                    Toast.makeText(this, "Nova categoria adicionada.", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "Ação cancelada.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, "Ação inexistente.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        if (requestCode == INTENT_LISTA_SITES) {
            List<Site> sites = (ArrayList<Site>) data.getSerializableExtra("sites");
            categoriaSites.put(chaveCategoriaAtual, sites);
        }
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        chaveCategoriaAtual = (String) getListAdapter().getItem(position);
        List<Site> sites = categoriaSites.get(chaveCategoriaAtual);

        Intent intencaoListaSites = new Intent(this, ListaSitesActivity.class);
        intencaoListaSites.putExtra("sites", (Serializable) sites);
        startActivityForResult(intencaoListaSites, INTENT_LISTA_SITES);
    }
}
