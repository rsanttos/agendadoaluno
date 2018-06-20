package pds.ufrn.com.br.agendadoaluno.service;

import android.os.AsyncTask;
import android.util.Log;

import agendaufrnfw.ufrn.imd.pds.dto.LoanDTO;
import agendaufrnfw.ufrn.imd.pds.model.calendar.Loan;
import agendaufrnfw.ufrn.imd.pds.request.LoanRequest;

/**
 * Created by root on 23/04/18.
 */

public class LoanService extends AsyncTask<Void, Void, Loan[]> {
    private String token;
    private String cpf;
    public LoanService(String cpf, String token){
        this.token = token;
        this.cpf = cpf;
    }

    @Override
    protected Loan[] doInBackground(Void... params) {
        LoanRequest loanRequest = new LoanRequest();
        LoanDTO lDto = new LoanDTO();
        Loan[] loans = lDto.toArrayObject(loanRequest.getStudentLoans(cpf, token));
        return loans;
    }

    @Override
    protected void onPostExecute(Loan[] loans){
        Log.v("Loans", String.valueOf(loans.length));
    }
}

