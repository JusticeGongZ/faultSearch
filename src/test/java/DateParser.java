import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class DateParser {
    public static void main(String[] args) {
//java时间格式转换： Sep 29, 2012 1:00:01 AM 如何转换成标准的java Date对象
/*
LetterDate or Time ComponentPresentationExamples
GEra designatorTextAD
yYearYear1996; 96
MMonth in yearMonthJuly; Jul; 07
wWeek in yearNumber27
WWeek in monthNumber2
DDay in yearNumber189
dDay in monthNumber10
FDay of week in monthNumber2
EDay in weekTextTuesday; Tue
aAm/pm markerTextPM
HHour in day (0-23)Number0
kHour in day (1-24)Number24
KHour in am/pm (0-11)Number0
hHour in am/pm (1-12)Number12
mMinute in hourNumber30
sSecond in minuteNumber55
SMillisecondNumber978
zTime zoneGeneral time zonePacific Standard Time; PST; GMT-08:00
ZTime zoneRFC 822 time zone-0800
*/
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-d yyyy K:m:s",Locale.ENGLISH);
        Date d2 = null;
        try {
            d2 = sdf.parse("Dec-25 2018 00:27:35.900");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d3 = new Date();
        System.out.println("d2 ====== "+d2.getTime());
//        System.out.println("d3 ====== "+sdf.format(d3));
    }
}
