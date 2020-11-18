package com.sys.course.analitics.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCalendarConverter implements Converter<String, Calendar> {

   @Override
    public Calendar convert(String string) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(string));

            return calendar;

            } catch (ParseException e) {

                e.printStackTrace();
                System.out.println("Não consegui converter num Calendar. String: " + string);
                return null;
        }
   }
}


