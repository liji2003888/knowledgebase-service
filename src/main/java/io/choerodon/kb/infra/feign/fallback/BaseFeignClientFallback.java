package io.choerodon.kb.infra.feign.fallback;

import io.choerodon.core.exception.FeignException;
import io.choerodon.kb.infra.feign.BaseFeignClient;
import io.choerodon.kb.infra.feign.vo.OrganizationDTO;
import io.choerodon.kb.infra.feign.vo.UserDO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Zenger on 2019/4/30.
 */
@Component
public class BaseFeignClientFallback implements BaseFeignClient {

    private static final String BATCH_QUERY_ERROR = "error.baseFeign.queryList";
    private static final String LIST_ORGANIZATION_ERROR = "error.baseFeign.listOrganizationByUserId";

    @Override
    public ResponseEntity<List<UserDO>> listUsersByIds(Long[] ids, Boolean onlyEnabled) {
        throw new FeignException(BATCH_QUERY_ERROR);
    }

    @Override
    public ResponseEntity<List<OrganizationDTO>> listOrganizationByUserId(Long userId) {
        throw new FeignException(LIST_ORGANIZATION_ERROR);
    }
}
