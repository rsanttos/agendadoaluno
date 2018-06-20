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

import com.applandeo.materialcalendarview.EventDay;

import java.util.*;
import java.util.concurrent.ExecutionException;

import agendaufrnfw.ufrn.imd.pds.dto.ClassDTO;
import agendaufrnfw.ufrn.imd.pds.dto.EvaluationDTO;
import agendaufrnfw.ufrn.imd.pds.dto.StudentDTO;
import agendaufrnfw.ufrn.imd.pds.dto.TaskDTO;
import agendaufrnfw.ufrn.imd.pds.model.calendar.Evaluation;
import agendaufrnfw.ufrn.imd.pds.model.calendar.ProfessorCalendar;
import agendaufrnfw.ufrn.imd.pds.model.calendar.Task;
import agendaufrnfw.ufrn.imd.pds.model.user.GraduateStudent;
import agendaufrnfw.ufrn.imd.pds.model.user.UndergraduateStudent;
import pds.ufrn.com.br.agendadoaluno.service.UndergraduateStudentService;

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
            UndergraduateStudentService studentService = new UndergraduateStudentService(token);
            try {
                UndergraduateStudent student = studentService.execute().get();
                tvNome.setText(student.getNome_discente());
                tvCurso.setText(student.getNome_curso());
                tvMatricula.setText(String.valueOf(student.getMatricula()));
                List<Task> allTasks = new ArrayList<Task>();
                List<Evaluation> allEvaluations = new ArrayList<Evaluation>();
                for(ClassDTO classe : student.getClasses()){
                    allTasks.addAll(classe.getTasks());
                    allEvaluations.addAll(classe.getEvaluations());
                }

                ArrayAdapter<Task> arrayAdapterTarefas = new ArrayAdapter<Task>(this,
                        android.R.layout.simple_list_item_1, allTasks);
                lvTarefas.setAdapter(arrayAdapterTarefas);

                ArrayAdapter<Evaluation> arrayAdapterAvaliacoes = new ArrayAdapter<Evaluation>(this,
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
            case R.id.item_calendario_compromissos:
                intent = new Intent();
                intent.setClass(this, CalendarCustomActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_tarefas_avaliacoes:
                intent = new Intent();
                intent.setClass(this, StudentActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_calendario:
                intent = new Intent();
                intent.setClass(this, CalendarActivity.class);
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
