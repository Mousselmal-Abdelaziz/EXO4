package com.example.appexo1s3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ArrayList<Achat>> List= new ArrayList<ArrayList<Achat>>();
    ArrayList<String>nomlist;
    ArrayList<Achat> listAchats;
     MyAdapter myadapter;
     ArrayAdapter<String>menuadapter;
    EditText item,qte;
    ListView listAchatsView;
    TextView title ;
    Integer  k=0;
    Integer l=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item=(EditText)findViewById(R.id.item);
        qte=(EditText)findViewById(R.id.qte);
        title=(TextView)findViewById(R.id.titletlist) ;
         listAchatsView = (ListView)findViewById(R.id.listview);

        listAchats=new ArrayList<Achat>();
        /*listAchats.add(new Achat(" kg farine",10) );
        listAchats.add(new Achat(" L Hulle",10));
        listAchats.add(new Achat("kg Tomates",4));
        listAchats.add(new Achat(" Levures",10));
        listAchats.add(new Achat("l Eau",10));
        listAchats.add(new Achat("Extrait de vanille",1));
        listAchats.add(new Achat("g poivre noir",100));
        listAchats.add(new Achat("g Olives noires",200));*/
        nomlist= new ArrayList<String>();
        nomlist.add("Liste "+l);
        String t =(String)nomlist.get(k);
        title.setText(t);
        List.add(listAchats);


       myadapter= new MyAdapter(this,listAchats);
        listAchatsView.setAdapter(myadapter);
       // unregisterForContextMenu(title);   // context menu



        
    }
   /* @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

           menu.add(0,1,0,"sara");
           menu.add(0,2,0,"aouina");*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.addNewList){
                ArrayList<Achat> list = new ArrayList<Achat>();
                l=l+1;
                k=k+1;
                nomlist.add("Liste "+l);
                title.setText(nomlist.get(k));
                List.add(list);
                myadapter= new MyAdapter(this,list);
                listAchatsView.setAdapter(myadapter);

        }else if(item.getItemId()==R.id.clearList){
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirmation");
            alert.setMessage("Would you delete all the products from this list ?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    List.get(k).clear();
                    myadapter.notifyDataSetChanged();
                    dialog.cancel();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();

         }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Purshase list app");
            dialog.setTitle("About");
            dialog.setNeutralButton("Ok",null);
            dialog.show();
        }
        return  true;
    }


    public void buClick(View view) {
        String item1 =item.getText().toString();
        double qte1 =Double.parseDouble(qte.getText().toString());
        List.get(k).add(new Achat(item1,qte1));
        item.setText("");
        qte.setText("");
       myadapter.notifyDataSetChanged();
    }

    private class MyAdapter extends ArrayAdapter<Achat> { // utilise adapter when we have list view
       private ArrayList<Achat>  listAchats ;
       private Activity context;
        // initialisation d'une list

        public MyAdapter(Activity context,ArrayList<Achat> listAchats) {   //constrocteur
            super(context,R.layout.liste_view,listAchats);
            this.listAchats=listAchats;
            this.context=context;
        }

        @Override
        public int getCount() {
            return listAchats.size();
        }

        @Override
        public Achat getItem(int position) {   // getteur
            return listAchats.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=context.getLayoutInflater() ;
                convertView=inflater.inflate(R.layout.liste_view,null);

            }
            final   Achat s = listAchats.get(position);
            final TextView itemName=(TextView)convertView.findViewById(R.id.item);
            itemName.setText(s.item);
            final TextView itemqte=(TextView)convertView.findViewById(R.id.qte);
            itemqte.setText(String.valueOf(s.qte));
             Button sup = (Button)convertView.findViewById(R.id.supprimer);
            Button mod = (Button)convertView.findViewById(R.id.modifier);
            sup.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){

                   // int position =(Integer) v.getTag();
                    listAchats.remove(position);
                    notifyDataSetChanged();

                }
            });
            mod.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){


                }
            });


            return convertView;
        }


    }

}

