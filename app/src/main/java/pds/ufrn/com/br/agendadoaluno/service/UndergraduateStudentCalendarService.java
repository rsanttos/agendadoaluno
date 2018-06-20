package pds.ufrn.com.br.agendadoaluno.service;

import android.os.AsyncTask;
import android.util.Log;

import agendaufrnfw.ufrn.imd.pds.dto.UndergraduateStudentCalendarDTO;
import agendaufrnfw.ufrn.imd.pds.model.calendar.UndergraduateStudentCalendar;
import agendaufrnfw.ufrn.imd.pds.request.CalendarRequest;

/**
 * Created by root on 24/04/18.
 */

public class UndergraduateStudentCalendarService extends AsyncTask<Void, Void, UndergraduateStudentCalendar> {

    private UndergraduateStudentCalendar calendar;

    public UndergraduateStudentCalendar getCalendar(){
        CalendarRequest calendarRequest = new CalendarRequest();
        UndergraduateStudentCalendarDTO cDto = new UndergraduateStudentCalendarDTO();
        return cDto.toObject(calendarRequest.getCalendar());
    }
    @Override
    protected UndergraduateStudentCalendar doInBackground(Void... params) {
        calendar = getCalendar();
        return calendar;
    }

    @Override
    protected void onPostExecute(UndergraduateStudentCalendar calendar){
        Log.v("Calendar", String.valueOf(calendar.getAno()));
    }

}
