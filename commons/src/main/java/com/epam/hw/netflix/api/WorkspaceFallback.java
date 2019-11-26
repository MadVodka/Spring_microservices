package com.epam.hw.netflix.api;

import com.epam.hw.netflix.domain.OSFamily;
import com.epam.hw.netflix.domain.Workspace;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceFallback implements WorkspaceAPI {
    @Override
    public Workspace getWorkspaceById(String id) {
        Workspace workspace = new Workspace();
        workspace.setId(id)
                .setUnit(0)
                .setSeat(0)
                .setSerialNumber("0")
                .setOsFamily(OSFamily.LINUX);

        return workspace;
    }
}
