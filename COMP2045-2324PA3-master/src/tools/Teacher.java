package tools;

public class Teacher {
    private String id;
    private String teacherName;
    private String email;

    public Teacher() {
    }

    public Teacher(String id, String teacherName, String email) {
        this.id = id;
        this.teacherName = teacherName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
