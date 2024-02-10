package com.example.miniproject.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {
    private Long roomId;
    private String roomName;
}