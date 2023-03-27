package ua.com.foxminded.university.service.exception;

public class TeacherNotFoundException extends RuntimeException {

    public static final String TEACHER_WITH_ID_NOT_FOUND = "Teacher with ID = %d not found!";


    public TeacherNotFoundException(Long id) {
        super(String.format(TEACHER_WITH_ID_NOT_FOUND, id));
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
