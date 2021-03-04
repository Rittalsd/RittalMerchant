package sd.rittal.qrsdk.uitl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateSplite {

    public String expDate(String textdate) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy/MM", Locale.ENGLISH);
            DateFormat outputFormat = new SimpleDateFormat("yyMM", Locale.ENGLISH);
            Date date = inputFormat.parse(textdate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
