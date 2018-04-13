package pds.ufrn.com.br.agendadoaluno.request;

import pds.ufrn.com.br.agendadoaluno.dto.StudentDTO;

/**
 * Created by f202359 on 13/04/2018.
 */

public class StudentRequest extends GenericRequest {
    public StudentRequest(){

    }

    public String getEvaluationsAndTasksStudentLoggedIn(String token){
        url = String.format("/student/%s", token);
        String resultado = super.objectRequest();
        return resultado;
    }
}
