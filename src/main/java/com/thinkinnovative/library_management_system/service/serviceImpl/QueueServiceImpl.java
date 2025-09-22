package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.QueueDTO;
import com.thinkinnovative.library_management_system.entity.LibraryInformation;
import com.thinkinnovative.library_management_system.entity.Queue;
import com.thinkinnovative.library_management_system.repository.LibraryRepository;
import com.thinkinnovative.library_management_system.repository.MemberRepository;
import com.thinkinnovative.library_management_system.repository.QueueRepository;
import com.thinkinnovative.library_management_system.service.QueueService;

import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class QueueServiceImpl implements QueueService {

    private static final Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);

    private final LibraryRepository libraryRepository;
    private final MemberRepository memberRepository;
    private final QueueRepository queueRepository;

    @Autowired
    public QueueServiceImpl(LibraryRepository libraryRepository,
                            MemberRepository memberRepository,
                            QueueRepository queueRepository) {
        this.libraryRepository = libraryRepository;
        this.memberRepository = memberRepository;
        this.queueRepository = queueRepository;
    }

    /**
     * Adds a member to the queue for a specific book if out of stock.
     */
    @Override
    @Transactional
    @Async
    public CompletableFuture<String> addQueue(Integer bookId, Integer memberId) {

        try{

            Thread.sleep(5000);
            log.info("The thread has been implemented");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Optional<LibraryInformation> optionalLibrary = Optional.ofNullable(libraryRepository.findBook1ById(bookId));

        if (optionalLibrary.isEmpty()) {
            logger.warn("Book with ID {} not found", bookId);
            return CompletableFuture.completedFuture("Book not found");
        }

        LibraryInformation library = optionalLibrary.get();

        // Fetch current queue from the DB (now not directly from library)
        List<QueueDTO> currentQueue = queueRepository.findQueueWithMemberNameByBookID(bookId);

        // Check if already in queue
        boolean alreadyQueued = currentQueue.stream()
                .anyMatch(q -> q.getMember_id().equals(memberId));

        if (alreadyQueued) {
            logger.info("Member {} is already in the queue for book {}", memberId, bookId);
            return CompletableFuture.completedFuture("You are already in the queue for this book.");
        }

        int newPosition = currentQueue.size() + 1;

        Queue newQueueEntry = new Queue();
        newQueueEntry.setMemberId(memberId);
        newQueueEntry.setPosition(newPosition);
        newQueueEntry.setLibraryInformation(library);

        queueRepository.save(newQueueEntry);

        logger.info("Member {} added to queue at position {} for book ID {}", memberId, newPosition, bookId);
        return CompletableFuture.completedFuture("You have been added to the queue at position "+ newPosition) ;
    }

    /**
     * Returns the queue list with member names for a specific book.
     */
    @Override
    public List<QueueDTO> queueByBookid(Integer bookID) {
        logger.debug("Fetching queue list for book ID {}", bookID);
        return queueRepository.findQueueWithMemberNameByBookID(bookID);
    }
}
