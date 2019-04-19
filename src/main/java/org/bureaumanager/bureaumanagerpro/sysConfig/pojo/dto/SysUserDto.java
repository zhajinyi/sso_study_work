package org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto;

import com.google.common.collect.Lists;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysUser;
import java.util.List;

public class SysUserDto extends SysUser {
    private SysEmpInfoDto sysEmpInfoDto;//用户详细信息
    private SysRoleDto sysRoleDto;	// 根据角色查询用户条件
    private List<SysRoleDto> sysRoleDtos; // 拥有角色列表
    public boolean isAdmin(){
        return isAdmin(this.getId());
    }

    public static boolean isAdmin(String id){
        return id != null && "sysadmin".equals(id);
    }

    public SysEmpInfoDto getSysEmpInfoDto() {
        return sysEmpInfoDto;
    }

    public void setSysEmpInfoDto(SysEmpInfoDto sysEmpInfoDto) {
        this.sysEmpInfoDto = sysEmpInfoDto;
    }

    public SysRoleDto getSysRoleDto() {
        return sysRoleDto;
    }

    public void setSysRoleDto(SysRoleDto sysRoleDto) {
        this.sysRoleDto = sysRoleDto;
    }

    public List<SysRoleDto> getSysRoleDtos() {
        return sysRoleDtos;
    }

    public void setSysRoleDtos(List<SysRoleDto> sysRoleDtos) {
        this.sysRoleDtos = sysRoleDtos;
    }
}
