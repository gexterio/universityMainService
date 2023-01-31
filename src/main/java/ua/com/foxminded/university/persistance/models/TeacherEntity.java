package ua.com.foxminded.university.persistance.models;

import java.util.Objects;

public class TeacherEntity {
    private Long iD;
    private String firstName;
    private String lastName;
    private Byte age;
    private String grade;
    private Integer experience;
    private String email;
    private Long facultyID;

    public TeacherEntity() {
    }

    public TeacherEntity(TeacherEntityBuilder builder) {
        this.iD = builder.iD;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.grade = builder.grade;
        this.experience = builder.experience;
        this.email = builder.email;
        this.facultyID = builder.facultyID;
    }

    public static class TeacherEntityBuilder {
        private Long iD;
        private final String firstName;
        private final String lastName;
        private Byte age;
        private String grade;
        private Integer experience;
        private String email;
        private Long facultyID;

        public TeacherEntityBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public TeacherEntityBuilder setID(Long iD) {
            this.iD = iD;
            return this;
        }

        public TeacherEntityBuilder setAge(Byte age) {
            this.age = age;
            return this;
        }

        public TeacherEntityBuilder setExperience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public TeacherEntityBuilder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public TeacherEntityBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public TeacherEntityBuilder setFacultyId(Long facultyID) {
            this.facultyID = facultyID;
            return this;
        }

        public TeacherEntity build() {
            return new TeacherEntity(this);
        }
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "iD=" + iD +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", facultyID=" + facultyID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return age == that.age && experience == that.experience && Objects.equals(iD, that.iD) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && Objects.equals(grade, that.grade) && Objects.equals(email, that.email) && Objects.equals(facultyID, that.facultyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, firstName, lastName, age, grade, experience, email, facultyID);
    }
}
