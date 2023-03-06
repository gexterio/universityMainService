package ua.com.foxminded.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.persistance.repository.LessonRepository;
import ua.com.foxminded.university.util.modelmapper.LessonMapper;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository repository;
    private final LessonMapper mapper;

    @Autowired
    public LessonService(LessonRepository repository, LessonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<LessonDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public LessonDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->new RuntimeException("lesson with id = " + id + " not found"));
    }

    @Transactional
    public LessonDTO create(LessonDTO dto) {
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public LessonDTO update(LessonDTO lesson) {
        return repository.findById(lesson.getId())
                .map(entity -> mapper.toEntity(lesson))
                .map(repository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("lesson with id = " + lesson.getId() + " not found"));
    }

    public boolean delete(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                })
                .orElse(false);
    }

    public List<LessonDTO> findLessonsForStudentForDay(Long id, ZonedDateTime day) {
        ZonedDateTime from = day.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusDays(1);
        return repository.findAllLessonsByStudentIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LessonDTO> findLessonsForStudentForMonth(Long id, ZonedDateTime month) {
        ZonedDateTime from = month.truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1);
        ZonedDateTime to = from.plusMonths(1);
        return repository.findAllLessonsByStudentIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LessonDTO> findLessonsForTeacherForDay(Long id, ZonedDateTime day) {
        ZonedDateTime from = day.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusDays(1);
        return repository.findAllLessonsByTeacherIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LessonDTO> findLessonsForTeacherForMonth(Long id, ZonedDateTime month) {
        ZonedDateTime from = month.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusMonths(1);
        return repository.findAllLessonsByTeacherIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
