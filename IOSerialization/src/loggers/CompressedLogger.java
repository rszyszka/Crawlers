package loggers;

import crawler.Logger;
import crawler.Student;
import java.io.*;
import java.util.Calendar;
import java.util.zip.*;

public class CompressedLogger implements Logger, Closeable
{
    private String zipName;
    private File zip;
    private FileOutputStream fos;
    private Calendar now;
    private String fileName;

    public CompressedLogger()
    {
        zipName = "logs.zip";
        zip = new File( zipName );
        if ( !zip.isFile() )
        {
            try
            {
                fos = new FileOutputStream( zip );
                try (ZipOutputStream zos = new ZipOutputStream( fos )) {
                    zos.closeEntry();
                }
            }
            catch ( IOException e ) {}
        }
    }


    public synchronized static void zipIt( File zipFile, File file ) throws IOException
    {
        File tempFile = File.createTempFile( zipFile.getName(), null );
        tempFile.delete();

        boolean renameOk = zipFile.renameTo( tempFile );
        if ( !renameOk )
            throw new RuntimeException( "Couldn't change name of " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath() );

        byte[] buf = new byte[128];

        ZipOutputStream out;
        try (ZipInputStream zip = new ZipInputStream( new FileInputStream( tempFile ) )) {
            out = new ZipOutputStream( new FileOutputStream( zipFile ) );
            ZipEntry entry = zip.getNextEntry();
            while ( entry != null )
            {
                String name = entry.getName();
                boolean notInFiles = true;
                if ( file.getName().equals( name ) )
                {
                    notInFiles = false;
                    break;
                }
                
                if ( notInFiles )
                {
                    out.putNextEntry( new ZipEntry( name ) );
                    int len;
                    while ( ( len = zip.read( buf ) ) > 0 )
                        out.write( buf, 0, len );
                }
                entry = zip.getNextEntry();
            }
        }
        try (InputStream in = new FileInputStream( file )) {
            out.putNextEntry( new ZipEntry( file.getName() ) );
            
            int len;
            while ( ( len = in.read( buf ) ) > 0 )
                out.write( buf, 0, len );
            
            out.closeEntry();
        }
        out.close();
        tempFile.delete();
    }

    @Override
    public synchronized void log( String status, Student student )
    {
        now = Calendar.getInstance();
        fileName = String.format("%1$tY.%1$tm.%1$td_%1$tH.%1$tM.%1$tS.%1$tL.txt", now);
        File tmpfile;

        TextLogger tmpTextLog = new TextLogger( fileName );
        tmpTextLog.log( status, student );
        try
        {
            tmpTextLog.close();
            tmpfile = new File( fileName );
            zipIt(zip, tmpfile );
            tmpfile.delete();
        }
        catch ( IOException e ) {}
    }

    @Override
    public synchronized void close() throws IOException
    {
        fos.close();
    }
}
