package com.example.whatsapp_clone.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class ConversationParticipantId implements Serializable {

    private UUID conversationId;
    private UUID userId;

}
