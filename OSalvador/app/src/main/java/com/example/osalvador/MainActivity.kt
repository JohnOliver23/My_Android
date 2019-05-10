package com.example.osalvador

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.ClipboardManager
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
    //shared preferences
    var mSharedPref:SharedPreferences?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSharedPref = this.getSharedPreferences("My_Data", android.content.Context.MODE_PRIVATE)
        dao = PessoaDAO(this)
        //load sorting
        var mSorting = mSharedPref!!.getString("Sort", "ascending")
        when(mSorting){
            "ascending"->LoadQuery("%","ascending")
            "descending"->LoadQuery("%","descending")
            "newest"->LoadQuery("%","newest")
            "oldest"->LoadQuery("%","oldest")
        }

    }

    fun LoadQuery(title: String, sort:String){
        var myContactsAdapter:MyContactsAdapter?=null
        when(sort){
            "ascending"-> myContactsAdapter = MyContactsAdapter(this, dao.getAscending(title) as ArrayList<Pessoa>)
            "descending"->myContactsAdapter = MyContactsAdapter(this, dao.getDescending(title) as ArrayList<Pessoa>)
            "newest"->myContactsAdapter = MyContactsAdapter(this, dao.getNewest(title) as ArrayList<Pessoa>)
            "oldest"->myContactsAdapter = MyContactsAdapter(this, dao.getOldest(title) as ArrayList<Pessoa>)
        }
        this.pessoasLv = findViewById(R.id.lvPessoas)
        pessoasLv.adapter = myContactsAdapter
    }

    override fun onResume() {
        super.onResume()
        //load sorting
        var mSorting = mSharedPref!!.getString("Sort", "ascending")
        when(mSorting){
            "ascending"->LoadQuery("%","ascending")
            "descending"->LoadQuery("%","descending")
            "newest"->LoadQuery("%","newest")
            "oldest"->LoadQuery("%","oldest")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sv:android.support.v7.widget.SearchView = menu!!.findItem(R.id.app_bar_search).actionView as android.support.v7.widget.SearchView
        sv.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var mSorting = mSharedPref!!.getString("Sort", "ascending")
                when(mSorting){
                    "ascending"->LoadQuery(query+"%","ascending")
                    "descending"->LoadQuery(query+"%","descending")
                    "newest"->LoadQuery(query+"%","newest")
                    "oldest"->LoadQuery(query+"%","oldest")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var mSorting = mSharedPref!!.getString("Sort", "ascending")
                when(mSorting){
                    "ascending"->LoadQuery(newText+"%","ascending")
                    "descending"->LoadQuery(newText+"%","descending")
                    "newest"->LoadQuery(newText+"%","newest")
                    "oldest"->LoadQuery(newText+"%","oldest")
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!=null){
            when(item.itemId){
                R.id.addContact->{
                    startActivity(Intent(this, AddActivity::class.java))
                }
                R.id.action_sort->{
                    //show sorting dialog
                    showSortDialog()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSortDialog(){
        //list of sorting options
        val sortOptions = arrayOf("Name(Ascending)", "Name(Descending)","Newest", "Oldest")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Sort by")
        mBuilder.setIcon(R.drawable.ic_action_sort)
        mBuilder.setSingleChoiceItems(sortOptions, -1){
            dialogInterface, i->
            if (i==0){
               //Name ascending
                Toast.makeText(this, "Name ascending", Toast.LENGTH_SHORT).show()
                val editor = mSharedPref!!.edit()
                editor.putString("Sort", "ascending")
                editor.apply()
                LoadQuery("%","ascending")

            }
            if (i==1){
                //Name descending
                Toast.makeText(this, "Name descending", Toast.LENGTH_SHORT).show()
                val editor = mSharedPref!!.edit()
                editor.putString("Sort", "descending")
                editor.apply()
                LoadQuery("%","descending")

            }
            if (i==2){
                //Newest
                Toast.makeText(this, "Newest", Toast.LENGTH_SHORT).show()
                val editor = mSharedPref!!.edit()
                editor.putString("Sort", "newest")
                editor.apply()
                LoadQuery("%","newest")

            }
            if (i==3){
                //Oldest
                Toast.makeText(this, "Oldest", Toast.LENGTH_SHORT).show()
                val editor = mSharedPref!!.edit()
                editor.putString("Sort", "oldest")
                editor.apply()
                LoadQuery("%","oldest")
            }
            dialogInterface.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
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
            //delete
            myView.deleteBtn.setOnClickListener{
                dao.delete(myContact.id)
                var mSorting = mSharedPref!!.getString("Sort", "ascending")
                when(mSorting){
                    "ascending"->LoadQuery("%","ascending")
                    "descending"->LoadQuery("%","descending")
                    "newest"->LoadQuery("%","newest")
                    "oldest"->LoadQuery("%","oldest")
                }
            }
            //update
            myView.editBtn.setOnClickListener({
                updateContact(myContact)
            })
            //copy btn click
            myView.copyBtn.setOnClickListener({
                //get name
                val name = myView.titleTv.text.toString()
                //get age
                val age = myView.ageTv.text.toString()
                //concat
                val concat = name+"\n"+age
                val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cb.text = concat
                Toast.makeText(this@MainActivity, "Message Copied", Toast.LENGTH_SHORT).show()
            })
            //share btn click
            myView.shareBtn.setOnClickListener({
                //get name
                val name = myView.titleTv.text.toString()
                //get age
                val age = myView.ageTv.text.toString()
                //concat
                val concat = name+"\n"+age
                //share intent
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, concat)
                startActivity(Intent.createChooser(shareIntent,concat))
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
