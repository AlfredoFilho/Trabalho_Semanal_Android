package a193532_c195741.ft.unicamp.br.aula03;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import a193532_c195741.ft.unicamp.br.aula03.alunos.AlunosFragment;
import a193532_c195741.ft.unicamp.br.aula03.alunos.BiografiasFragment;
import a193532_c195741.ft.unicamp.br.aula03.database.DatabaseFragment;
import a193532_c195741.ft.unicamp.br.aula03.internet.InternetFragment;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2.EstatisticasFragment;
import a193532_c195741.ft.unicamp.br.aula03.kotlin.KotlinActivity;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2.NameFragment;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo1.PuzzleFragment;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.AlunosFraseFragment;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.estatisticas.EstatAlunosFrase;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.estatisticas.EstatFrasesAluno;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.FrasesAlunoFragment;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.Jogo3Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AlunosFragment.OnBiografiaRequest {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame, new FirstFragment(), "first_fragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Você pressionou  o botão Settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_contato) {
            Fragment mailFragment = fragmentManager.findFragmentByTag("mail_fragment");
            if (mailFragment == null) {
                mailFragment = new MailFragment();
            }
            replaceFragment(mailFragment, "mail_fragment");
        }else if (id == R.id.action_estatisticas) {
            Fragment estatisticasFragment = fragmentManager.findFragmentByTag("estatisticas_fragment");
            if (estatisticasFragment == null) {
                estatisticasFragment = new EstatisticasFragment();
            }
            replaceFragment(estatisticasFragment, "estatisticas_fragment");
        }else if (id == R.id.estjogo3aluno) {
            Fragment estatAlunosFrase = fragmentManager.findFragmentByTag("estatAlunosFrase_fragment");
            if (estatAlunosFrase == null) {
                estatAlunosFrase = new EstatAlunosFrase();
            }
            replaceFragment(estatAlunosFrase, "estatAlunosFrase_fragment");
        }else if (id == R.id.estjogo3frase) {
            Fragment estatFrasesAluno = fragmentManager.findFragmentByTag("estatFrasesAluno_fragment");
            if (estatFrasesAluno == null) {
                estatFrasesAluno = new EstatFrasesAluno();
            }
            replaceFragment(estatFrasesAluno, "estatFrasesAluno_fragment");
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_autores) {
            Fragment autoresFragment = fragmentManager.findFragmentByTag("autores_fragment");
            if (autoresFragment == null)
                autoresFragment = new AutoresFragment();
            replaceFragment(autoresFragment, "autores_fragment");
        } else if (id == R.id.nav_alunos) {
            Fragment alunosFragment = fragmentManager.findFragmentByTag("alunos_fragment");
            if (alunosFragment == null){
                alunosFragment = new AlunosFragment();
                ((AlunosFragment) alunosFragment).setAbrirBiografiaAluno(new AlunosFragment.AbrirBiografiaAluno() {
                    @Override
                    public void abrirBiografiaAluno(int position) {
                        BiografiasFragment biografiasFragment = (BiografiasFragment) fragmentManager.findFragmentByTag("biografias_fragment");
                        if (biografiasFragment == null)
                            biografiasFragment = new BiografiasFragment();
                        biografiasFragment.atualizaBiografia(position);
                        replaceFragment(biografiasFragment, "biografias_fragment");
                    }
                });
            }
            replaceFragment(alunosFragment, "alunos_fragment");
        } else if (id == R.id.nav_biografias) {
            Fragment biografiaFragment = fragmentManager.findFragmentByTag("biografias_Fragment");
            if (biografiaFragment == null)
                biografiaFragment = new BiografiasFragment();
            replaceFragment(biografiaFragment, "biografias_Fragment");
        } else if (id == R.id.nav_jogo1) {
            Fragment puzzleFragment = fragmentManager.findFragmentByTag("puzzle_Fragment");
            if (puzzleFragment == null)
                puzzleFragment = new PuzzleFragment();
            replaceFragment(puzzleFragment, "puzzle_Fragment");
        } else if (id == R.id.nav_jogo2) {
            Fragment nameFragment = fragmentManager.findFragmentByTag("jogo2_Fragment");
            if (nameFragment == null)
                nameFragment = new NameFragment();
            replaceFragment(nameFragment, "jogo2_Fragment");
        } else if (id == R.id.nav_jogo3){
            Fragment jogo3Fragment = fragmentManager.findFragmentByTag("jogo3_Fragment");
            if (jogo3Fragment == null)
                jogo3Fragment = new Jogo3Fragment();
            replaceFragment(jogo3Fragment, "jogo3_Fragment");
        }else if (id == R.id.nav_Kotlin){
            Intent intent = new Intent(this, KotlinActivity.class);
            intent.putExtra("chave","string inicial");
            startActivity(intent);
        }
        else if (id == R.id.nav_database){
            Fragment databaseFragment = fragmentManager.findFragmentByTag("database_Fragment");
            if (databaseFragment == null)
                databaseFragment = new DatabaseFragment();
            replaceFragment(databaseFragment, "database_Fragment");
        }

        else if (id == R.id.nav_internet){
            Fragment interFragment = fragmentManager.findFragmentByTag("internet_Fragment");
            if (interFragment == null)
                interFragment = new InternetFragment();
            replaceFragment(interFragment, "internet_Fragment");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceAlunosfrase(){
        Fragment alunosFrase = fragmentManager.findFragmentByTag("alunosFrase_Fragment");
        if (alunosFrase == null)
            alunosFrase = new AlunosFraseFragment();
        replaceFragment(alunosFrase, "alunosFrase_Fragment");
    }

    public void replaceFrasesAluno(){
        Fragment alunosFrase = fragmentManager.findFragmentByTag("frasesAluno_Fragment");
        if (alunosFrase == null)
            alunosFrase = new FrasesAlunoFragment();
        replaceFragment(alunosFrase, "frasesAluno_Fragment");
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void abrirAutoresFragment(String mensagem) {
        AutoresFragment autoresFragment = (AutoresFragment) fragmentManager.findFragmentByTag("autores_fragment");
        if (autoresFragment == null)
            autoresFragment = new AutoresFragment();
        autoresFragment.setMailContent(mensagem);
        replaceFragment(autoresFragment, "autores_fragment");
    }

    @Override
    public void setPosition(int position) {
        BiografiasFragment biografiasFragment = (BiografiasFragment) fragmentManager.findFragmentByTag("biografias");
        if (biografiasFragment == null) {
            biografiasFragment = new BiografiasFragment();
        }
        biografiasFragment.atualizaBiografia(position);
        replaceFragment(biografiasFragment, "biografias");
    }
}
