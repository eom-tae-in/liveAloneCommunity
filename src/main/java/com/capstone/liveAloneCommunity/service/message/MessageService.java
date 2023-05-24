package com.capstone.liveAloneCommunity.service.message;

import com.capstone.liveAloneCommunity.dto.message.MessageResponseDto;
import com.capstone.liveAloneCommunity.entity.member.Member;
import com.capstone.liveAloneCommunity.entity.message.Message;
import com.capstone.liveAloneCommunity.exception.message.CanNotSameReceiverAndSenderException;
import com.capstone.liveAloneCommunity.repository.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageResponseDto writeMessage(Member member, Member receiver, String content) {
        isSameSenderAndReceiver(member, receiver);
        Message message = new Message(member, receiver, content);
        messageRepository.save(message);
        return MessageResponseDto.toDto(message);
    }

    private void isSameSenderAndReceiver(Member member, Member receiver) {
        if (member.equals(receiver)) {
            throw new CanNotSameReceiverAndSenderException();
        }
    }
}
