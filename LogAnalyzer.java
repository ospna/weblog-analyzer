/**
 * Read web server data and analyse hourly, daily, and mothly access patterns.
 * 
 * @author Giovanny Ospina
 * @version 11.12.2017
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the daily access counts.
    private int[] dayCounts;
    // Where to calculate the monthly access counts.
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly, daily, and montly web accesses.
     */
    public LogAnalyzer(String web)
    { 
        // Create the array object to hold the hourly access counts.
        hourCounts = new int[24];
        // Create the array object to hold the daily access counts.
        dayCounts = new int[31];
        // Create the array object to hold the monthly access counts.
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(web);
    }

    /**
     * Analyze the hourly, daily, and monthly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;
        }
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * @return total
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total.
        for (int hour = 0; hour < hourCounts.length; hour++)
        {
            total = total + hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Return the busiest hour
     * @return busiesthour
     */
    public int busiestHour()
    {
        int busiest = 0;
        int busiestHour = 0;
        
        for (int hour = 0; hour < hourCounts.length; hour++)
        {
            if (busiest < hourCounts[hour])
            {
                busiest = hourCounts[hour];
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
    
    /**
     * Return the quietest hour
     * @return quietesthour
     */
    public int quietestHour()
    {
        int quietest = hourCounts[0];
        int quietestHour = 0;
        
        for (int hour = 0; hour < hourCounts.length; hour++)
        {
            if (quietest > hourCounts[hour])
            {
                quietest = hourCounts[hour];
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
    
    /** 
     * Return the busiest two hour period.
     * @return busiestPeriodHour
     */
    public int busiestTwoHourPeriod()
    {
        int busiestPeriod = 0;
        int busiestPeriodHour = 0;

        for (int hour = 0; hour < hourCounts.length - 1; hour++) 
        {
            int periodCount = hourCounts[hour] + hourCounts[hour+1];
            if (periodCount > busiestPeriodHour)
            {
                busiestPeriod = hour;
                busiestPeriodHour = periodCount;
            }
        }
        return busiestPeriodHour;
    }
    
    /**
     * Return the busiest day
     * @return busiestday
     */
    public int busiestDay()
    {
        int busiest = 0;
        int busiestday = 0;
        
        for (int day = 0; day < dayCounts.length; day++)
        {
            if (busiest < dayCounts[day])
            {
                busiest = dayCounts[day];
                busiestday = day;
            }
        }
        return busiestday;
    }
    
    /**
     * Return the quietest day
     * @return quietestday
     */
    public int quietestDay()
    {
        int quietest = dayCounts[1];
        int quietestday = 0;
        
        for (int day = 0; day < dayCounts.length; day++)
        {
            if (quietest < dayCounts[day])
            {
                quietest = dayCounts[day];
                quietestday = day;
            }
        }
        return quietestday;
    }
    
    /**
     * Return the busiest month
     * @return busiestmonth
     */
    public int busiestMonth()
    {
        int busiest = 0;
        int busiestmonth = 0;
        
        for (int month = 0; month < monthCounts.length; month++)
        {
            if (busiest < monthCounts[month])
            {
                busiest = monthCounts[month];
                busiestmonth = month;
            }
        }
        return busiestmonth;
    }
    
    /**
     * Return the quietest month
     * @return quietestmonth
     */
    public int quietestMonth()
    {
        int quietest = monthCounts[1];
        int quietestmonth = 0;
        
        for (int month = 1; month < monthCounts.length; month++)
        {
            if (quietest < monthCounts[month])
            {
                quietest = monthCounts[month];
                quietestmonth = month;
            }
        }
        return quietestmonth;
    }
    
    /**
     * Return the average number of access per month
     * @return average
     */
    public int averageAccessesPerMonth()
    {
        int average = 0;
        
        for(int month = 0; month < monthCounts.length; month++)
        {
            average = average + monthCounts[month];
        }
        
        average = average / 12;
        return average;
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
