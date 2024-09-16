package com.kdu.smartHome.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {
    private Long roomId;
    private String roomName;
}