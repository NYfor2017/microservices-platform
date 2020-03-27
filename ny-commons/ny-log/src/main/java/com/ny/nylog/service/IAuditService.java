package com.ny.nylog.service;

import com.ny.nylog.model.Audit;

import java.lang.annotation.*;

/**
 * 审计日志接口
 *
 * @author N.Y
 * @date 2020-03-26 21:46
 */
public interface IAuditService {
    void save(Audit audit);
}
