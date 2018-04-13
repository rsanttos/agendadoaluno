package pds.ufrn.com.br.agendadoaluno.service;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import pds.ufrn.com.br.agendadoaluno.dto.StudentDTO;
import pds.ufrn.com.br.agendadoaluno.request.StudentRequest;

/**
 * Created by f202359 on 13/04/2018.
 */

public class StudentService extends AsyncTask<Void, Void, StudentDTO> {
    private String token;
    public StudentService(String token){
        this.token = token;
    }

    @Override
    protected StudentDTO doInBackground(Void... params) {
        StudentRequest studentRequest = new StudentRequest();
        StudentDTO studentDto = StudentDTO.toObject(studentRequest.getEvaluationsAndTasksStudentLoggedIn(token));
        return studentDto;
    }

    @Override
    protected void onPostExecute(StudentDTO studentDto){
        Log.v("Student", studentDto.getNome_discente());
    }
}
