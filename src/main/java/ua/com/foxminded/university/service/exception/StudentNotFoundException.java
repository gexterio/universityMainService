package ua.com.foxminded.university.service.exception;

public class StudentNotFoundException extends RuntimeException {

    private static final String STUDENT_NOT_FOUND_WITH_ID = "Student with ID = %d not found!";


    public StudentNotFoundException(Long id) {
        super(String.format(STUDENT_NOT_FOUND_WITH_ID, id));
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
