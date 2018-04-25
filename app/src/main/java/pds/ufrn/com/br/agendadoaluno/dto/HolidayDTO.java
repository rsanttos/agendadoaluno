package pds.ufrn.com.br.agendadoaluno.dto;

import java.sql.Date;

import pds.ufrn.com.br.agendadoaluno.util.DateUtil;

/**
 * Created by root on 24/04/18.
 */

public class HolidayDTO {
    private long date;
    private String description;

    public HolidayDTO() {
        super();
    }

    public HolidayDTO(long date, String description) {
        super();
        this.date = date;
        this.description = description;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String str = String.format("%s \n Data: %s \n", description, DateUtil.format(date));
        return str;
    }
}
