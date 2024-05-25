package hello.springmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStreamController {

    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletResponse response, HttpServletRequest request)
            throws IOException, ServletException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody); // api 에서 요청한 데이터가 {} 내부로 들어간다 (messageBody 의 값)

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws IOException, ServletException {

        // ServletInputStream inputStream = request.getInputStream(); 생략 가능 메서드 선언에서 InputSteam 을 생성
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity)
            throws IOException, ServletException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);
        return new HttpEntity<>("ok");

        // ServletInputStream inputStream = request.getInputStream(); 생략 가능 메서드 선언에서 InputSteam 을 생성
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); 생략 가능
        // HttpEntity<> 사용
        // responseWriter.write("ok");
    }

    /**
     * body 정보가 필요할 떄(조회 등) 실무에서 가장 많이 쓰는 로직
     * */
    // Http Body 의 데이터를 요청할 때 메서드 내부에 @RequestBody 를 통해 messageBody 를 읽어오는 객체를 생성한다.
    // 해당 시점부터 messageBody 는 html body 로 데이터를 처리한다.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody)
            throws IOException, ServletException {

        log.info("messageBody = {}", messageBody);

        return "ok";
    }
}
