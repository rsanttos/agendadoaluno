package pds.ufrn.com.br.agendadoaluno.request;

/**
 * Created by root on 24/04/18.
 */

public class CalendarRequest extends GenericRequest{
    public String getCalendar(){
        url = String.format("/calendar");
        String resultado = super.objectRequest();
        return resultado;
    }
}
