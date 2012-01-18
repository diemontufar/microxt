package mobile.web.webxt.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;


public class SimpleDateFormat extends DateFormat {

    private DateTimeFormat format;

    public SimpleDateFormat()
    {
        super();
    }


    protected SimpleDateFormat( DateTimeFormat format )
    {
        this.format = format;
    }

    public SimpleDateFormat( String pattern )
    {
        applyPattern( pattern );
    }

    public void applyPattern (String pattern)
    {
        this.format = DateTimeFormat.getFormat( pattern );
    }

    public String format( Date date )
    {
        return format.format( date );
    }

    /**
     * Parses text and returns the corresponding date object.
     */
    public Date parse( String source )
    {
        return format.parse( source );
    }

}