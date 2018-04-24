package pds.ufrn.com.br.agendadoaluno.dto;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by root on 24/04/18.
 */

public class CalendarDTO {
    private int year;
    private long startOnlineEnrollment;
    private long startExtraordinaryEnrollment;
    private long startPeriod;
    private long startReEnrollment;
    private long endExtraordinaryEnrollment;
    private long endOnlineEnrollment;
    private long endPeriod;
    private long endReEnrollment;

    private List<HolidayDTO> holidays;

    public CalendarDTO() {
        super();
    }

    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public long getStartOnlineEnrollment() {
        return startOnlineEnrollment;
    }


    public void setStartOnlineEnrollment(long startOnlineEnrollment) {
        this.startOnlineEnrollment = startOnlineEnrollment;
    }


    public long getStartExtraordinaryEnrollment() {
        return startExtraordinaryEnrollment;
    }


    public void setStartExtraordinaryEnrollment(long startExtraordinaryEnrollment) {
        this.startExtraordinaryEnrollment = startExtraordinaryEnrollment;
    }


    public long getStartPeriod() {
        return startPeriod;
    }


    public void setStartPeriod(long startPeriod) {
        this.startPeriod = startPeriod;
    }


    public long getStartReEnrollment() {
        return startReEnrollment;
    }


    public void setStartReEnrollment(long startReEnrollment) {
        this.startReEnrollment = startReEnrollment;
    }


    public long getEndExtraordinaryEnrollment() {
        return endExtraordinaryEnrollment;
    }


    public void setEndExtraordinaryEnrollment(long endExtraordinaryEnrollment) {
        this.endExtraordinaryEnrollment = endExtraordinaryEnrollment;
    }


    public long getEndOnlineEnrollment() {
        return endOnlineEnrollment;
    }


    public void setEndOnlineEnrollment(long endOnlineEnrollment) {
        this.endOnlineEnrollment = endOnlineEnrollment;
    }


    public long getEndPeriod() {
        return endPeriod;
    }


    public void setEndPeriod(long endPeriod) {
        this.endPeriod = endPeriod;
    }


    public long getEndReEnrollment() {
        return endReEnrollment;
    }


    public void setEndReEnrollment(long endReEnrollment) {
        this.endReEnrollment = endReEnrollment;
    }


    public List<HolidayDTO> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<HolidayDTO> holidays) {
        this.holidays = holidays;
    }

    public static CalendarDTO toObject(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, CalendarDTO.class);
    }
}
