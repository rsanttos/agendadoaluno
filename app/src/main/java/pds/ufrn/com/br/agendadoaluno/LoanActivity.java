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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pds.ufrn.com.br.agendadoaluno.dto.LoanDTO;
import pds.ufrn.com.br.agendadoaluno.dto.StudentDTO;
import pds.ufrn.com.br.agendadoaluno.dto.TaskDTO;
import pds.ufrn.com.br.agendadoaluno.request.LoanRequest;
import pds.ufrn.com.br.agendadoaluno.service.LoanService;
import pds.ufrn.com.br.agendadoaluno.service.StudentService;

public class LoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        criarBarraMenu();
        ListView lvLoans = (ListView) findViewById(R.id.lvEmprestimos);
        TextView tvNaoPossuiEmprestimos = (TextView) findViewById(R.id.tvNaoPossuiEmprestimos);

        if(getIntent().hasExtra("token")){
            String token = getIntent().getStringExtra("token");
            try {
                StudentService studentService = new StudentService(token);
                StudentDTO student = studentService.execute().get();
                LoanService loanService = new LoanService(student.getCpf_cnpj(), token);
                LoanDTO[] loans = loanService.execute().get();
                List<LoanDTO> loansArray = new ArrayList<LoanDTO>();
                for(int i = 0 ; i < loans.length ; i++){
                    loansArray.add(loans[i]);
                }
                if(loansArray.size() > 0) {
                    ArrayAdapter<LoanDTO> arrayAdapterEmprestimos = new ArrayAdapter<LoanDTO>(this,
                            android.R.layout.simple_list_item_1, loansArray);
                    lvLoans.setAdapter(arrayAdapterEmprestimos);
                } else {
                    tvNaoPossuiEmprestimos.setText("Você não possui empréstimos em aberto.");
                }

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
