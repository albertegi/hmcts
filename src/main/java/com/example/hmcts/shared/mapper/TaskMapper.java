package com.example.hmcts.shared.mapper;

import com.example.hmcts.shared.domain.Task;
import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    /**
     * convert TaskRequestDto to Task Entity
     */
    public Task toEntity(TaskRequestDto dto){
        if(dto == null){
            return null;
        }

        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .dueDate(dto.getDueDate())
                .build();
    }

    /**
     * convert Task entity to TaskResponseDto
     */
    public TaskResponseDto toResponseDto(Task entity){
        if (entity == null){
            return null;
        }

        return TaskResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .dueDate(entity.getDueDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .overdue(entity.isOverdue())
                .dueSoon(entity.isDueSoon())
                .build();

    }

    /**
     * update existing Task entity with data from TaskRequestDto
     */

    public void updateEntityFromDto(Task entity, TaskRequestDto dto){
        if(entity == null || dto == null){
            return;
        }

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setDueDate(dto.getDueDate());
    }
}
