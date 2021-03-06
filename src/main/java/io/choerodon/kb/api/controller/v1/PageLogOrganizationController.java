package io.choerodon.kb.api.controller.v1;

import io.choerodon.core.annotation.Permission;
import io.choerodon.core.enums.ResourceType;
import io.choerodon.core.iam.InitRoleCode;
import io.choerodon.kb.api.vo.PageLogVO;
import io.choerodon.kb.app.service.PageLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Zenger on 2019/5/17.
 */
@RestController
@RequestMapping(value = "/v1/organizations/{organization_id}/page_log")
public class PageLogOrganizationController {

    @Autowired
    private PageLogService pageLogService;

    @Permission(type = ResourceType.ORGANIZATION, roles = {InitRoleCode.ORGANIZATION_ADMINISTRATOR, InitRoleCode.ORGANIZATION_MEMBER})
    @ApiOperation("查询页面操作日志")
    @GetMapping(value = "/{page_id}")
    public ResponseEntity<List<PageLogVO>> listByPageId(@ApiParam(value = "组织ID", required = true)
                                                        @PathVariable(value = "organization_id") Long organizationId,
                                                        @ApiParam(value = "页面id", required = true)
                                                        @PathVariable(name = "page_id") Long pageId) {
        return new ResponseEntity<>(pageLogService.listByPageId(organizationId, null, pageId), HttpStatus.OK);
    }
}
