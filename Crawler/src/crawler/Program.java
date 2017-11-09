package crawler;

;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;



public class Program {

    public static boolean changesDetected = false;

    public static void main(String[] args) throws IOException, InterruptedException, CrawlerException {

        final Logger[] loggers = new Logger[]{
            new ConsoleLogger(), //                new MailLogger()
        };

        Crawler craw = new Crawler("students.txt", OrderMode.MARK);

        craw.iterationStartedEvent.add((iteration) -> {
            System.out.println("Iteration: " + iteration);
        });

        craw.studentAddedEvent.add((source, student) -> {
            for (Logger el : loggers) {
                try {
                    el.log("ADDED", student);
                } catch (RemoteException ex) {
                    System.err.println(ex);
                }
            }
            changesDetected = true;
        });
        craw.studentRemovedEvent.add((source, student) -> {
            for (Logger el : loggers) {
                try {
                    el.log("REMOVED", student);
                } catch (RemoteException ex) {
                    System.err.println(ex);
                }
            }
            changesDetected = true;
        });

        craw.iterationFinishedEvent.add((iteration) -> {

            if (changesDetected) {

                craw.extractStudents(craw.getMode());
                System.out.println("Age: <" + craw.extractAge(ExtremumMode.MIN) + ", " + craw.extractAge(ExtremumMode.MAX) + ">");
                System.out.println("Marks: <" + craw.extractMarks(ExtremumMode.MIN) + ", " + craw.extractMarks(ExtremumMode.MAX) + ">");

            }

        });

        craw.run();

    }
}
