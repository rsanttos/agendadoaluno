package pds.ufrn.com.br.agendadoaluno.dto;

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

}
