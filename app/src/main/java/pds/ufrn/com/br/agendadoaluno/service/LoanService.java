package pds.ufrn.com.br.agendadoaluno.service;

import android.os.AsyncTask;
import android.util.Log;

import pds.ufrn.com.br.agendadoaluno.dto.LoanDTO;
import pds.ufrn.com.br.agendadoaluno.dto.StudentDTO;
import pds.ufrn.com.br.agendadoaluno.request.LoanRequest;
import pds.ufrn.com.br.agendadoaluno.request.StudentRequest;

/**
 * Created by root on 23/04/18.
 */

public class LoanService extends AsyncTask<Void, Void, LoanDTO[]> {
    private String token;
    private String cpf;
    public LoanService(String cpf, String token){
        this.token = token;
        this.cpf = cpf;
    }

    @Override
    protected LoanDTO[] doInBackground(Void... params) {
        LoanRequest loanRequest = new LoanRequest();
        LoanDTO[] loans = LoanDTO.toArrayObject(loanRequest.getStudentLoans(cpf, token));
        return loans;
    }

    @Override
    protected void onPostExecute(LoanDTO[] loans){
        Log.v("Loans", String.valueOf(loans.length));
    }
}
