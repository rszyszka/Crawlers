package crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Szysz
 */
public class Crawler {

    private String adress;
    Set<Student> baseStudents;
    private OrderMode mode;
    

    public Crawler(String adr, OrderMode mode) {

        this.adress = adr;
        baseStudents = new HashSet();
        this.mode = mode;
    }

    public String getAdress() {
        return adress;
    }

    public OrderMode getMode() {
        return mode;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setMode(OrderMode mode) {
        this.mode = mode;
    }

    public EmptyEvent iterationStartedEvent = new EmptyEvent();
    public EmptyEvent iterationFinishedEvent = new EmptyEvent();
    public CustomEvent<Student> studentAddedEvent = new CustomEvent<>();
    public CustomEvent<Student> studentRemovedEvent = new CustomEvent<>();
    public CustomEvent<Student> studentNotModifiedEvent = new CustomEvent<>();

    boolean looped = false;

    public synchronized void postCancel() {
        this.looped = false;
    }

    public synchronized void run() throws IOException, InterruptedException, CrawlerException {

        int iteracja = 0;
        this.looped = true;
        while (this.looped) {

            boolean changed = false;
            iteracja++;
            List<Student> students;

            if (adress.contains("http") && adress.contains(".txt")) {
                students = StudentsParser.parse(new URL(adress));
            } else if (adress.contains(".txt")) {
                students = StudentsParser.parse(new File(adress));
            } else {
                throw new CrawlerException();
            }

            for (int i = 0; i < students.size(); i++) {
                if (!baseStudents.contains(students.get(i))) {
                    this.studentAddedEvent.fire(this, students.get(i));
                    baseStudents.add(students.get(i));
                    changed = true;
                } else {
                    this.studentNotModifiedEvent.fire(this, students.get(i));
                }
            }

            Iterator<Student> iterator = baseStudents.iterator();
            while (iterator.hasNext()) {
                Student a = iterator.next();

                if (!students.contains(a)) {
                    this.studentRemovedEvent.fire(mode, a);
                    iterator.remove();
                    changed = true;
                }
            }
            if (changed) {
                this.iterationFinishedEvent.fire(iteracja);
                this.iterationStartedEvent.fire(iteracja);
            }

            Thread.sleep(5000);

        }

    }

    public List<Student> extractStudents(OrderMode mode) {

        List<Student> temp = new ArrayList<>(baseStudents);
        if (null != mode) {
            switch (mode) {
                case MARK:
                    Collections.sort(temp, new ComparatorMarks());
                    System.out.println("Order by mark: ");
                    break;
                case FIRST_NAME:
                    Collections.sort(temp, new ComparatorFirstName());
                    System.out.println("Order by first name: ");
                    break;
                case LAST_NAME:
                    Collections.sort(temp, new ComparatorLastName());
                    System.out.println("Order by last name: ");
                    break;
                case AGE:
                    Collections.sort(temp, new ComparatorAge());
                    System.out.println("Order by age: ");
                    break;
                default:
                    break;
            }
        }

        for (Student el : temp) {
            System.out.println(el.getMark() + " " + el.getFirstName() + " " + el.getLastName() + " " + el.getAge());
        }

        return temp;
    }

    public double extractMarks(ExtremumMode mode) {

        if (baseStudents.isEmpty()) {
            return 0;
        }

        if (mode == ExtremumMode.MAX) {
            return Collections.max(baseStudents, new ComparatorMarks()).getMark();
        } else {
            return Collections.min(baseStudents, new ComparatorMarks()).getMark();
        }
    }

    public int extractAge(ExtremumMode mode) {

        if (baseStudents.isEmpty()) {
            return 0;
        }

        if (mode == ExtremumMode.MAX) {
            return Collections.max(baseStudents, new ComparatorAge()).getAge();
        } else {
            return Collections.min(baseStudents, new ComparatorAge()).getAge();
        }
    }

    private static class ComparatorMarks implements Comparator<Student> {

        public ComparatorMarks() {
        }

        @Override
        public int compare(Student t, Student t1) {
            return Double.compare(t.getMark(), t1.getMark());
        }
    }

    private static class ComparatorFirstName implements Comparator<Student> {

        public ComparatorFirstName() {
        }

        @Override
        public int compare(Student t, Student t1) {
            return t.getFirstName().compareTo(t1.getFirstName());
        }
    }

    private static class ComparatorLastName implements Comparator<Student> {

        public ComparatorLastName() {
        }

        @Override
        public int compare(Student t, Student t1) {
            return t.getLastName().compareTo(t1.getLastName());
        }
    }

    private static class ComparatorAge implements Comparator<Student> {

        public ComparatorAge() {
        }

        @Override
        public int compare(Student t, Student t1) {
            return Integer.compare(t.getAge(), t1.getAge());
        }
    }

}
