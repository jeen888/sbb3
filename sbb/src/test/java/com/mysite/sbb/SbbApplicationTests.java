package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SbbApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // @Test
    @DisplayName("JPA 테스트를 위한 더미 데이터 생성")
    void testJpa() {
        for (int i = 1; i <= 1024; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무 " + i;
            this.questionService.create(subject, content, null);
        }
    }

    // @Test
    @DisplayName("쿼리 테스트 - id=1 게시물의 내용 '내용무'인지 확인")
    void testJpa2() {
        int id = 1;
        Optional<Question> oq = this.questionService.findById(id);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("내용무", q.getContent());
        }
    }

    // @Test
    @DisplayName("쿼리 테스트 - 총 갯수 알아오기")
    void testJpa3() {
        assertEquals(400, this.questionRepository.count());
    }

    // @Test
    // @DisplayName("쿼리 테스트 - id=1에 답변 달기")
    void testJpa4() {
        Optional<Question> oq = this.questionService.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    // @Test
    @Transactional // 지연 로딩 때문에 필요, JUnit 테스트에서는 N번 쿼리 실행시 끊기기 때문
    @DisplayName("쿼리 테스트 - id=1 질문의 답변 가져오기")
    void testJpa5() {
        Optional<Question> oq = this.questionService.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(3, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

    // @Test
    void simpleEcho() {
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // Simple Echo Test");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Simple Echo Test");
    }

    // @Test
    // @DisplayName("전체 질문 수 조회 테스트")
    void simpleTest() {
        int count = this.questionService.findAllCount();
        assertEquals(400, count);
    }
}
