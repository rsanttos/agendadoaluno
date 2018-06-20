package pds.ufrn.com.br.agendadoaluno.service;

import android.os.AsyncTask;
import android.util.Log;

import agendaufrnfw.ufrn.imd.pds.model.user.UndergraduateStudent;
import agendaufrnfw.ufrn.imd.pds.model.user.factory.UndergraduateStudentFactory;
import agendaufrnfw.ufrn.imd.pds.request.StudentRequest;

/**
 * Created by f202359 on 13/04/2018.
 */

public class UndergraduateStudentService extends AsyncTask<Void, Void, UndergraduateStudent> {
    private String token;
    public UndergraduateStudentService(String token){
        this.token = token;
    }

    @Override
    protected UndergraduateStudent doInBackground(Void... params) {
        StudentRequest studentRequest = new StudentRequest();
        UndergraduateStudentFactory graduateStudentFactory = new UndergraduateStudentFactory();
        String studentStr = studentRequest.getEvaluationsAndTasksUndergraduateStudentLoggedIn(token);
        UndergraduateStudent gs = (UndergraduateStudent) graduateStudentFactory.createUserFromJson(studentStr);
        return gs;
    }

    @Override
    protected void onPostExecute(UndergraduateStudent gs){
        Log.v("Student", gs.getNome_discente());
    }
}
