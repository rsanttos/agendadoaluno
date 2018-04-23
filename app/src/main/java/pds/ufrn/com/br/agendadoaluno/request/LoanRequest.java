package pds.ufrn.com.br.agendadoaluno.request;

/**
 * Created by root on 23/04/18.
 */

public class LoanRequest extends GenericRequest {
    public LoanRequest(){
    }

    public String getStudentLoans(String cpf, String token){
        url = String.format("/student/loans/%s/%s", cpf, token);
        String resultado = super.objectRequest();
        return resultado;
    }
}
