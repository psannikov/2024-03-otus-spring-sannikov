package ru.otus.spring.psannikov.webflux.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ru.otus.spring.psannikov.webflux.models.Comment;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String id;

    private String fullComment;

    public static CommentDto entityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(comment, commentDto);
        return commentDto;
    }

    public static Comment dtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        return comment;
    }
}
