package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static String sqlDateFormat = "%s-%s-%s";

    public static LocalDate fromSql(String sqlDate){

        String[] dtSplit = sqlDate.split("-");

        int month = 0;
        switch (dtSplit[1]){
            case "JAN":
                month = 1;
                break;
            case "FEB":
                month = 2;
                break;
            case "MAR":
                month = 3;
                break;
            case "APR":
                month = 4;
                break;
            case "MAY":
                month = 5;
                break;
            case "JUN":
                month = 6;
                break;
            case "JUL":
                month = 7;
                break;
            case "AUG":
                month = 8;
                break;
            case "SEP":
                month = 9;
                break;
            case "OCT":
                month = 10;
                break;
            case "NOV":
                month = 11;
                break;
            case "DEC":
                month = 12;
                break;
            default:
                month = 1;
                break;

        }

        LocalDate date = LocalDate.of(Integer.parseInt(dtSplit[2].substring(2)),
                month,
                Integer.parseInt(dtSplit[0]));

        return date;
    }
    public static String toSql(LocalDate lclDate){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String dtStr =  dtf.format(lclDate);

        String[] dtSplit = dtStr.split("/");

        String month;

        switch (dtSplit[1]){
            case "01":
                month = "JAN";
                break;
            case "02":
                month = "FEB";
                break;
            case "03":
                month = "MAR";
                break;
            case "04":
                month = "APR";
                break;
            case "05":
                month = "MAY";
                break;
            case "06":
                month = "JUN";
                break;
            case "07":
                month = "JUL";
                break;
            case "08":
                month = "AUG";
                break;
            case "09":
                month = "SEP";
                break;
            case "10":
                month = "OCT";
                break;
            case "11":
                month = "NOV";
                break;
            case "12":
                month = "DEC";
                break;
            default:
                month = "JAN";
                break;
        }

        return String.format(sqlDateFormat , dtSplit[2], month, dtSplit[0]);
    }
}
