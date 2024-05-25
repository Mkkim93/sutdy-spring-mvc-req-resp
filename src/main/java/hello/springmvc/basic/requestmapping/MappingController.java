package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RestController
public class MappingController {

//    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic", method = GET)
    public String helloBasic() {

        log.info("hello-basic");

        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {

        log.info("mapping-get-v2");

        return "ok";
    }

    /** !중요
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @Pathvariable userId
     * /mapping/userA
     *
     */
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String data, @PathVariable("orderId") Long orderId) {

        log.info("mappingPath userId={}, orderId={}", data, orderId);

        return "ok";
    }

    // 특정 파라미터가 있어야 api 호출 가능
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {

        log.info("mapping-param");

        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {

        log.info("mapping-header");

        return "ok";
    }

    // 미디어 타입 조건 매핑
    @PostMapping(value = "mapping-consume", consumes = "application/json")
    public String mappingConsume() {

        log.info("mapping-consume");

        return "ok";
    }

    // 특정 타입 조건 매핑 (html, json 등등)
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduce() {

        log.info("mapping-produce");

        return "ok";
    }
}
