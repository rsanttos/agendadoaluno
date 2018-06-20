package pds.ufrn.com.br.agendadoaluno;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import agendaufrnfw.ufrn.imd.pds.dto.HolidayDTO;
import agendaufrnfw.ufrn.imd.pds.dto.UndergraduateStudentCalendarDTO;
import agendaufrnfw.ufrn.imd.pds.model.calendar.Holiday;
import agendaufrnfw.ufrn.imd.pds.model.calendar.UndergraduateStudentCalendar;
import pds.ufrn.com.br.agendadoaluno.service.UndergraduateStudentCalendarService;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        criarBarraMenu();

        TextView tvAno = (TextView) findViewById(R.id.tvValorAno);
        TextView tvMatricula = (TextView) findViewById(R.id.tvValorMatricula);
        TextView tvMatriculaExtraordinaria = (TextView) findViewById(R.id.tvValorMatriculaExtraordinaria);
        TextView tvRematricula = (TextView) findViewById(R.id.tvValorRematricula);
        TextView tvPeriodo = (TextView) findViewById(R.id.tvValorPeriodo);

        UndergraduateStudentCalendarService calendarService = new UndergraduateStudentCalendarService();
        UndergraduateStudentCalendar undergraduateStudentCalendar = null;

        try {
            undergraduateStudentCalendar = calendarService.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(undergraduateStudentCalendar != null){
            tvAno.setText(String.valueOf(undergraduateStudentCalendar.getAno()));
            tvMatricula.setText(undergraduateStudentCalendar.getInicio_matricula_online() + " - " + undergraduateStudentCalendar.getFim_matricula_online());
            tvMatriculaExtraordinaria.setText(undergraduateStudentCalendar.getInicio_matricula_extraordinaria() + " - " + undergraduateStudentCalendar.getFim_matricula_extraordinaria());
            tvPeriodo.setText(undergraduateStudentCalendar.getInicio_periodo() + " - " + undergraduateStudentCalendar.getFim_periodo());
            tvRematricula.setText(undergraduateStudentCalendar.getInicio_rematricula() + " - " + undergraduateStudentCalendar.getFim_rematricula());

            ListView lvHolidays = (ListView) findViewById(R.id.lvFeriados);
            ArrayAdapter<Holiday> arrayAdapterHolidays = new ArrayAdapter<Holiday>(this,
                    android.R.layout.simple_list_item_1, undergraduateStudentCalendar.getHolidays());
            lvHolidays.setAdapter(arrayAdapterHolidays);
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
