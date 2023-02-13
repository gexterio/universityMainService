package ua.com.foxminded.university.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.model.StudentEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query(value = "select l from StudentEntity s " +
            "join GroupEntity g on s.group.id = g.id " +
            "join LessonEntity l on g.id = l.group.id " +
            "where s.id = :id " +
            "and date_trunc('day', l.startTime) = :date")
    List<LessonEntity> findAllLessonsForDayByStudentIdAndDate(Long id, Date date);

    @Query(value = "select l from StudentEntity s " +
            "join fetch GroupEntity g on s.group.id = g.id " +
            "join fetch LessonEntity l on g.id = l.group.id " +
            "where s.id= :id " +
            "and date_trunc('month', l.startTime) = :date")
    List<LessonEntity> findAllLessonsForMonthByStudentIdAndDate(Long id, Date date);

}
