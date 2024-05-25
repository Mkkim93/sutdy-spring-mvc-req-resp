package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username:{}, age:{}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username")String memberName,
                                 @RequestParam("age")int memberAge) {

        log.info("memberName:{}, memberAge:{}", memberName, memberAge);

        return "ok"; // @ResponseBody 를 입력하면 ok 라는 문자를 html 화면에 바로 노출시켜준다 (@RestController 와 같은 효과)
    }

    // v3 에서는 @RequestParam() 내부에 선언된 변수명을 생략하였다. 변수명이 생략 가능한 경우는 log의 변수명과 @RequestParma 의 변수명이 일치할 떄 가능하다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    // @RequestParam 생략 가능 : 요청 파라미터 이름과 일치할 떄 + String, Integer, int 등 일때만 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    // required(기본값 true) : true 이면 해당 파라미터가 무조건 들어와야된다. / false 이면 해당 파라미터가 없어도 된다. (요청스펙에 따라 설정)
    // 아래의 코드에서 실행 결과는 만약 username(required = true) 값을 생략하면 errorStatus code : 400 이된다.
    // 만약 아래 코드에서 username 을 넣고 age 를 빼면 어떻게 될까? -> 500 error(server) 가 발생한다.
    // why? : int 형이 들어가는 곳에 데이터를 입력하지 않으면 null 이 반환되기 때문이다.
    // 해결 방법 : age 를 입력하지 않아도 에러를 해결하는 방법은 int -> Integer 로 변경 해줘야 한다 (null 예외 없음)
    // 참고로 url 에 username 에 스페이스바를 입력하면 encoding 되어 %20 로 변환된다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                         @RequestParam(required = false) int age) {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    // defaultValue = "none" 으로 설정하면 입력 파라미터가 없어도 정상적으로 서버가 실행되고 해당 username = none 으로 표시된다.
    // 참고 : 빈문자의 경우에도 defaultValue 로 설정한 문자가 출력된다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue= "guest") String username,
                                         @RequestParam(defaultValue = "-1") int age) {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    // username 과 age 를 Map 에 담을 수 있다. (해당 메서드에서 Map 선언과 동시에 age 의 변수타입을 int->Object 으로 변경하였다)
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(Map<String, Object> paramMap) {
        log.info("paramMap:{}", paramMap);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData hellodata) {
        log.info("username:{}, age:{}", hellodata.getUsername(), hellodata.getAge());
        return "ok";
    }

    // @ModelAttribute 생략 가능 (HttpServlet 같은 경우는 생략 불가)
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData hellodata) {
        log.info("username:{}, age:{}", hellodata.getUsername(), hellodata.getAge());
        return "ok";
    }
}