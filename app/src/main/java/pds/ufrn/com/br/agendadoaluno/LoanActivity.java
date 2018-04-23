package pds.ufrn.com.br.agendadoaluno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ListView lvLoans = (ListView) findViewById(R.id.lvEmprestimos);

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

                ArrayAdapter<LoanDTO> arrayAdapterEmprestimos = new ArrayAdapter<LoanDTO>(this,
                        android.R.layout.simple_list_item_1, loansArray);
                lvLoans.setAdapter(arrayAdapterEmprestimos);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
