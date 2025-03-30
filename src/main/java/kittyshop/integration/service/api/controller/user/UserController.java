package kittyshop.integration.service.api.controller.user;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.controller.BaseController;
import kittyshop.integration.service.api.dto.Response;
import kittyshop.integration.service.api.dto.UserUpdateRequestDto;
import kittyshop.integration.service.api.entity.user.User;
import kittyshop.integration.service.api.mapper.UserMapper;
import kittyshop.integration.service.api.service.UserService;
import kittyshop.integration.service.api.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static kittyshop.integration.service.api.utils.PageableUtils.USERS_ORDER_LIST;

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(ControllerRoutes.USERS_GET)
    public ResponseEntity<Response> getAll(@PageableDefault Pageable pageable) {
        return this.response(userService.findAll(
                PageableUtils.generatePageable(pageable.getPageNumber(),
                        pageable.getPageSize(), USERS_ORDER_LIST)));
    }

    @GetMapping(ControllerRoutes.USER_GET)
    public ResponseEntity<Response> get(@PathVariable("id") Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(category -> this.response(userMapper.toUserResponseDto(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping(ControllerRoutes.USER_UPDATE)
    public ResponseEntity<Response> updateById(@PathVariable("id") Long id,
                                               @RequestBody @Valid UserUpdateRequestDto user) {
        return this.response(userService.updateById(id, user));
    }

    @DeleteMapping(ControllerRoutes.USER_DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
