package org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto;

import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.SisoClassInfo;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.SysOrganization;

import java.util.List;

public class SysOrganizationDto extends SysOrganization {
    List<SisoClassInfo> sisoClassInfoList;

    public List <SisoClassInfo> getSisoClassInfoList() {
        return sisoClassInfoList;
    }

    public void setSisoClassInfoList(List <SisoClassInfo> sisoClassInfoList) {
        this.sisoClassInfoList = sisoClassInfoList;
    }
}
