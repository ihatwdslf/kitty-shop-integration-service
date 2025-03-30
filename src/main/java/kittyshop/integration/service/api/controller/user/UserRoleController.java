package kittyshop.integration.service.api.controller.user;

import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.controller.BaseController;
import kittyshop.integration.service.api.dto.ListResponseDto;
import kittyshop.integration.service.api.dto.Response;
import kittyshop.integration.service.api.entity.user.UserRole;
import kittyshop.integration.service.api.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRoleController extends BaseController {

    private final UserRoleRepository roleRepository;

    @PostMapping(ControllerRoutes.USERS_GET_ROLES)
    public ResponseEntity<Response> getAll() {
        List<UserRole> userRoles = roleRepository.findAll();
        return this.response(new ListResponseDto<UserRole>()
                .setList(userRoles)
                .setTotalRows((long) userRoles.size())
        );
    }
}
