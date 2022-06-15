package com.feith.jar.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import com.google.common.base.Strings;

public class Main
{
    /**
     * @param args
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException
    {
        String delimiter = args[0];
        String fileToReadPath = args[1];
        String fileToWritePath = args[2];

        File fileToRead = new File( fileToReadPath );

        try ( Scanner scannerToRead = new Scanner( fileToRead );
                FileWriter fileToWrite = new FileWriter( fileToWritePath ); )
        {
            String oneString = scannerToRead.useDelimiter( delimiter ).next();

            System.out.println( oneString );
            String[] splitString = oneString.split( "\\n" );

            System.out.println( splitString );

            for ( int i = 0; i < splitString.length; i++ )
            {

                if ( splitString[i].startsWith( "CASEID: " ) )
                {
                    String sub = splitString[i].substring( 8, splitString[i].length() );
                    System.out.println( "field001: " + sub );
                }

                if ( splitString[i].startsWith( "VISIT DATES: " ) )
                {

                    System.out.println( "dateString:" + splitString[i] );
                    String[] dateString = splitString[i].split( " - " );
                    String sub1 = dateString[0].substring( 13, dateString[0].length() );
                    String sub2 = dateString[1].substring( 0, dateString[1].length() );

                    System.out.println( "date1:" + sub1 );
                    System.out.println( "date2:" + sub2 );

//                  
                    try
                    {
                        Date rawDate = new SimpleDateFormat( "dd MMM yyyy" ).parse( sub1 );
                        String pattern = "dd/MM/yyyy";
                        SimpleDateFormat dateFormat = new SimpleDateFormat( pattern );
                        String formattedDate = dateFormat.format( rawDate );

                        System.out.println( "formatted date: " + formattedDate );

                    } catch ( ParseException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
            ;
        } catch ( FileNotFoundException e )
        {
            System.out.println( e );
        }
    }
}