package ru.otus.spring.psannikov.mybatisdemo.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.otus.spring.psannikov.mybatisdemo.models.EMail;

import java.util.List;

@Mapper
public interface EmailRepository {

    @Select("select * from emails where student_id = #{studentId}")
    List<EMail> getEmailsByStudentId(long studentId);
}
