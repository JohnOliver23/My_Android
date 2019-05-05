package com.example.osalvador

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.row.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var dao: PessoaDAO
    private lateinit var pessoasLv: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao = PessoaDAO(this)
        LoadQuery()

    }

    fun LoadQuery(){
        var myContactsAdapter = MyContactsAdapter(this, dao.get() as ArrayList<Pessoa>)
        this.pessoasLv = findViewById(R.id.lvPessoas)
        pessoasLv.adapter = myContactsAdapter
    }

    override fun onResume() {
        super.onResume()
        LoadQuery()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sv:android.support.v7.widget.SearchView = menu!!.findItem(R.id.app_bar_search).actionView as android.support.v7.widget.SearchView
        sv.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!=null){
            when(item.itemId){
                R.id.addContact->{
                    Log.e("APP_PESSOA","entrou!")
                    startActivity(Intent(this, AddActivity::class.java))
                }
                R.id.action_settings->{
                    Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    inner class MyContactsAdapter : BaseAdapter {
        var listPessoas = ArrayList<Pessoa>()
        var context:Context?=null

        constructor(context: Context, listContactsAdapter: ArrayList<Pessoa>): super(){
            this.listPessoas = listContactsAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.row, null)
            var myContact = listPessoas[position]
            myView.titleTv.text = myContact.nome
            myView.ageTv.text = myContact.idade.toString()+" years"
            myView.deleteBtn.setOnClickListener{
                dao.delete(myContact.id)
                LoadQuery()
            }
            myView.editBtn.setOnClickListener({
                updateContact(myContact)
            })
            return myView;
        }

        override fun getItem(position: Int): Any {
            return listPessoas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listPessoas.size
        }

    }
    private fun updateContact(pessoa: Pessoa){
        var intent = Intent(this, AddActivity::class.java)
        intent.putExtra("id",pessoa.id)
        intent.putExtra("name",pessoa.nome)
        intent.putExtra("age", pessoa.idade)
        startActivity(intent)
    }




}
