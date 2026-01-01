package com.example.whatsapp_clone.domain.entities;

import com.example.whatsapp_clone.domain.enums.ParticipantRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="conversation_participants")
public class ConversationParticipant {

    @EmbeddedId
    private ConversationParticipantId conversationParticipantId;

    @ManyToOne
    @MapsId("conversationId")
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ParticipantRole role;


    private Instant joinedAt = Instant.now();

    private Instant lastReadAt;
}
