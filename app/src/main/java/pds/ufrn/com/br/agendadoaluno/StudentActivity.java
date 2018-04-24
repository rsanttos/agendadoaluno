package pds.ufrn.com.br.agendadoaluno;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pds.ufrn.com.br.agendadoaluno.dto.ClassDTO;
import pds.ufrn.com.br.agendadoaluno.dto.EvaluationDTO;
import pds.ufrn.com.br.agendadoaluno.dto.StudentDTO;
import pds.ufrn.com.br.agendadoaluno.dto.TaskDTO;
import pds.ufrn.com.br.agendadoaluno.request.StudentRequest;
import pds.ufrn.com.br.agendadoaluno.service.StudentService;

import static android.R.layout.simple_list_item_1;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvCurso = (TextView) findViewById(R.id.tvCurso);
        TextView tvMatricula = (TextView) findViewById(R.id.tvMatricula);
        ListView lvTarefas = (ListView) findViewById(R.id.lvTarefas);
        ListView lvAvaliacoes = (ListView) findViewById(R.id.lvAvaliacoes);

        criarBarraMenu();

        if(getIntent().hasExtra("token")){
            String token = getIntent().getStringExtra("token");
            StudentService studentService = new StudentService(token);
            try {
                StudentDTO student = studentService.execute().get();
                tvNome.setText(student.getNome_discente());
                tvCurso.setText(student.getNome_curso());
                tvMatricula.setText(String.valueOf(student.getMatricula()));
                List<TaskDTO> allTasks = new ArrayList<TaskDTO>();
                List<EvaluationDTO> allEvaluations = new ArrayList<EvaluationDTO>();
                for(ClassDTO classe : student.getClasses()){
                    allTasks.addAll(classe.getTasks());
                    allEvaluations.addAll(classe.getEvaluations());
                }

                ArrayAdapter<TaskDTO> arrayAdapterTarefas = new ArrayAdapter<TaskDTO>(this,
                        android.R.layout.simple_list_item_1, allTasks);
                lvTarefas.setAdapter(arrayAdapterTarefas);

                ArrayAdapter<EvaluationDTO> arrayAdapterAvaliacoes = new ArrayAdapter<EvaluationDTO>(this,
                        android.R.layout.simple_list_item_1, allEvaluations);
                lvAvaliacoes.setAdapter(arrayAdapterAvaliacoes);



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void criarBarraMenu() {
        Toolbar menuSIGAgenda = (Toolbar) findViewById(R.id.menu);
        setSupportActionBar(menuSIGAgenda);
        menuSIGAgenda.setLogo(R.mipmap.ic_launcher);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        String token = "";
        int id = item.getItemId();
        switch (id){
            case R.id.item_tarefas_avaliacoes:
                intent = new Intent();
                intent.setClass(this, StudentActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_calendario:
                intent = new Intent();
                intent.setClass(this, Calendar.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_emprestimos:
                intent = new Intent();
                intent.setClass(this, LoanActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
