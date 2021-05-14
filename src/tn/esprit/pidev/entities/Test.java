package tn.esprit.pidev.entities;

import java.util.Comparator;
import java.sql.Date;

public class Test {
    private int id;
    private String name;
    private String category;
    private String contenu;
    private Date date;
    private String teacherName;

    public Test() {
    }

    public Test(String name, String category, String contenu, Date date, String teacherName) {
        this.name = name;
        this.category = category;
        this.contenu = contenu;
        this.date = date;
        this.teacherName = teacherName;
    }

    public Test(int id, String name, String category, String contenu, Date date, String teacherName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.contenu = contenu;
        this.date = date;
        this.teacherName = teacherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public static Comparator<Test> nameComparator = new Comparator<Test>() {
        @Override
        public int compare(Test o1, Test o2) {
            return (int) (o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        }
    };
    public static Comparator<Test> categoryComparator = new Comparator<Test>() {
        @Override
        public int compare(Test o1, Test o2) {
            return (int) (o1.getCategory().toLowerCase().compareTo(o2.getCategory().toLowerCase()));
        }
    };
}
