package hello.springmvc.basic.requestmapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 회원 관리 API
     * 회원 목록 조회: GET /users
     * 회원 등록: POST /users
     * 회원 조회: GET /users/{userId}
     * 회원 수정: PATCH /users/{userId}
     * 회원 삭제: DELETE /users/{userId}
     */

    @GetMapping()
    // @GetMapping("/mapping/users")
    public String user() {

        return "get users";
    }

    @PostMapping()
    // @PostMapping("/mapping/users")
    public String addUser() {

        return "post user";
    }

    @GetMapping("/{userId}")
    // @GetMapping("/mapping/users/{userId}")
    public String findUser(@PathVariable String userId) {

        return "get user" + userId;
    }

    @PatchMapping("/{userId}")
    // @PatchMapping("/mapping/users/{userId}")
    public String updateUser(@PathVariable String userId) {

        return "update userId" + userId;
    }

    @DeleteMapping("/{userId}")
    // @DeleteMapping("/mapping/users/{userId}")
    public String deleteUser(@PathVariable String userId) {

        return "delete userId" + userId;
    }
}
