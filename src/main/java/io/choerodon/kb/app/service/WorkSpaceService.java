package io.choerodon.kb.app.service;

import io.choerodon.kb.api.vo.*;
import io.choerodon.kb.infra.dto.WorkSpaceDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zenger on 2019/4/30.
 */
public interface WorkSpaceService {

    WorkSpaceDTO baseCreate(WorkSpaceDTO workSpaceDTO);

    WorkSpaceDTO baseUpdate(WorkSpaceDTO workSpaceDTO);

    WorkSpaceDTO selectById(Long id);

    /**
     * 校验项目层组织层权限
     *
     * @param organizationId
     * @param projectId
     * @param workSpaceId
     * @return
     */
    WorkSpaceDTO baseQueryById(Long organizationId, Long projectId, Long workSpaceId);

    /**
     * 校验项目层组织层权限，可以查询项目层权限
     *
     * @param organizationId
     * @param projectId
     * @param workSpaceId
     * @return
     */
    WorkSpaceDTO baseQueryByIdWithOrg(Long organizationId, Long projectId, Long workSpaceId);

    void checkById(Long organizationId, Long projectId, Long workSpaceId);

    List<WorkSpaceDTO> queryAllChildByWorkSpaceId(Long workSpaceId);

    WorkSpaceInfoVO createWorkSpaceAndPage(Long organizationId, Long projectId, PageCreateWithoutContentVO create);

    WorkSpaceInfoVO queryWorkSpaceInfo(Long organizationId, Long projectId, Long workSpaceId, String searchStr);

    WorkSpaceInfoVO updateWorkSpaceAndPage(Long organizationId, Long projectId, Long id, String searchStr, PageUpdateVO pageUpdateVO);

    void removeWorkSpaceAndPage(Long organizationId, Long projectId, Long workspaceId, Boolean isAdmin);

    void deleteWorkSpaceAndPage(Long organizationId, Long projectId, Long workspaceId);

    void restoreWorkSpaceAndPage(Long organizationId, Long projectId, Long workspaceId);

    void moveWorkSpace(Long organizationId, Long projectId, Long id, MoveWorkSpaceVO moveWorkSpaceVO);

    Map<String, Object> queryAllChildTreeByWorkSpaceId(Long workSpaceId, Boolean isNeedChild);

    Map<String, Map<String, Object>> queryAllTreeList(Long organizationId, Long projectId, Long expandWorkSpaceId);

    Map<String, Object> queryAllTree(Long organizationId, Long projectId, Long expandWorkSpaceId);

    List<WorkSpaceVO> queryAllSpaceByOptions(Long organizationId, Long projectId);

    List<WorkSpaceVO> querySpaceByIds(Long projectId, List<Long> spaceIds);

    /**
     * 校验用户是否有该组织的权限
     *
     * @param organizationId
     */
    void checkOrganizationPermission(Long organizationId);

    List<WorkSpaceRecentInfoVO> recentUpdateList(Long organizationId, Long projectId);

    Map<String, Object> recycleWorkspaceTree(Long organizationId, Long projectId);

    /**
     * 将知识库下面的所有文件放入回收站
     * @param organizationId
     * @param projectId
     * @param baseId
     */
    void removeWorkSpaceByBaseId(Long organizationId, Long projectId, Long baseId);

    /**
     * 将知识库下面的所有文件彻底删除
     * @param organizationId
     * @param projectId
     * @param baseId
     */
    void deleteWorkSpaceByBaseId(Long organizationId, Long projectId, Long baseId);

    /**
     * 将回收站中知识库下面的所有文件恢复到项目下
     * @param organizationId
     * @param projectId
     * @param baseId
     */
    void restoreWorkSpaceByBaseId(Long organizationId, Long projectId, Long baseId);
}
