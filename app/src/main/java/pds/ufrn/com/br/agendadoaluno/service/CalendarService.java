package pds.ufrn.com.br.agendadoaluno.service;

import android.os.AsyncTask;
import android.util.Log;

import pds.ufrn.com.br.agendadoaluno.dto.CalendarDTO;
import pds.ufrn.com.br.agendadoaluno.dto.LoanDTO;
import pds.ufrn.com.br.agendadoaluno.request.CalendarRequest;
import pds.ufrn.com.br.agendadoaluno.request.LoanRequest;

/**
 * Created by root on 24/04/18.
 */

public class CalendarService extends AsyncTask<Void, Void, CalendarDTO> {

    private CalendarDTO calendar;

    public CalendarDTO getCalendar(){
        CalendarRequest calendarRequest = new CalendarRequest();
        return CalendarDTO.toObject(calendarRequest.getCalendar());
    }
    @Override
    protected CalendarDTO doInBackground(Void... params) {
        calendar = getCalendar();
        return calendar;
    }

    @Override
    protected void onPostExecute(CalendarDTO calendar){
        Log.v("Calendar", String.valueOf(calendar.getYear()));
    }

}
