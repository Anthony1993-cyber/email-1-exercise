package co.develhope.email1exercise.entities;

import lombok.Data;

@Data
public class NotificationDTO {

    private String contactId;
    private String title;
    private String text;
}
