package pds.ufrn.com.br.agendadoaluno.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by f202359 on 25/04/2018.
 */

public class DateUtil {

    public static String format(long mili){
        Date date = new Date(mili);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }
}
